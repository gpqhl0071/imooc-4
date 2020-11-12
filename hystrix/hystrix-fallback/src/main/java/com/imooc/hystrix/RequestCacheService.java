package com.imooc.hystrix;

import com.imooc.Friend;
import com.imooc.MyService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RequestCacheService {
  @Autowired
  private MyService iService;

  @CacheResult
  @HystrixCommand(commandKey = "cacheKey")
  public Friend requstCache(@CacheKey String name){
    log.info("name :{}", name);
    Friend friend = new Friend();
    friend.setName(name);
    friend = iService.sayHiPost(friend);
    log.info("after name :{}", name);

    return friend;
  }

}
