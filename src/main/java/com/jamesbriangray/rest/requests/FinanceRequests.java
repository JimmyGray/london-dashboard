package com.jamesbriangray.rest.requests;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Callables;
import lombok.SneakyThrows;
import com.jamesbriangray.models.finance.StockUpdate;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.concurrent.Callable;

@Component
public class FinanceRequests {

    private final String FTSE_100 = "^FTSE";

    @SneakyThrows
    public Callable<List<?>> returnFTSEStock() {
        return Callables.returning(Lists.newArrayList(returnStockUpdateFromStock(YahooFinance.get(FTSE_100))));
    }

    private StockUpdate returnStockUpdateFromStock(Stock stock) {
        return StockUpdate.builder()
                .name(stock.getName())
                .price(stock.getQuote().getPrice())
                .open(stock.getQuote().getOpen())
                .prevClose(stock.getQuote().getPreviousClose())
                .change(stock.getQuote().getChange())
                .build();
    }
}
