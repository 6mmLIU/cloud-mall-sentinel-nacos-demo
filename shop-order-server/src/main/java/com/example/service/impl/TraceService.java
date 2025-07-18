package com.example.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

@Service
public class TraceService {
    public void handleError(){
        System.out.println("请求失败---sentinel异常");
    }
    public void commonError(){
        System.out.println("请求失败--接口普通异常");
    }
    @SentinelResource(value = "impRes",blockHandler = "handleError",fallback = "commonError",exceptionsToIgnore = {NullPointerException.class})
    public void impRes(){
        System.out.println("我是重要的资源");
    }
}
