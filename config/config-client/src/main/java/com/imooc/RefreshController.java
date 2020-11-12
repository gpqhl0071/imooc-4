package com.imooc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 动态刷新 <><br/>
 * <p>
 * 通过actualtor接口刷新配置 调用 http://localhost:61000/actuator/refresh <><br/>
 */
@RestController
@RequestMapping("refresh")
@RefreshScope
public class RefreshController {

  @Value("${words}")
  private String words;

  @Value("${food}")
  private String foods;

  @GetMapping("words")
  public String getWords() {
    return words;
  }

  @GetMapping("dinner")
  public String dinner(){
    return "May I have one " + foods;
  }
}
