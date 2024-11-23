package org.example.controller;

import org.example.repository.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("hello")
public class HelloController {
    @Value("hello world")
    private String hello;
    @Autowired
    private User user;
    @GetMapping("boot")
    public String hello(){
        System.out.println("正在访问hello方法: "+hello);
        return user.toString();
    }
}
