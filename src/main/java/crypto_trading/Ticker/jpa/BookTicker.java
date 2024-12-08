package crypto_trading.Ticker.jpa;
import java.io.IOException;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

@JsonDeserialize(using = BookTickerDeserializer.class)
@Entity
@IdClass(TickerIdClass.class)
public class BookTicker {

    //@Id
    //@GeneratedValue
    //private Long id;
	
	@Id
    private String symbol;
	
	@Id
	private String source;
    
    @JsonAlias("bid")
    private Double bidPrice;
    
    @JsonAlias("bidSize")
    private Double bidQty;
    
    @JsonAlias("ask")
    private Double askPrice;
    
    @JsonAlias("askSize")
    private Double askQty;
    
    private Double spread;
    
    private Double percentageSpread;

    public BookTicker() {
        super();
    }

    public BookTicker(String source, String symbol, Double bidPrice, Double bidQty, 
    		Double askPrice, Double askQty) {
        super();
        //this.id = id;
        this.setSymbol(symbol);
        this.setBidPrice(bidPrice);
        this.setBidQty(bidQty);
        this.setAskPrice(askPrice);
        this.setAskQty(askQty);
        this.setSource(source);
    }

    public BookTicker(String symbol, Double bidPrice, Double bidQty, 
    		Double askPrice, Double askQty) {
        super();
        
        this.setSymbol(symbol);
        this.setBidPrice(bidPrice);
        this.setBidQty(bidQty);
        this.setAskPrice(askPrice);
        this.setAskQty(askQty);
    }

    @Override
    public String toString() {
        return String.format("BookTicker [symbol=%s, bidPrice=%s, bidQty=%s, askPrice=%s, askQty=%s]", 
        		symbol, bidPrice, bidQty, askPrice, askQty);
    }

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Double getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(Double bidPrice) {
		this.bidPrice = bidPrice;
	}

	public Double getBidQty() {
		return bidQty;
	}

	public void setBidQty(Double bidQty) {
		this.bidQty = bidQty;
	}

	public Double getAskPrice() {
		return askPrice;
	}

	public void setAskPrice(Double askPrice) {
		this.askPrice = askPrice;
	}

	public Double getAskQty() {
		return askQty;
	}

	public void setAskQty(Double askQty) {
		this.askQty = askQty;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Double getSpread() {
		return spread;
	}

	public void setSpread(Double spread) {
		this.spread = spread;
	}

	public Double getPercentageSpread() {
		return percentageSpread;
	}

	public void setPercentageSpread(Double percentageSpread) {
		this.percentageSpread = percentageSpread;
	}
}

class TickerIdClass implements Serializable {

    private String symbol;
    private String source;
}

class BookTickerDeserializer extends StdDeserializer<BookTicker> { 

    /**
	 * 
	 */
	private static final long serialVersionUID = 3413021533492339304L;

	public BookTickerDeserializer() { 
        this(null); 
    } 

    public BookTickerDeserializer(Class<?> bt) { 
        super(bt); 
    }

    @Override
    public BookTicker deserialize(JsonParser jp, DeserializationContext ctxt) 
      throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        //int id = (Integer) ((IntNode) node.get("id")).numberValue();
        String symbol = node.get("symbol").asText();
        double bidPrice = Double.parseDouble(node.get("bidPrice") != null ? node.get("bidPrice").asText() : node.get("bid").asText());
        double bidQty = Double.parseDouble(node.get("bidQty") != null ? node.get("bidQty").asText() : node.get("bidSize").asText());
        double askPrice = Double.parseDouble(node.get("askPrice") != null ? node.get("askPrice").asText() : node.get("ask").asText());
        double askQty = Double.parseDouble(node.get("askQty") != null ? node.get("askQty").asText() : node.get("askSize").asText());
        return new BookTicker(symbol, bidPrice, bidQty, askPrice, askQty);
    }
}
