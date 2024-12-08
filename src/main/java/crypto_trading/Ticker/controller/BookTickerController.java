package crypto_trading.Ticker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import crypto_trading.Ticker.jpa.BookTicker;
import crypto_trading.Ticker.jpa.BookTickerRepository;
import crypto_trading.Ticker.service.BookTickerService;
import crypto_trading.Ticker.service.BookTickerServiceImpl;


@RestController
@RequestMapping("/api/ticker")
public class BookTickerController {

	private BookTickerRepository bookTickerRepository;
	
	@Autowired
	private BookTickerService bookTickerService;
	
	@Autowired
    public BookTickerController(BookTickerServiceImpl bookTickerService, BookTickerRepository bookTickerRepository){
        this.bookTickerService = bookTickerService;
        this.bookTickerRepository = bookTickerRepository;
    }
	

	@GetMapping
	public List<BookTicker> getAllBookTickers() {
		return bookTickerRepository.findAll();
	}
  
	@PostMapping
	public BookTicker createBookTicker(@RequestBody BookTicker bookTicker) {
		return bookTickerRepository.save(bookTicker);
	}
  
	@GetMapping("/{symbol}")
	public Optional<List<BookTicker>> getBookTickerBySymbol(@PathVariable String symbol) {
		return Optional.ofNullable(bookTickerRepository.findBySymbol(symbol.toUpperCase()));
	}
  
  	@PutMapping("/{symbol}/{source}")
  	public BookTicker updateBookTicker(@PathVariable String symbol, @PathVariable String source, @RequestBody BookTicker bookTickerDetails) {
		BookTicker bookTicker = bookTickerRepository.findBySymbolAndSource(symbol.toUpperCase(), source);
		bookTicker.setSymbol(bookTickerDetails.getSymbol());
		bookTicker.setSource(bookTickerDetails.getSource());
		bookTicker.setBidQty(bookTickerDetails.getBidQty());
		bookTicker.setAskQty(bookTickerDetails.getAskQty());
		bookTicker.setBidPrice(bookTickerDetails.getBidPrice());
		bookTicker.setAskPrice(bookTickerDetails.getAskPrice());
		bookTicker.setSpread(bookTickerDetails.getAskPrice() - bookTickerDetails.getBidPrice());
		bookTicker.setPercentageSpread((bookTickerDetails.getAskPrice() - bookTickerDetails.getBidPrice())/bookTickerDetails.getAskPrice()*100);
	    return bookTickerRepository.save(bookTicker);
  	}
  
  	@GetMapping("/getBestPrice/{symbol}")
	public Optional<BookTicker> getBestPriceForSymbol(@PathVariable String symbol) {
		return bookTickerService.getBestPriceForSymbol(symbol.toUpperCase());
	}
  	
  	@GetMapping("/getBestPrice/{source}")
	public Optional<BookTicker> getBestPriceInSource(@PathVariable String source) {
  		return bookTickerService.getBestPriceInSource(source.toUpperCase());
	}
  	
  	
}