package com.atguigu.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class JmsConsumer_Topic {
    public static final String ACTIVEMQ_URL = "tcp://192.168.91.128:61616";
    public static final String TOPIC_NAME = "topic-atguigu";

    public static void main(String[] args) throws JMSException, IOException {
        System.out.println("****我是3号消费者");
        //1 创建连接工厂，按照给定的url地址，采用默认用户和密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //2 通过连接工厂，获得连接connection并启动访问
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        //3 创建会话Session
        //两个参数，第一个叫事务，第二个叫签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4 创建目的地（具体是队列还是主题topic）
        Topic topic = session.createTopic(TOPIC_NAME);
        //5 创建消费者
        MessageConsumer messageConsumer = session.createConsumer(topic);

//        messageConsumer.setMessageListener(new MessageListener() {
//            @Override
//            public void onMessage(Message message) {
//                if(message != null && message instanceof TextMessage){
//                    TextMessage textMessage = (TextMessage) message;
//                    try {
//                        System.out.println("******消费者接收到消息："+textMessage.getText());
//                    } catch (JMSException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
        messageConsumer.setMessageListener((message) -> {
            if(null != message && message instanceof TextMessage){
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("******消费者接收到Topic消息：" + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        System.in.read();//保证控制台不灭
        messageConsumer.close();
        session.close();
        connection.close();
    }
}
