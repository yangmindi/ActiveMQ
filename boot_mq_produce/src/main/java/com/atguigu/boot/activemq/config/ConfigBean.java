package com.atguigu.boot.activemq.config;


import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

@Component
@EnableJms
public class ConfigBean {
    @Value("${myqueue}")
    private String myQueue;

    @Bean
    public ActiveMQQueue queue(){
        return new ActiveMQQueue(myQueue);
    }
}
