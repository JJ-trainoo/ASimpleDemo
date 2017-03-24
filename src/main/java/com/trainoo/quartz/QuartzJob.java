package com.trainoo.quartz;

import java.util.Date;

/**
 * 使用quartz2配置定时器任务，配置见：applicationContext.xml 定时器配置方式二。
 * 使用时注意spring跟 quartz版本，否者会报错
 * 
 * class not found CronTriggerBean 
 * 原因是: 
 * 	Spring 3.0版本中内置的Quartz版本是<2.0的，在使用最新的Quartz包(>2.0)之后，接口不兼容。
 * 解决办法有两种： 
 *	1.降低Quartz版本，降到1.X去。
 *	2.升级Spring版本到3.1+，根据Spring的建议，将原来的**TriggerBean替换成**TriggerFactoryBean，
 *	    例如CronTriggerBean 就可以替换成 CronTriggerFactoryBean。替换之后问题解决。
 * 
 * @author zhout
 * @date 2017年3月24日
 */
public class QuartzJob {

	private static int count = 0;

	public void execute() {
		System.out.println(new Date().getTime());
		System.out.println(count++);
	}
}
