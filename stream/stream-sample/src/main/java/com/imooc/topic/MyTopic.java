package com.imooc.topic;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MyTopic {

  String INPUT = "myTopic-consumer";

  String OUTPUT = "myTopic-producer";

  @Input(INPUT)
  SubscribableChannel input();

  @Output(OUTPUT)
  MessageChannel output();
}
