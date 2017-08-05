package com.jamesbriangray.models.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Value;

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
    private final int id;
    private final String main;
    private final String description;
    private final String icon;
}
