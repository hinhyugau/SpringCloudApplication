package xyz.hinhyuga.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Description: Global Token Filter
 * @Author bryan
 * @Date 2021/1/6 10:23 下午
 * @Version 1.0
 */
@Slf4j
@Component
public class GlobalTokenFilter implements GlobalFilter, Ordered {

    /**
     * filter carried out
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        /**
         * get token
         */
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        if (StringUtils.isBlank(token)) {
            log.error("You are not logged in, please log in and try again！");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().writeWith(Flux.just(exchange.getResponse().bufferFactory()
                    .wrap("You are not logged in, please log in and try again!".getBytes())));
        }
        /**
         * next filter carried out
         */
        return chain.filter(exchange);
    }

    /**
     * Set the priority of the current filter, the larger the value, the lower the priority
     *
     * @return
     */
    @Override
    public int getOrder() {
        return -100;
    }
}