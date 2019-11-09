package com.atguigu.boot.activemq.produce;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Queue_Produce {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private ActiveMQQueue queue;

    public void produceMsg(){
        jmsMessagingTemplate.convertAndSend(queue,"******：" + UUID.randomUUID().toString().substring(0,6));
    }
}
