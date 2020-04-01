package com.phx.activemq.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Producer {
	public static void main(String[] args) throws JMSException {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, "tcp://140.143.26.129:61616");
		Connection connection =   connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue("ww-activemq");
		MessageProducer producer  = session.createProducer(destination);
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		for(int i = 1;i<=5;i++) {
			String ms = "我是生产者："+i;
			System.out.println(ms);
			sendMsg(session,producer, ms);
		}
		System.out.println("生产者 发送消息完毕！！");
			
		
	}
	
	
	 public static void sendMsg(Session session,MessageProducer producer,String message) throws JMSException {
		TextMessage textMessage = session.createTextMessage(message);
		producer.send(textMessage);
	}

}
