package crypto_trading.Ticker.dto;

public class OrderDetail {
	private Long userId;
	
	private String symbol;
	
	private Double sellQty;
	
	private Double buyQty;
	
	private Double sellPrice;
	
	private Double buyPrice;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Double getSellQty() {
		return sellQty;
	}

	public void setSellQty(Double sellQty) {
		this.sellQty = sellQty;
	}

	public Double getBuyQty() {
		return buyQty;
	}

	public void setBuyQty(Double buyQty) {
		this.buyQty = buyQty;
	}

	public Double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public Double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}
}
