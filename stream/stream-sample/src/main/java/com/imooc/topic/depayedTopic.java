package com.imooc.topic;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface depayedTopic {
  String INPUT = "delayed-consumer";

  String OUTPUT = "delayed-producer";

  @Input(INPUT)
  SubscribableChannel input();

  @Output(OUTPUT)
  MessageChannel output();
}
