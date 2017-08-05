package com.jamesbriangray.rest.requests;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Callables;
import com.jamesbriangray.models.weather.WeatherCity;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.Callable;

@Component
public class WeatherRequests {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?id  =";
    private final String API_KEY = "&appid=bc5ba0743164d6e55060d3c78fde5442";
    private final String LONDON_CODE = "2643741";
    private final List<String> OPTIONS = Lists.newArrayList("&units=metric&");
    private final String LONDON_QUERY = BASE_URL + LONDON_CODE + OPTIONS.toString() + API_KEY;

    @SneakyThrows
    public Callable<List<?>> retrieveLondonCityWeather() {
        return Callables.returning(Lists.newArrayList(restTemplate.getForObject(LONDON_QUERY, WeatherCity.class)));
    }
}


