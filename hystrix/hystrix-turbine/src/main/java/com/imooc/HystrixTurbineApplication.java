package com.imooc;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@EnableDiscoveryClient
@EnableHystrix
@EnableCircuitBreaker
@EnableTurbine
@EnableAutoConfiguration
public class HystrixTurbineApplication {
  public static void main(String[] args) {

    new SpringApplicationBuilder(HystrixTurbineApplication.class)
        .web(WebApplicationType.SERVLET)
        .run(args);
  }
}
