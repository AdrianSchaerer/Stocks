package ch.zhaw.oop.stocks.repo;

import ch.zhaw.oop.stocks.model.Stocks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StocksRepo extends JpaRepository<Stocks, Long> {
    Stocks findByStockName(String stocksName);
}
