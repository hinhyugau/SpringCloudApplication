package xyz.hinhyuga.web;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: user web
 * @Author bryan
 * @Date 2021/1/6 11:17 上午
 * @Version 1.0
 */
@RestController
@RefreshScope
@RequestMapping("/user")
public class UserController {
    @Value("${human.name}")
    private String userName;

    @Value("${human.age}")
    private Integer age;

    @RequestMapping("/getUser")
    public User getUser() {
        User user = new User();
        user.setAge(age);
        user.setName(userName);
        return user;
    }
}
@Data
class User{
    private String name;
    private Integer age;
}