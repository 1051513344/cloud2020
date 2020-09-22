package com.laoxu.springcloud.service.Impl;

import com.laoxu.springcloud.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;


import java.util.UUID;

@EnableBinding(Source.class)  // 定义消息的推送管道
public class MessageProviderImpl implements IMessageProvider {

    @Autowired
    private MessageChannel output; //消息发送管道

    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("******serial " + serial);
        return null;
    }
}
