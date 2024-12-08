package crypto_trading;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import crypto_trading.Ticker.jpa.BookTicker;
import crypto_trading.Ticker.jpa.BookTickerRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@DataJpaTest
public class HoubiApiTest extends AbstractTransactionalTestNGSpringContextTests {
	
	@Autowired
	private BookTickerRepository bookTickerRepo;
	
	@Test
	@Commit
	public void testGet() {
	    RestAssured
	        .get("https://api.huobi.pro/market/tickers")
	    .then()
	    .assertThat()
	    .statusCode(200)
	    .and()
	    .contentType(ContentType.JSON);
	    
	    RestAssured.baseURI = "https://api.huobi.pro/market/tickers";
	    RequestSpecification httpRequest = RestAssured.given();
	    Response response = httpRequest.request(Method.GET, "");
	    System.out.println("\nSTATUS RECEIVED => \n" + response.getStatusLine());
	    
	    String responseBody = response.getBody().asString();
	    System.out.println("\nRESPOND BODY => \n" + responseBody);
	    /*
	    ObjectMapper mapper = new ObjectMapper();
	    List<BookTicker> bookTickerList = new ArrayList<BookTicker>();
	    try {
			JsonNode jsonNode = mapper.readTree(responseBody);
			responseBody = jsonNode.get("data").toString();
		    
		    bookTickerList = mapper.readValue(responseBody, new TypeReference<List<BookTicker>>(){});
		    bookTickerRepo.saveAll(bookTickerList);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		*/
	}
}
