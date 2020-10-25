package com.laoxu.springcloud.service.impl;

import com.laoxu.springcloud.dao.OrderDao;
import com.laoxu.springcloud.domain.Order;
import com.laoxu.springcloud.service.AccountService;
import com.laoxu.springcloud.service.OrderService;
import com.laoxu.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private StorageService storageService;

    @Autowired
    private AccountService accountService;

    @Override
    @GlobalTransactional(name = "fsp-create-order", rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("--->开始创建订单");
        // 1.创建订单
        orderDao.create(order);
        log.info("--->订单微服务开始调用库存，做扣减Count");
        // 2.扣减库存
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("--->订单微服务开始调用库存，做扣减end");

        log.info("--->订单微服务开始调用账户，做扣减Money");
        // 3.扣减账户
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("--->订单微服务开始调用账户，做扣减end");

        // 4.修改订单的状态，从零到1，代表已经完成
        log.info("--->修改订单状态开始");
        orderDao.update(order.getUserId(), 0);
        log.info("--->修改订单状态结束");

        log.info("--->下单结束");

    }
}
