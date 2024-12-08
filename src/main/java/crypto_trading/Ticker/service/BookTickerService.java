package crypto_trading.Ticker.service;

import java.util.List;
import java.util.Optional;

import crypto_trading.Ticker.jpa.BookTicker;

public interface BookTickerService {
  void saveAll(List <BookTicker> bookTickerList);
  List <BookTicker> getAll();
  void fetchDataFromApi(String source);
  Optional<BookTicker> getBestPriceForSymbol(String symbol);
  Optional<BookTicker> getBestPriceInSource(String source);
}