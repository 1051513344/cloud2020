package com.laoxu.springcloud.service;

import com.laoxu.springcloud.entities.CommonResult;
import com.laoxu.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService {

    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<Payment>(444, "服务降级返回, PaymentFallbackService", new Payment(id, "errorSerial"));
    }
}
