package com.imooc;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class Controller implements IService {


  @Value("${server.port}")
  private String port;

  @Override
  public String sayHi() {
    return "this is " + port;
  }

  @Override
  public Friend sayHiPost(@RequestBody Friend friend) {
    log.info("you name is {}", friend.getName());
    friend.setPort(port);
    return friend;
  }

  @Override
  public String retry(int timeout) {
    while (timeout-- >= 0) {
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    log.info("retry " + port);
    return port;
  }

  @Override
  public String error() {
    throw new RuntimeException("error ... ");
  }
}
