package com.imooc.hystrix;

import com.imooc.Friend;
import com.imooc.MyService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Component
public class Fallback implements MyService {

  /**
   * 容错处理类
   *
   * @return
   */
  @Override
  @HystrixCommand(fallbackMethod = "fallback2")
  public String error() {
    log.info("i am fallback");
    throw new RuntimeException("first fallback");
//    return "Fallback ~~~";
  }

  /**
   * 多级降级
   * @return
   */
  @HystrixCommand(fallbackMethod = "fallback3")
  public String fallback2(){
    log.info("fallback again");
    throw new RuntimeException("fallback again");
  }


  public String fallback3(){
    log.info("fallback again and again");
    return "success";
  }

  @Override
  public String sayHi() {
    return null;
  }

  @Override
  public Friend sayHiPost(@RequestBody Friend friend) {
    return null;
  }

  @Override
  public String retry(@RequestParam("timeout") int timeout) {
    return "You are Late !";
  }

}
