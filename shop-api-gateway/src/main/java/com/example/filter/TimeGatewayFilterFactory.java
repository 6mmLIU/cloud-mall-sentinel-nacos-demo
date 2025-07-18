package com.example.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.out;

@Component
public class TimeGatewayFilterFactory extends AbstractGatewayFilterFactory<TimeGatewayFilterFactory.Config> {

    public TimeGatewayFilterFactory() {
        super(Config.class); // ← 缺分号已补
    }

    @Override
    public List<String> shortcutFieldOrder() {   // ← 方法名拼写修正 shotcutFiledOrder -> shortcutFieldOrder
        return Stream.of("show").collect(Collectors.toUnmodifiableList());
    }

    @Override
    public GatewayFilter apply(Config config) {  // ← 参数类型修正 AbcGatewayFilterFactory.Config -> Config
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                long begin = new Date().getTime();
//                exchange.getAttributes().put("begin",begin);
                return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                    long end = new Date().getTime();
//                    long start=exchange.getAttribute("begin");
                    long duration = end - begin;
                    String path = exchange.getRequest().getURI().getPath();
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("请求")
                            .append(path)
                            .append("接口耗时")
                            .append(duration)
                            .append("毫秒").toString();
                    System.err.println(stringBuffer);
                }));
            }
        };
    }

    public static class Config {
        // true表示输出接口调用耗时,false不输出
        private boolean show;

        public boolean isShow() {   // ← 增加 getter/setter 以便属性绑定
            return show;
        }

        public void setShow(boolean show) {
            this.show = show;
        }
    }
}
