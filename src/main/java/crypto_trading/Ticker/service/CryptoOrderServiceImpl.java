package crypto_trading.Ticker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crypto_trading.Ticker.dto.OrderDetail;
import crypto_trading.Ticker.jpa.CryptoOrder;
import crypto_trading.Ticker.jpa.CryptoOrderRepository;

@Service
public class CryptoOrderServiceImpl implements CryptoOrderService {
	@Autowired
	private CryptoOrderRepository cryptoRepo;
	
	public CryptoOrder createNewOrder(OrderDetail newOrder) {
		CryptoOrder cryptoOrder = new CryptoOrder();
		cryptoOrder.setSymbol(newOrder.getSymbol());
		cryptoOrder.setUserId(newOrder.getUserId());
		cryptoOrder.setBuyQty(newOrder.getBuyQty());
		cryptoOrder.setSellQty(newOrder.getSellQty());
		cryptoOrder.setSellPrice(newOrder.getSellPrice());
		cryptoOrder.setBuyPrice(newOrder.getBuyPrice());
		return cryptoRepo.save(cryptoOrder);
	}
}
