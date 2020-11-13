package com.imooc;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AuthApplication {

  public static void main(String[] args) {
    new SpringApplicationBuilder(AuthApplication.class)
        .web(WebApplicationType.SERVLET)
        .run(args);
  }
}
