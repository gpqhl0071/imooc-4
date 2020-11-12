package com.imooc;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

@Configuration
@RibbonClient(name = "eureka-provider", value = "com.netflix.loadbalancer.RandomRule")
public class RibbonConfiguration {

//  @Bean
//  public IRule defaultLBStrategy(){
//    return new RandomRule();
//  }
}
