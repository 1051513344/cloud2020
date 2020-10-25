package com.laoxu.springcloud.service.impl;

import com.laoxu.springcloud.dao.AccountDao;
import com.laoxu.springcloud.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    public void decrease(Long userId, BigDecimal money) {
        log.info("------------account-service-中扣减账户开始");
        accountDao.decrease(userId, money);
        try {
            TimeUnit.SECONDS.sleep(5);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        log.info("------------account-service-中扣减账户结束");
    }
}
