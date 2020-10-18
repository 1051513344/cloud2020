package com.laoxu.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.laoxu.springcloud.entities.CommonResult;

public class CustomerBlockHandle {

    public static CommonResult handleException(BlockException exception){
        return new CommonResult(4444, "按客户自定义,全局异常------1");
    }
    public static CommonResult handleException2(BlockException exception){
        return new CommonResult(4444, "按客户自定义,全局异常------2");
    }
}
