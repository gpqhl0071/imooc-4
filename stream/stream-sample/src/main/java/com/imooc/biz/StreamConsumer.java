package com.imooc.biz;

import com.imooc.topic.DlqTopic;
import com.imooc.topic.ErrorTopic;
import com.imooc.topic.GroupTopic;
import com.imooc.topic.MyTopic;

import lombok.extern.slf4j.Slf4j;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@EnableBinding(
    value = {
        Sink.class,
        MyTopic.class,
        GroupTopic.class,
        ErrorTopic.class,
        DlqTopic.class
    }
)
public class StreamConsumer {

  private AtomicInteger count = new AtomicInteger(1);

  @StreamListener(Sink.INPUT)
  public void consume(Object payload) {
    log.info("message consumed successfully , payload={}", payload);
  }


  @StreamListener(MyTopic.INPUT)
  public void consumeMyMessage(Object payload) {
    log.info("my message consumed successfully , payload={}", payload);
  }

  @StreamListener(GroupTopic.INPUT)
  public void consumeGroupMessage(Object payload) {
    log.info("Group message consumed successfully , payload={}", payload);
  }

  @StreamListener(ErrorTopic.INPUT)
  public void consumeErrorMessage(Object payload) {
    log.info("are you ok?");
    if (count.incrementAndGet() % 3 == 0) {
      log.info("fine, thank you. and you ");
      count.set(0);
    } else {
      log.info("what's your problem");
      throw new RuntimeException("i'am not ok");
    }
    log.info("error message consumed successfully , payload={}", payload);
  }

  @StreamListener(DlqTopic.INPUT)
  public void consumeDlqMessage(Object payload) {
    log.info("dlq - are you ok?");
    if (count.incrementAndGet() % 3 == 0) {
      log.info("dlq - fine, thank you. and you ");
    } else {
      log.info("dlq - what's your problem");
      throw new RuntimeException("i'am not ok");
    }
    log.info("error message consumed successfully , payload={}", payload);
  }

}
