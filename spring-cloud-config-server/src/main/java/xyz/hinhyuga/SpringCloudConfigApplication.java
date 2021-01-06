package xyz.hinhyuga;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @Description: spring cloud config
 * @Author bryan
 * @Date 2021/1/6 3:31 下午
 * @Version 1.0
 */
@SpringCloudApplication
@EnableConfigServer
public class SpringCloudConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConfigApplication.class, args);
    }
}