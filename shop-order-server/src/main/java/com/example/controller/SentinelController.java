package com.example.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class SentinelController {
    @RequestMapping("/sentinel1")
    public String sentinel1() {
//模拟一次网络延时
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "sentinel1";
    }

    @RequestMapping("/sentinel2")
    public String sentinel2() {

        return "测试高并发下的问题";

    }

    @RequestMapping("/vip")
    public String vip() {
        return "vip访问资源";
    }

    @RequestMapping("/free")
    public String free() {
        return "免费用户访问资源";
    }


    @RequestMapping("/trace1")
    public String trace1() {
        return "trace1";
    }

    @RequestMapping("/trace2")
    public String trace2() {
        return "trace2";
    }

    @RequestMapping("/trace3")
    public String trace3() {
        return "trace3";
    }

    int i = 0;

    @RequestMapping("/fallBack2")
    public String fallBack2() {
        //模拟出现异常，异常比例为33%
        if (++i % 3 == 0) {
            throw new RuntimeException();
        }
        return "fallBack2";
    }

    @RequestMapping("/hotSpot1")
    @SentinelResource(value = "hotSpot1")
    public String hotSpot1(Long productId) {
        return "hotSpot1";
    }

    @RequestMapping("/auth")
    public String auth() {
        return "auth";
    }
}
