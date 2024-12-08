package crypto_trading.Ticker.service;

import crypto_trading.Ticker.dto.OrderDetail;
import crypto_trading.Ticker.jpa.CryptoOrder;

public interface CryptoOrderService {
	public CryptoOrder createNewOrder(OrderDetail newOrder); 
}
