package com.jamesbriangray.rest.requests;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Callables;
import com.jamesbriangray.models.news.News;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import yahoofinance.YahooFinance;

import java.util.List;
import java.util.concurrent.Callable;

@Component
public class NewsRequests {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URL = "https://newsapi.org/v1/articles?source=";
    private final String API_KEY = "&apiKey=c89fd31b766a4e72be09134a185d7db3";
    private final String BBC_URL = BASE_URL + "bbc-news&sortBy=top" + API_KEY;

    @SneakyThrows
    public Callable<List<?>> returnLondonNews() {
        return Callables.returning(Lists.newArrayList(restTemplate.getForObject(BBC_URL, News.class)));
    }
}

