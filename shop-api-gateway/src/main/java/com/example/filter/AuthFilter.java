package com.example.filter;

import com.alibaba.cloud.commons.lang.StringUtils;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;
//
//@Component
//public class AuthFilter implements GlobalFilter {
//    @Autowired
//    private StringRedisTemplate template;
//    /**
//     * 过滤器的过滤方法
//     * 参数1: 可以简单理解成 request+response=exchange
//     * 参数2: 过滤器链
//     */
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        ServerHttpRequest request=exchange.getRequest();
//        ServerHttpResponse response=exchange.getResponse();
//        //共享域
//        Map<String,Object> attributes=exchange.getAttributes();
//        //header设置
//        HttpHeaders headers=request.getHeaders();
//        String token = exchange.getRequest().getQueryParams().getFirst("token");
//        //如果token为null或" "或空白符
//        if (StringUtils.isBlank(token)){
//            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//            return exchange.getResponse().setComplete();
//        }
//       Boolean b= template.hasKey(token);
//        if (!b){
//            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//            return exchange.getResponse().setComplete();
//        }
//        System.out.println("过滤器被执行了");
//        //放行
//        return chain.filter(exchange);
//    }
//}
