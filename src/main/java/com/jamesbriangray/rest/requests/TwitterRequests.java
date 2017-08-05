package com.jamesbriangray.rest.requests;

import com.google.common.util.concurrent.Callables;
import lombok.SneakyThrows;
import com.jamesbriangray.models.twitter.TwitterTrend;
import org.springframework.stereotype.Component;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

@Component
public class TwitterRequests {

    private final String CONSUMER_KEY = "Fzq218wJ0f1lGCm4cpMwQWNIZ";
    private final String CONSUMER_SECRET = "q0FtJEZW1FDHIbSlsEBjs2ZLpPjMIyo1wEgtzDBN1XlPHXMgxL";
    private final String ACCESS_TOKEN = "262589512-qUFd6agE7qIYYWlDErArv18UhOWzlDAVvwPvEDfY";
    private final String ACCESS_TOKEN_SECRET = "NjVK2hFpsyvTYKojLr7UCfHHkP57yEL4VWzf58IST7a48";
    private final int LONDON_WOEID = 44418;

    @SneakyThrows
    public Callable<List<?>> returnLondonTwitterTrends() {
        return Callables.returning(returnTwitterTrendFromTrends(returnTwitterQueryInstance().getPlaceTrends(LONDON_WOEID)));
    }

    private List<TwitterTrend> returnTwitterTrendFromTrends(Trends trends) {
        return Arrays.stream(trends.getTrends())
                .map(trend -> TwitterTrend.builder()
                    .name(trend.getName())
                    .url(trend.getURL())
                    .query(trend.getQuery())
                    .build())
                .collect(Collectors.toList());
    }

    private Twitter returnTwitterQueryInstance() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(CONSUMER_KEY)
                .setOAuthConsumerSecret(CONSUMER_SECRET)
                .setOAuthAccessToken(ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);
        TwitterFactory tf = new TwitterFactory(cb.build());
        return tf.getInstance();
    }
}
