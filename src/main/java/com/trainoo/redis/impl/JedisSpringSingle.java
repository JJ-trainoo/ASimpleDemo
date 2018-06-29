package com.trainoo.redis.impl;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;

import com.trainoo.redis.JedisClient;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

public class JedisSpringSingle implements JedisClient{

	/**
	 * 使用是spring的redis工厂获取连接
	 */
	@Autowired
    protected JedisConnectionFactory jedisConnectionFactory;

	
	@Override
	public String get(String key) {
		RedisConnection connect = jedisConnectionFactory.getConnection();
		String string = null;
		try {
			string = new String(connect.get(key.getBytes()), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		connect.close();
		return string;
	}

	@Override
	public String set(String key, String value) {
		RedisConnection connect = jedisConnectionFactory.getConnection();
		connect.set(key.getBytes(), value.getBytes());;
		connect.close();
		return "";
	}


	@Override
	public long del(String key) {
		RedisConnection connect = jedisConnectionFactory.getConnection();
		Long result = connect.del(key.getBytes());
		connect.close();
		return result;
	}

	@Override
	public String hGet(String hkey, String key) {
		RedisConnection connect = jedisConnectionFactory.getConnection();
		String result = null;
		try {
			result = new String(connect.hGet(key.getBytes(), key.getBytes()),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		connect.close();
		return result;
	}

	@Override
	public long hSet(String hkey, String key, String value) {
		RedisConnection connect = jedisConnectionFactory.getConnection();
		connect.hSet(hkey.getBytes(), key.getBytes(), value.getBytes());
		connect.close();
		return 0;
	}

	@Override
	public long incr(String key) {
		RedisConnection connect = jedisConnectionFactory.getConnection();
		connect.incr(key.getBytes());
		connect.close();
		return 0;
	}

	@Override
	public long expire(String key, int seconds) {
		RedisConnection connect = jedisConnectionFactory.getConnection();
		connect.expire(key.getBytes(), seconds);
		connect.close();
		return 0;
	}

	@Override
	public long ttl(String key) {
		RedisConnection connect = jedisConnectionFactory.getConnection();
		connect.ttl(key.getBytes());
		connect.close();
		return 0;
	}

	@Override
	public long hdel(String hkey, String key) {
		RedisConnection connect = jedisConnectionFactory.getConnection();
		connect.hDel(hkey.getBytes(), key.getBytes());
		connect.close();
		return 0;
	}

}
