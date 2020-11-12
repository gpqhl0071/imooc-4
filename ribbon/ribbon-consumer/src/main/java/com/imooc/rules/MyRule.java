package com.imooc.rules;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

public class MyRule extends AbstractLoadBalancerRule implements IRule {


  @Override
  public void initWithNiwsConfig(IClientConfig iClientConfig) {

  }

  @Override
  public Server choose(Object key) {
    return null;
  }
}
