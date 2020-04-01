package com.phx.activemq.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Consumer {
	
	public static void main(String[] args) throws JMSException {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, "tcp://140.143.26.129:61616");
		Connection connection =   connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue("ww-activemq");
		MessageConsumer messageConsumer  = session.createConsumer(destination);
		
		while(true) {
			TextMessage textMessage = (TextMessage) messageConsumer.receive();
			if(textMessage!=null) {
				String s = textMessage.getText();
				System.out.println("消费者 获取到消息：text:"+s);
			}else {
				break;
			}
			
		}
		System.out.println("消费者 获取消息完毕！！");
			
		
	}
	
	

}
