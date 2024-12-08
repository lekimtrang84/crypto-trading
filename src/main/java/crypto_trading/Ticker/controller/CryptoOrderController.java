package crypto_trading.Ticker.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crypto_trading.Ticker.dto.OrderDetail;
import crypto_trading.Ticker.jpa.CryptoOrder;
import crypto_trading.Ticker.service.CryptoOrderService;
import crypto_trading.Ticker.service.CryptoOrderServiceImpl;
import crypto_trading.Ticker.service.CryptoUserService;
import crypto_trading.Ticker.service.CryptoUserServiceImpl;

@RestController
@RequestMapping("/api/order")
public class CryptoOrderController {
	@Autowired
	private CryptoUserService userService;
	
	@Autowired
	private CryptoOrderService orderService;
	
	@Autowired
    public CryptoOrderController(CryptoUserServiceImpl userService, CryptoOrderServiceImpl orderService){
        this.userService = userService;
        this.orderService = orderService;
    }
	
	@GetMapping("/sell")
	public CryptoOrder sellOrder(@RequestBody OrderDetail orderDetail) {
		return(orderService.createNewOrder(orderDetail));
	}
	
	@GetMapping("/buy")
	public CryptoOrder buyOrder(@RequestBody OrderDetail orderDetail) {
		return(orderService.createNewOrder(orderDetail));
	}
}
