package com.atguigu.activemq.spring;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Component
public class MyMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        if (message != null && message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            try {
                System.out.println(textMessage.getText());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
