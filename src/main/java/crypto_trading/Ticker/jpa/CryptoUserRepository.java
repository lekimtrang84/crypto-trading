package crypto_trading.Ticker.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoUserRepository extends JpaRepository<CryptoUser, Long>{
	Optional<CryptoUser> findById(Long userId);
}
