package com.laoxu.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.laoxu.springcloud.dao"})
public class MyBatisConfig {

}
