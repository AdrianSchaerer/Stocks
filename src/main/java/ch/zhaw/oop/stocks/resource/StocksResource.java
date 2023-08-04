package ch.zhaw.oop.stocks.resource;

import ch.zhaw.oop.stocks.model.Response;
import ch.zhaw.oop.stocks.model.Stocks;
import ch.zhaw.oop.stocks.service.implementation.StocksServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static java.util.Map.*;

@RestController
@RequestMapping("/stocksNew")
@RequiredArgsConstructor
public class StocksResource {
    private final StocksServiceImpl stocksService;

    @GetMapping("/list")
    public ResponseEntity<Response> getStocks() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(of("stocks", stocksService.list(10)))
                        .message("Stocks fetched successfully")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @PostMapping("/save")
    public ResponseEntity<Response> saveStocks(@RequestBody @Valid Stocks stocks) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(of("stocks", stocksService.create(stocks)))
                        .message("Stocks fetched successfully")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getStocks(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(of("stocks", stocksService.read(id)))
                        .message("Stock retrieved successfully")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteStocks(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(of("deleted", stocksService.delete(id)))
                        .message("Stock deleted successfully")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }
}
