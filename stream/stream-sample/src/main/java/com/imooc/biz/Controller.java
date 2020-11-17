package com.imooc.biz;

import com.imooc.topic.GroupTopic;
import com.imooc.topic.MyTopic;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class Controller {

  @Autowired
  private MyTopic producer;
  @Autowired
  private GroupTopic groupTopicProducer;

  @PostMapping("send")
  public void sendMessage(@RequestParam(value = "body") String body) {
    producer.output().send(MessageBuilder.withPayload(body).build());
  }

  @PostMapping("sendToGroup")
  public void sendToGroup(@RequestParam(value = "body") String body) {
    groupTopicProducer.output().send(MessageBuilder.withPayload(body).build());
  }

}
