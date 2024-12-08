package crypto_trading.Ticker.service;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import crypto_trading.CryptoTradingApplication.AppConstants;
import crypto_trading.Ticker.jpa.BookTicker;
import crypto_trading.Ticker.jpa.BookTickerRepository;

@Service
public class BookTickerServiceImpl implements BookTickerService {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	private String houbiUri = "https://api.huobi.pro/market/tickers";
	private String binanceUri = "https://api.binance.com/api/v3/ticker/bookTicker";
	@Autowired
	private BookTickerRepository bookTickerRepo;
	
	@Override
	public void saveAll(List <BookTicker> bookTickerList) {
		bookTickerRepo.saveAll(bookTickerList);
	}

	@Override
	public List <BookTicker> getAll() {
	    return bookTickerRepo.findAll();
	}

	@Override
	public void fetchDataFromApi(String source) {
		String baseUri = "";
		switch(source) {
		case AppConstants.BINANCE:
			baseUri = binanceUri;
			break;
		case AppConstants.HOUBI:
			baseUri = houbiUri;
			break;
		}
		try {
		    RestTemplate restTemplate = new RestTemplate();
		    List<BookTicker> bookTickerList = new ArrayList<BookTicker>();
		    ResponseEntity<String> response = restTemplate.exchange(baseUri, HttpMethod.GET, null, String.class);
		    if(response.getStatusCode().toString().equals("200 OK")) {
		    	LOGGER.info("FETCHING TICKER " + baseUri);
		    	String responseBody = response.getBody();
			    ObjectMapper mapper = new ObjectMapper();
				JsonNode jsonNode = mapper.readTree(responseBody);
				if(source.equals(AppConstants.HOUBI))
					responseBody = jsonNode.get("data").toString();
			    bookTickerList = mapper.readValue(responseBody, new TypeReference<List<BookTicker>>(){});
			    //set source (Binance or Houbi) && Upper case the symbol
			    //set spread and spread percentage
			    bookTickerList.stream().forEach(item -> {
			    	item.setSource(source);
			    	item.setSymbol(item.getSymbol().toUpperCase());
			    	item.setSpread(item.getAskPrice() - item.getBidPrice());
			    	item.setPercentageSpread((item.getAskPrice() - item.getBidPrice())/item.getAskPrice()*100);
			    });
			      
			    bookTickerRepo.saveAll(bookTickerList);
			    LOGGER.info("SAVED TICKER TO H2DB " + baseUri);
		    }
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Optional<BookTicker> getBestPriceForSymbol(String symbol) {
		List<BookTicker> tickerList = bookTickerRepo.findBySymbol(symbol.toUpperCase());
		//get the lowest spread among source
		BookTicker bestTicker = tickerList.stream()
		        .sorted(Comparator.<BookTicker, Double>comparing(contact-> contact.getSpread()))
		        .findFirst().get();
		return Optional.ofNullable(bestTicker);
	}

	@Override
	public Optional<BookTicker> getBestPriceInSource(String source) {
		List<BookTicker> tickerList = bookTickerRepo.findBySource(source);
		//get the lowest spread in source
		BookTicker bestTicker = tickerList.stream()
		        .sorted(Comparator.<BookTicker, Double>comparing(contact-> contact.getSpread()))
		        .findFirst().get();
		return Optional.ofNullable(bestTicker);
	}
}
