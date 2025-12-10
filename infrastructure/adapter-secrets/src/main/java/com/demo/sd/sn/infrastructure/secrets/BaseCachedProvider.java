package com.demo.sd.sn.infrastructure.secrets;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.time.Duration;

abstract class BaseCachedProvider {

    protected final Cache<String, String> cache =
            Caffeine.newBuilder()
                    .maximumSize(100)
                    .expireAfterWrite(Duration.ofMinutes(5))
                    .build();

    protected String fromEnv(String key) {
        return System.getenv(key);
    }
}
