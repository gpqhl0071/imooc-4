package com.imooc;

import com.imooc.hystrix.RequestCacheService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

import lombok.Cleanup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
  @Autowired
  private MyService myService;
  @Autowired
  private RequestCacheService requestCacheService;

  @GetMapping("/fallback")
  public String fallback() {
    return myService.error();
  }

  @GetMapping("/timeout")
  public String timeout(Integer timeout) {
    return myService.retry(timeout);
  }

  @GetMapping("/timeout2")
  @HystrixCommand(
      fallbackMethod = "timeoutFallback",
      commandProperties = {
          @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
      }
  )
  public String timeout2(Integer timeout) {
    return myService.retry(timeout);
  }

  public String timeoutFallback(Integer timeout) {
    return "success";
  }

  @GetMapping("/cache")
  public Friend cache(String name) {
    @Cleanup HystrixRequestContext context = HystrixRequestContext.initializeContext();

    Friend friend = requestCacheService.requstCache(name);
//    name += "!";
    friend = requestCacheService.requstCache(name);
    return friend;
  }
}
