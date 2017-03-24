package com.trainoo.quartz;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * springMVC定时器任务
 * 
 * 注解形式  或  xml中配置
 *
 * @author zhout
 * @date 2017年3月24日
 */

//@Component
public class ScheduledDemo {

	//@Scheduled(cron="0/1 * * * * ?")
	public void taskJobA(){
		System.out.println("A runnig...");
	}
	
	//@Scheduled(cron="0/5 * * * * ?")
	public void taskJobB(){
		System.out.println("B runnig...");
	}
}
