package xyz.hinhyuga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description: User
 * @Author bryan
 * @Date 2021/1/6 10:57 上午
 * @Version 1.0
 */
@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudUserApplication.class, args);
    }
}