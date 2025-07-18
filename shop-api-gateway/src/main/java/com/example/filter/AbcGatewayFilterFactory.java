package com.example.filter;

import lombok.Data;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Component
public class AbcGatewayFilterFactory extends AbstractGatewayFilterFactory<AbcGatewayFilterFactory.Config> {
    public AbcGatewayFilterFactory(){
        super(Config.class);
    }
    @Override
    public List<String> shortcutFieldOrder(){
        List<String> list=new ArrayList<>();
        list.add("n1");
        list.add("n2");
        list.add("s");
        list.add("b");
        list.add("n3");
        return list;
    }
    @Override
    public GatewayFilter apply(Config config){
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                return chain.filter(exchange);
            }
        };
    }

    @Data
    public static class Config {
        private Integer n1;
        private Integer n2;
        private String s;
        private boolean b;
        private Integer n3;
    }
}
