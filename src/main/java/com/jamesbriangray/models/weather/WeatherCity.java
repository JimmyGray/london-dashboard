package com.jamesbriangray.models.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Value
@RequiredArgsConstructor
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherCity {
    private final int id;
    private final String name;
    private final WeatherCityStatistics main;
    private final List<Weather> weather;
    private final String lastUpdated = LocalDateTime.now(ZoneId.of("GMT")).toString();
    ;
}
