package crypto_trading.Ticker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import crypto_trading.CryptoTradingApplication.AppConstants;


@Service
public class ScheduledTask {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    BookTickerService service;
		
	
	@Scheduled(fixedRate = 10000)
	public void runTask() {
		LOGGER.info("\n\nCRONJOB EXECUTED AT " + System.currentTimeMillis() +"\n");
		
		service.fetchDataFromApi(AppConstants.BINANCE);
		service.fetchDataFromApi(AppConstants.HOUBI);
	}
	
}
