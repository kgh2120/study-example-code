package org.kk.cachesync.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@EnableCaching
@Configuration
public class CacheConfig {


    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        List<CaffeineCache> caches =
                Arrays.stream(CacheType.values())
                        .map(
                                cache ->
                                        new CaffeineCache(
                                                cache.getName(),
                                                Caffeine.newBuilder()
                                                        .expireAfterWrite(cache.getExpireAfterWrite(), TimeUnit.SECONDS)
                                                        .maximumSize(cache.getMaximumSize())
                                                        .recordStats()
                                                        .build()))
                        .toList();

        cacheManager.setCaches(caches);
        return cacheManager;
    }
}
