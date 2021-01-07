package xyz.hinhyuga.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * @Description: Request Time gateway Filter Factory
 * @Author bryan
 * @Date 2021/1/7 9:40 上午
 * @Version 1.0
 */
@Slf4j
@Component
public class RequestTimeGatewayFilterFactory extends AbstractGatewayFilterFactory<RequestTimeGatewayFilterFactory.Config> {

    public RequestTimeGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("enable");
    }

    /**
     * filter chain
     *
     * @param config
     * @return
     */
    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (!config.isEnable()) {  //if disEnable filter skip
                return chain.filter(exchange);
            }
            exchange.getAttributes().put("startTime", System.currentTimeMillis());
            log.info("===========pre阶段============");
            return chain.filter(exchange).then(
                    Mono.fromRunnable(() -> {
                        Long startTime = exchange.getAttribute("startTime");
                        if (startTime != null) {
                            log.info("============post阶段===========,执行路径：{},所耗时间：{}",
                                    exchange.getRequest().getURI().getRawPath(), (System.currentTimeMillis() - startTime) + "ms");
                        }
                    })
            );
        });
    }

    /**
     * Configuration properties of the filter factory in the configuration file
     */
    public static class Config {
        //Control whether to enable authentication
        private boolean enable;

        public Config() {
        }

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }
    }
}