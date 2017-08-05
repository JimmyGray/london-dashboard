package com.jamesbriangray.models.twitter;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Value
@RequiredArgsConstructor
@Builder(toBuilder = true)
public class TwitterTrend {
    private final String name;
    private final String url;
    private final String query;
    private final String lastUpdated = LocalDateTime.now(ZoneId.of("GMT")).toString();;
}
