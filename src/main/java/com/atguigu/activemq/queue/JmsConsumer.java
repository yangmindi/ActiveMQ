package com.atguigu.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class JmsConsumer {
//    public static final String ACTIVEMQ_URL = "tcp://192.168.91.128:61616";
    public static final String ACTIVEMQ_URL = "tcp://localhost:61616";
    public static final String QUEUE_NAME = "queue01";

    public static void main(String[] args) throws JMSException, IOException {
        System.out.println("****我是2号消费者");
        //1 创建连接工厂，按照给定的url地址，采用默认用户和密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //2 通过连接工厂，获得连接connection并启动访问
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        //3 创建会话Session
        //两个参数，第一个叫事务，第二个叫签收
        Session session = connection.createSession(true, Session.CLIENT_ACKNOWLEDGE);
        //4 创建目的地（具体是队列还是主题topic）
        Queue queue = session.createQueue(QUEUE_NAME);
        //5 创建消费者
        MessageConsumer messageConsumer = session.createConsumer(queue);

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

        while(true){
            TextMessage textMessage = (TextMessage) messageConsumer.receive(4000L);
            if(null != textMessage){
                System.out.println("*****消费者接受到消息:"+textMessage.getText());
//                textMessage.acknowledge();
            }else {
                break;
            }
        }
//        System.in.read();//保证控制台不灭
        messageConsumer.close();
        session.commit();
        session.close();
        connection.close();
    }
}
