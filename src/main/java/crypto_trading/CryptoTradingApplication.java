package crypto_trading;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import crypto_trading.Ticker.jpa.BookTickerRepository;
import crypto_trading.Ticker.jpa.CryptoUser;
import crypto_trading.Ticker.jpa.CryptoUserRepository;



@SpringBootApplication
@EnableScheduling
public class CryptoTradingApplication implements CommandLineRunner{

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	//@Autowired
    //BookTickerService service;
	
	@Autowired
	CryptoUserRepository userRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(CryptoTradingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//service.fetchDataFromApi(Constants.BINANCE);
		//service.fetchDataFromApi(Constants.HOUBI);
		
        LOGGER.info("Inserting -> {}", userRepo.save(new CryptoUser("User1", "SGD", 0.00)));
        LOGGER.info("Inserting -> {}", userRepo.save(new CryptoUser("User2", "SGD", 0.00)));
        LOGGER.info("Inserting -> {}", userRepo.save(new CryptoUser("User3", "SGD", 0.00)));
	}
	
	public class AppConstants { 
	    public static final String BINANCE = "Binance"; 
	    public static final String HOUBI = "Houbi"; 
	} 

}
