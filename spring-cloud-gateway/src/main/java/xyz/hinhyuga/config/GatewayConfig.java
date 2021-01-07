package xyz.hinhyuga.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.hinhyuga.filter.RequestTimeFilter;

/**
 * @Description: Gateway Config
 * @Author bryan
 * @Date 2021/1/6 10:53 下午
 * @Version 1.0
 */
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/user/**")
                        .filters(f -> f.filter(new RequestTimeFilter())
                                .addRequestHeader("X-Response-Default-Foo", "Default-Bar"))
                        .uri("lb://springcloud-user")
                        .order(0)
                        .id("springcloud-user")).build();
    }
}