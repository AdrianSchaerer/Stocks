package ch.zhaw.oop.stocks.service;

import ch.zhaw.oop.stocks.model.Stocks;

import java.util.Collection;

public interface StocksService {
    Stocks create(Stocks stock);
    Collection<Stocks> list(int limit);
    Stocks read(Long id);
    Boolean delete(Long id);

}
