package org.kk.cachesync.service;

import lombok.RequiredArgsConstructor;
import org.kk.cachesync.dto.CacheEvictMessage;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CacheEvictService {

    private final CacheManager cacheManager;


    public void evictCache(CacheEvictMessage cacheEvictMessage){

        Cache cache = cacheManager.getCache(cacheEvictMessage.getCacheName());
        if(cache == null)
            return;

        cache.evict(cacheEvictMessage.getKey());
    }
}
