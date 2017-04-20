package com.trainoo.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DubboProvider {
	
	public static void main(String[] args) throws Exception {
			
		String configLocation = "dubbo/dubbo-provider.xml";
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configLocation);
		context.start();
		//阻塞，保持程序运行
		System.in.read();
		
	}
}
