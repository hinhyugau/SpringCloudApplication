package xyz.hinhyuga.web;

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
@RequestMapping("/user")
public class UserController {
    @GetMapping
    public String getUser(){
        return "是的！";
    }
}
