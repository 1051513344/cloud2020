package com.laoxu.springcloud.service;

import com.laoxu.springcloud.entities.Payment;

public interface PaymentService {
    int create(Payment payment);
    Payment getPaymentById(Long id);
}
