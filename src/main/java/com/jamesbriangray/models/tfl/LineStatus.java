package com.jamesbriangray.models.tfl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LineStatus {
    private final int statusSeverity;
    private final String statusSeverityDescription;
}
