package crypto_trading.Ticker.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoOrderRepository extends JpaRepository<CryptoOrder, Long>{
	Optional<CryptoOrder> findById(Long orderId);
	
}
