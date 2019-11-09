package com.atguigu.activemq.persist;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsProduce_Topic_Persist {
    public static final String ACTIVEMQ_URL = "tcp://192.168.91.128:61616";
    public static final String TOPIC_NAME = "topic-atguigu";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();

        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(TOPIC_NAME);
        MessageProducer messageProducer = session.createProducer(topic);

        messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
        connection.start();

        for(int i=1; i<=3; i++){
            TextMessage textMessage = session.createTextMessage("msg-persist-" + i);
            messageProducer.send(textMessage);
        }

        messageProducer.close();
        session.close();
        connection.close();
        System.out.println("*****消息发布到MQ完成");
    }
}
