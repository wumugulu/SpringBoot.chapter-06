package com.wumugulu.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisCacheConfig {
	
	@Bean
	public CacheManager cacheManager(RedisTemplate<Object, Object> redisTemplate){
		RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
		// 设置cache过期时间,时间单位是秒  
		redisCacheManager.setDefaultExpiration(3600);
		Map<String, Long> mapTimeout = new HashMap<>();
		mapTimeout.put("testCache", 120L);
		redisCacheManager.setExpires(mapTimeout);
		return redisCacheManager;
	}

	@Bean
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory){
		RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
		template.setConnectionFactory(factory);
		// 可选的序列化策略4中：JdkSerializationRedisSerializer，
		// StringRedisSerializer，Jackson2JsonRedisSerializer，OxmSerializer
		template.setKeySerializer(new StringRedisSerializer());
		// 这种方式需要指定类名，扩展性不够强，所以使用了后面自定义的策略
		// template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Book.class));
		template.setValueSerializer(new ObjectRedisSerializer());

		return template;
	}
	
}
