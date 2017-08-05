package com.jamesbriangray.rest.caching;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import com.jamesbriangray.config.EventHandler;
import com.jamesbriangray.rest.requests.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ApiCacheManager {

    private static final long CACHE_TIMER = 5000L;
    private final ImmutableList<String> CACHE_KEYS = ImmutableList.copyOf(getApiCallsList());
    private final ExecutorService EXECUTOR = Executors.newFixedThreadPool(20);
    private final EventHandler eventHandler;
    private final TflRequests tflRequests = new TflRequests();
    private final WeatherRequests weatherRequests = new WeatherRequests();
    private final FinanceRequests financeRequests = new FinanceRequests();
    private final NewsRequests newsRequests = new NewsRequests();
    private final TwitterRequests twitterRequests = new TwitterRequests();

    @Getter
    private final LoadingCache<String, List<?>> loadingCache = initializeLoadingCache();

    private LoadingCache<String, List<?>> initializeLoadingCache() {
        return CacheBuilder.newBuilder()
                .refreshAfterWrite(2, TimeUnit.MINUTES)
                .build(getConfiguredAsyncCacheLoader());
    }

    private List<?> getApiCall(String key) {
        switch (key) {
            case "financeRequest":
                return processRequest(key, financeRequests.returnFTSEStock());
            case "newsRequest":
                return processRequest(key, newsRequests.returnLondonNews());
            case "tflTubeStatusRequest":
                return processRequest(key, tflRequests.retrieveTubeStatus());
            case "tflTrainStatusRequest":
                return processRequest(key, tflRequests.retrieveNationRailWayStatus());
            case "twitterRequest":
                return processRequest(key, twitterRequests.returnLondonTwitterTrends());
            case "weatherRequest":
                return processRequest(key, weatherRequests.retrieveLondonCityWeather());
            default:
                throw new IllegalArgumentException();
        }
    }

    @SneakyThrows
    private List<?> processRequest(String apiCallType, Callable<List<?>> apiRequest) {
        List<?> result = apiRequest.call();
        eventHandler.cacheUpdate(apiCallType, result);
        return result;
    }

    private List<String> getApiCallsList() {
        return Lists.newArrayList(
                "financeRequest",
                "newsRequest",
                "tflTubeStatusRequest",
                "tflTrainStatusRequest",
                "twitterRequest",
                "weatherRequest"
        );
    }

    @SneakyThrows
    @Scheduled(fixedDelay = CACHE_TIMER)
    public void queryEndPointsCache() {
        CACHE_KEYS.stream()
                .forEach(loadingCache::getUnchecked);
    }

    public void populateCacheWithDefaultValues() {
        List<String> defaultList = Lists.newArrayList();
        defaultList.add("No Data");
        CACHE_KEYS.stream()
                .forEach(entry -> loadingCache.put(entry, defaultList));
    }

    private CacheLoader<String, List<?>> getConfiguredAsyncCacheLoader() {
        return new CacheLoader<String, List<?>>() {
            @Override
            public ListenableFuture<List<?>> reload(final String key, final List<?> value) {
                ListenableFutureTask<List<?>> cacheKeyLoadingTask = ListenableFutureTask.create(
                        () -> load(key)
                );
                EXECUTOR.submit(cacheKeyLoadingTask);
                return cacheKeyLoadingTask;
            }

            @Override
            public List<?> load(String key) {
                return getApiCall(key);
            }
        };
    }
}
