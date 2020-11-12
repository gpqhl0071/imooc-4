package imooc;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class EurekaConsumerApplication {

  @Bean
  public RestTemplate register() {
    return new RestTemplate();
  }

  public static void main(String[] args) {
    new SpringApplicationBuilder(EurekaConsumerApplication.class)
        .web(WebApplicationType.SERVLET)
        .run(args);
  }

}
