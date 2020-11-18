package com.imooc.biz;

import com.imooc.topic.DlqTopic;
import com.imooc.topic.ErrorTopic;
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
  @Autowired
  private ErrorTopic errorTopic;
  @Autowired
  private DlqTopic dlqTopic;

  @PostMapping("send")
  public void sendMessage(@RequestParam(value = "body") String body) {
    producer.output().send(MessageBuilder.withPayload(body).build());
  }

  @PostMapping("sendToGroup")
  public void sendToGroup(@RequestParam(value = "body") String body) {
    groupTopicProducer.output().send(MessageBuilder.withPayload(body).build());
  }

  @PostMapping("sendError")
  public void sendErrorMessage(@RequestParam(value = "body") String body) {
    MessageBean msg = new MessageBean();
    msg.setPayload(body);
    errorTopic.output().send(MessageBuilder.withPayload(msg).build());
  }

  @PostMapping("dlq")
  public void sendDlqMessage(@RequestParam(value = "body") String body) {
    MessageBean msg = new MessageBean();
    msg.setPayload(body);
    dlqTopic.output().send(MessageBuilder.withPayload(msg).build());
  }

}
