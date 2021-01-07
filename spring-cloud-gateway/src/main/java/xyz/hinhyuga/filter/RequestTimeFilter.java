package xyz.hinhyuga.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Description: RequestTimeFilter
 * @Author bryan
 * @Date 2021/1/6 10:42 下午
 * @Version 1.0
 */
@Slf4j
@Component
public class RequestTimeFilter implements GatewayFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getAttributes().put("startTime", System.currentTimeMillis());
        log.info("===============pre阶段================");
        return chain.filter(exchange).then(
                Mono.fromRunnable(() -> {
                    Long startTime = exchange.getAttribute("startTime");
                    if (startTime != null) {
                        log.info("=============post阶段============，执行路径：{}，所耗时间：{}",
                                exchange.getRequest().getURI().getRawPath(),
                                (System.currentTimeMillis() - startTime) + "ms");
                    }
                })
        );
    }

    @Override
    public int getOrder() {
        return 100;
    }
}
