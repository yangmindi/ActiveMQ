package com.atguigu.boot.activemq.produce;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
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

    //间隔时间3秒钟定投
    @Scheduled(fixedDelay = 3000)
    public void produceMsgScheduled(){
        jmsMessagingTemplate.convertAndSend(queue,"******Scheduled:"+UUID.randomUUID().toString().substring(0,6));
        System.out.println("*****produceMsgScheduled send ok");
    }

}
