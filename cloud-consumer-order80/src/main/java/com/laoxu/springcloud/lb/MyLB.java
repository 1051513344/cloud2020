package com.laoxu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLB implements LoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement(){
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current >= 2147483647 ? 0 : current + 1;

        }while (!this.atomicInteger.compareAndSet(current, next));
        System.out.println("*****next: " + next);
        return next;
    }

    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        // 当前服务提供者下标 = 获取当前自增数 % 服务总数
        int index = getAndIncrement() % serviceInstances.size();
        // 获取当前服务提供者
        return serviceInstances.get(index);
    }
}
