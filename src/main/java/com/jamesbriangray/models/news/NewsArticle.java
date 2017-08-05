package com.jamesbriangray.models.news;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;


@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsArticle {
    private final String author;
    private final String title;
    private final String description;
    private final String url;
    private final String urlToImage;
}
