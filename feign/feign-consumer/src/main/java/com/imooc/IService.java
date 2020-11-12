package com.imooc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("eureka-provider")
public interface IService {

  @GetMapping("/sayHi")
  String sayHi();

}
