package com.trainoo.dubbo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试dubbo:
 * comsumer只需要有两个文件:DubboConsumer.java\DubboService.java
 * 它是通过DubboService去调用DubboProvider里面是实现类的
 * 
 * 如果传递的对象是一个实体对象，那么，实体对象在comsumer跟provider里面要有完全相同的包名
 * 并实现序列化接口
 *
 * @author zhout
 * @date 2017年4月6日
 */
public class DubboConsumer {

	public static void main(String[] args) throws InterruptedException {
	
		String configLocation = "dubbo/dubbo-consumer.xml";
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configLocation);
		context.start();
		
		DubboService ds = (DubboService) context.getBean("dubboService");
		ds.sayHello("zhoutao");
		System.out.println(ds.returnHello());
		
		MsgInfo info = new MsgInfo();
		List<String> msgs = new ArrayList<String>();
		msgs.add("list1");
		msgs.add("list2");
		info.setId(001);
		info.setName("msg001");
		info.setMsgs(msgs);
		System.out.println(ds.returnMsgInfo(info));

	}

}
