package com.trainoo.activemq.producer;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/jms")
public class QueueController extends QueueSender{

	@Autowired
	private QueueSender queue;
	
	/**
	 * 向指定队列发送消息
	 * 定时任务，class需要加@Component,去掉@Controller,@RequestMapping等
	 */
	@Scheduled(cron="0/3 * * * * ?")
	public void sendMessage() {
		queue.sendQueueMessage("test.queue", System.currentTimeMillis()+"");
	}
	
	@RequestMapping("/activemq.do")
	@ResponseBody
	public String testActiveMQ() {
		String[] str = {"你好~", "Hello~", "bonjour!", "Guten Tag!", "こんにちは~"};
		int index = RandomUtils.nextInt(0, 5);
		queue.sendQueueMessage("test.queue", str[index]);
		//queue.sendTopicMessage("test.queue", str[index]);
		return "{\"code\":\"发送消息成功~\"}";
	}
}
