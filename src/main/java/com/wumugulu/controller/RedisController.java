package com.wumugulu.controller;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wumugulu.entity.Book;

@Controller
@RequestMapping(value="/redis")
public class RedisController {

	// 由于我们引入了redis的包，并且配置了redis的相关参数
	// 所以在springboot项目中直接注入就可以使用了，不需要配置xml，也不需要new对象，get/set什么的
	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	// post-从前端请求中获取参数，并保存String到redis
	@RequestMapping(value="/{key}", method=RequestMethod.POST)
	@ResponseBody
	// @Cacheable(value="testCache", key="#key+'-test4String'")
	public String setRedisString(@PathVariable String key, String value){
		System.out.println("got parameter redisKey = " + key);
		System.out.println("got parameter redisValue = " + value);
		redisTemplate.opsForValue().set(key, value);
		return "success";
	}

	// get-从redis查询String，并返回给前端显示
	@RequestMapping(value="/{key}", method=RequestMethod.GET)
	@ResponseBody
	public String getRedisString(@PathVariable String key){
		System.out.println("got parameter redisKey = " + key);
		String value = (String) redisTemplate.opsForValue().get(key);
		return value;
	}

	// post-保存Object到redis
	@RequestMapping(value="/object/{key}", method=RequestMethod.POST)
	@ResponseBody
	// @Cacheable(value="testCache", key="#key+'-test4Object'")
	public String setRedisObject(@PathVariable String key, String value){
		System.out.println("got parameter redisKey = " + key);
		System.out.println("got parameter redisValue = " + value);
		Book book = new Book();
		book.setId(99);
		book.setName("SpringBoot从小白到大神");
		book.setAuthor("乌木轱辘");
		redisTemplate.opsForValue().set(key, book);
		return "success";
	}

	// get-从redis查询Object
	@RequestMapping(value="/object/{key}", method=RequestMethod.GET)
	@ResponseBody
	public Book getRedisObject(@PathVariable String key){
		System.out.println("got parameter redisKey = " + key);
		Book book = (Book) redisTemplate.opsForValue().get(key);
		return book;
	}

}
