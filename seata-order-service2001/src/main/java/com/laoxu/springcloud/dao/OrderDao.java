package com.laoxu.springcloud.dao;

import com.laoxu.springcloud.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao {
    // 1新建订单
    void create(Order order);

    // 2修改订单状态 从0到1
    void update(@Param("userId") Long userId, @Param("status") Integer status);

}
