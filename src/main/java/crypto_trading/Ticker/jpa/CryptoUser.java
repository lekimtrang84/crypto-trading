package crypto_trading.Ticker.jpa;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class CryptoUser {
	@Id
    @GeneratedValue
    private Long userId;
	
	private String name;
	
	private Double walletBalance;
	
	private String cryptoCurrency;
	
	public CryptoUser() {
        super();
    }
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "userId")
    private List<CryptoOrder> orders;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


	public Double getWalletBalance() {
		return walletBalance;
	}

	public void setWalletBalance(Double walletBalance) {
		this.walletBalance = walletBalance;
	}

	public String getCryptoCurrency() {
		return cryptoCurrency;
	}

	public void setCryptoCurrency(String cryptoCurrency) {
		this.cryptoCurrency = cryptoCurrency;
	}
	
	public CryptoUser(Long userId, String name, String cryptoCurrency, Double walletBalance) {
        super();
        this.setUserId(userId);
        this.setName(name);
        this.setCryptoCurrency(cryptoCurrency);
        this.setWalletBalance(walletBalance);
    }
	
	public CryptoUser(String name, String cryptoCurrency, Double walletBalance) {
        super();
        this.setName(name);
        this.setCryptoCurrency(cryptoCurrency);
        this.setWalletBalance(walletBalance);
    }
}
