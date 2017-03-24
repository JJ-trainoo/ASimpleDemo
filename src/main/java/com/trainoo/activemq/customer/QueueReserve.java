package com.trainoo.activemq.customer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

@Component
public class QueueReserve implements MessageListener {

	@Override
	public void onMessage(Message message) {

		TextMessage tm = (TextMessage) message;
		try {
			System.out.println("监听到了文本消息：" + tm.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
