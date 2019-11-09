package com.atguigu.boot.activemq.consumer;

import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Component
public class Queue_Consumer {

    @JmsListener(destination = "${myqueue}")
    public void reveive(TextMessage textMessage) throws JMSException {
        System.out.println("****消费者收到消息:"+textMessage.getText());
    }
}
