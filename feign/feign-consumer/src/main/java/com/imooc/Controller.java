package com.imooc;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class Controller {
  @Autowired
  private IService iService;

  @GetMapping("sayHi")
  public String sayHi(){
    return iService.sayHi();
  }

}
