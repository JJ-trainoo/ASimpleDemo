package com.trainoo.spring;

import java.util.List;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.trainoo.druid.DruidService;

public class TestSpring {

	public void test() {
		// 从Ioc容器获得对象
		// 1、 获取Ioc容器工厂对象
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("mybatis/applicationContext1.xml");
		// 2、 从Ioc容器工厂 获取需要对象 (根据bean的id 获取)
		// 使用注解注入的，注解在实现类里面，所以这里拿实现类bean
		DruidService druidService = (DruidService) applicationContext.getBean("druidServiceImpl");
		List<Map<Integer, String>> list = druidService.queryDetail();
		for (Map<Integer, String> map : list){
			System.out.println(map);
		}
		applicationContext.close();
	}
	
	public static void main(String[] args) {
		new TestSpring().test();
	}
}
