package com.imooc;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@Slf4j
@RequestMapping("gateway")
public class GatewayController {

  public static final Map<Long, Product> items = new ConcurrentHashMap<>();

  @RequestMapping(value = "details", method = RequestMethod.GET)
  @ResponseBody
  public Product get(@RequestParam("pid") Long pid) {
    if (!items.containsKey(pid)) {
      Product product = Product.builder()
          .productId(pid)
          .description("好吃")
          .stock(100L)
          .build();
      items.putIfAbsent(pid, product);
    }
    return items.get(pid);
  }

  @RequestMapping(value = "placeOrder", method = RequestMethod.POST)
  public String buy(@RequestParam("pid") Long pid) {
    Product prod = items.get(pid);
    if (prod == null) {
      return "Prodcut not found";
    } else if (prod.getStock() <= 0L) {
      return "Solt out";
    }

    synchronized (prod) {
      if (prod.getStock() <= 0L) {
        return "Solt out";
      }
      prod.setStock(prod.getStock() - 1);
    }
    return "Order Placed";
  }

}
