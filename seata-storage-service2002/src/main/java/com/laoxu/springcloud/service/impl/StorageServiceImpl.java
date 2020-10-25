package com.laoxu.springcloud.service.impl;


import com.laoxu.springcloud.dao.StorageDao;
import com.laoxu.springcloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageDao storageDao;

    public void decrease(Long productId, Integer count) {
        log.info("------------storage-service-中扣减库存开始");
        storageDao.decrease(productId, count);
        log.info("------------storage-service-中扣减库存结束");
    }
}

