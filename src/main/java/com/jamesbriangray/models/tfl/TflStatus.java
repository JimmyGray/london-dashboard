package com.jamesbriangray.models.tfl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Data
@RequiredArgsConstructor
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TflStatus {
    private final String id;
    private final String name;
    private final String modeName;
    private final List<LineStatus> lineStatuses;
    private final String lastUpdated = LocalDateTime.now(ZoneId.of("GMT")).toString();
}
