package crypto_trading.Ticker.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookTickerRepository extends JpaRepository<BookTicker, Long> {
	BookTicker findBySymbolAndSource(String symbol, String source);
	List<BookTicker> findBySymbol(String symbol);
	List<BookTicker> findBySource(String source);
}
