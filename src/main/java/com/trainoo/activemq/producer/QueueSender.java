package com.trainoo.activemq.producer;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class QueueSender {

	@Resource(name = "jmsQueueTemplate")
	private JmsTemplate jmsQueueTemplate;
	
	@Resource(name = "jmsTopicTemplate")
	private JmsTemplate jmsTopicTemplate;

	/**
	 * 向指定队列发送消息
	 */
	public void sendQueueMessage(String destination, final String msg) {
		System.out.println("向队列[" + destination + "]发送了消息：" + msg);
		jmsQueueTemplate.send(destination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(msg);
			}
		});
	}
	
	/**
	 * 发送主题消息
	 */
	public void sendTopicMessage(String destination, final String msg) {
		System.out.println("向队列[" + destination + "]发送了topic消息：" + msg);
		jmsTopicTemplate.send(destination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(msg);
			}
		});
	}
}
