package com.laoxu.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallBackService implements PaymentHystrixService {

    public String paymentInfo_OK(Integer id) {
        return "PaymentFallBackService fall back-- paymentInfo_OK";
    }

    public String paymentInfo_Timeout(Integer id) {
        return "PaymentFallBackService fall back-- paymentInfo_Timeout";
    }
}
