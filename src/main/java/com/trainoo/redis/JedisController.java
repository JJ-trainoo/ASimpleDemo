package com.trainoo.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/redis")
public class JedisController {
	
	@Value("${REDIS_ITEM}")
	private String REDIS_ITEM;
	
	@Value("${REDIS_ITEM_EXPIRE}")
	private int REDIS_ITEM_EXPIRE;
	
	@Autowired
	private JedisClient jedisClient;

	@RequestMapping("/jedisSet.do")
	@ResponseBody
	public String setJedis(){
		jedisClient.set(REDIS_ITEM, "test redis");
		jedisClient.expire(REDIS_ITEM, REDIS_ITEM_EXPIRE);
		System.out.println(REDIS_ITEM+","+REDIS_ITEM_EXPIRE);
		return "{\"count\":\"0\"}";
	}
	
	@RequestMapping("/jedisGet.do")
	@ResponseBody
	public String getJedis(){
		String result = jedisClient.get(REDIS_ITEM);
		System.out.println(result);
		return "{\"count\":\""+REDIS_ITEM+" : "+result+"\"}";
	}
	
}
