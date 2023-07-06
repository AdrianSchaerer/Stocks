package ch.zhaw.oop.stocks.service.implementation;

import ch.zhaw.oop.stocks.model.Stocks;

import ch.zhaw.oop.stocks.repo.StocksRepo;
import ch.zhaw.oop.stocks.service.StocksService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor // ADR: Lombok annotation to create a constructor with all required fields
@Service // ADR: Spring annotation to create a service
@Transactional // ADR: Spring annotation to create a transactional service
@Slf4j // ADR: Lombok annotation to create a logger
public class StocksServiceImpl implements StocksService {
    private final StocksRepo stocksRepo;
    @Override
    public Stocks create(Stocks stocks) {
        log.info("Creating new stock {}", stocks.getStockName());
        return stocksRepo.save(stocks);
    }

    @Override
    public Collection<Stocks> list(int limit) {
        log.info("Fetching all Stock entries");
        return stocksRepo.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Stocks read(Long id) {
        log.info("Fetching stock with id {}", id);
        return stocksRepo.findById(id).get();
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting stock by ID {}", id);
        stocksRepo.deleteById(id);
        return Boolean.TRUE;
    }
}
