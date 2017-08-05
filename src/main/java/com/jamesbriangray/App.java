package com.jamesbriangray;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.jamesbriangray.rest.caching.ApiCacheManager;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class App implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    ApiCacheManager apiCacheManager;

    @SneakyThrows
    public static void main(String[] args) {
        new SpringApplicationBuilder(App.class).run(args);
    }

    @SneakyThrows
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        apiCacheManager.populateCacheWithDefaultValues();
    }
}