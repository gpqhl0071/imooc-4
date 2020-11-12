package com.imooc;

import com.imooc.hystrix.Fallback;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "feign-client", fallback = Fallback.class)
public interface MyService extends IService {

}
