package com.wumugulu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableCaching
// maxInactiveIntervalInSeconds: 设置Session失效时间;
// 使用Redis Session之后，原SpringBoot的server.session.timeout属性不再生效
@EnableRedisHttpSession(redisNamespace="SpringBootSession", maxInactiveIntervalInSeconds=120)
public class Chapter06Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Chapter06Application.class, args);
	}
	
}
