package com.laoxu.springcloud.controller;

import com.laoxu.springcloud.entities.CommonResult;
import com.laoxu.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PaymentController {

    @Value("${server.port}")
    public String serverPort;

    public static HashMap<Long, Payment> hashMap = new HashMap<Long, Payment>();
    static
    {
        hashMap.put(1L, new Payment(1L, "a"));
        hashMap.put(2L, new Payment(2L, "b"));
        hashMap.put(3L, new Payment(3L, "c"));
    }

    @GetMapping("/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        Payment payment = hashMap.get(id);
        CommonResult<Payment> result = new CommonResult<Payment>(200, "from mysql, serverPort: " + serverPort, payment);
        return result;
    }

}
