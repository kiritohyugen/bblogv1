package com.example.blog.config;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class CacheConfig {

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        // Set a default TTL (e.g., 5 minutes) for caches, but allow dynamic TTL in logic
        RedisCacheConfiguration defaultConfig = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(5)) // Default expiration
                .disableCachingNullValues() // Avoid caching null values
                .serializeKeysWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer())); // Key serializer

        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(defaultConfig)
                .transactionAware()
                .build();
    }
    
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(redisConnectionFactory);
//        template.setKeySerializer(new StringRedisSerializer());  // Key serializer
//        template.setValueSerializer(new GenericToStringSerializer<>(Object.class));  // Value serializer
//        return template;
//    }
}
