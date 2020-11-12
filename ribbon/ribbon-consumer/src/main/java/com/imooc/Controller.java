package com.imooc;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class Controller {
  @Autowired
  private RestTemplate restTemplate;

  @GetMapping("/sayHi")
  public String sayHi(){
    return restTemplate.getForObject("http://eureka-provider/sayHi", String.class);
  }

}
