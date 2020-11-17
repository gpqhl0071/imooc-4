package com.imooc.biz;

import lombok.extern.slf4j.Slf4j;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@Slf4j
@EnableBinding(
    value = {
        Sink.class
    }
)
public class StreamConsumer {

  @StreamListener(Sink.INPUT)
  public void consume(Object payload) {
    log.info("message consumed successfully , payload={}", payload);
  }

}
