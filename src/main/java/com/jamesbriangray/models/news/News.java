package com.jamesbriangray.models.news;

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
public class News {
    private final String source;
    private final List<NewsArticle> articles;
    private final String lastUpdated = LocalDateTime.now(ZoneId.of("GMT")).toString();;
}
