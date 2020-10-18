package com.laoxu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.laoxu.springcloud.entities.CommonResult;
import com.laoxu.springcloud.entities.Payment;
import com.laoxu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class CircleBreakerController {

    public static final String SERVICE_URL = "http://nacos-payment-provider";

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/fallback/{id}")
//    @SentinelResource(value = "fallback", fallback = "handleFallback")
//    @SentinelResource(value = "fallback", blockHandler = "blockHandler")
//    @SentinelResource(value = "fallback", fallback = "handleFallback", blockHandler = "blockHandler")
    @SentinelResource(value = "fallback", fallback = "handleFallback",
            blockHandler = "blockHandler", exceptionsToIgnore = IllegalArgumentException.class)
    public CommonResult<Payment> fallback(@PathVariable("id") Long id){
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class);
        if(id == 4){
            throw new IllegalArgumentException("IllegalArgumentException， 非法参数异常。。。");
        }else if(id > 4 && id < 8){
            throw new NullPointerException("NullPointerException, 该id没有对应的记录，空指针异常");
        }
        return result;
    }

    public CommonResult<Payment> handleFallback(@PathVariable("id") Long id, Throwable e){
        Payment payment = new Payment(id, "null");
        return new CommonResult<Payment>(444, "fallback异常, 异常内容："+ e.getMessage(), payment);
    }
    public CommonResult<Payment> blockHandler(@PathVariable Long id, BlockException exception){
        Payment payment = new Payment(id, "null");
        return new CommonResult<Payment>(445, "blockHandler-sentinel限流, 无此流水 异常内容："+ exception.getMessage(), payment);
    }

    //==========openfeign
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/consumer/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL (@PathVariable("id") Long id){
        return paymentService.paymentSQL(id);
    }

}
