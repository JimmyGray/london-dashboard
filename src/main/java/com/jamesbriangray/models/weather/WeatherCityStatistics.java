package com.jamesbriangray.models.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Value;

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherCityStatistics {
    private final String temp;
    private final String pressure;
    private final String humidity;
    private final String temp_min;
    private final String temp_max;
}
