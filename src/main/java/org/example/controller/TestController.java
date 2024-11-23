package org.example.controller;

import org.example.repository.mapper.UserMapper;
import org.example.repository.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("hello")
public class TestController {
    @Autowired
    private UserMapper userMapper;
    @Transactional
    @GetMapping("trx1")
    public String trx1() throws Exception {
        // 先从数据库中读取
        User user=userMapper.selectById(6);
        System.out.println("事务一select by id "+user.toString());
        Thread.sleep(10000);
        user.setAge(user.getAge()+50);
        System.out.println("事务一 read_view check"+userMapper.selectById(6).toString());
        int row = userMapper.updateById(user);
        System.out.println("事务一 update by id"+user.toString());
        return "事务一 结束";
    }
    @Transactional
    @GetMapping("trx2")
    public String trx2() throws InterruptedException {
        // 先从数据库中读取
        User user=userMapper.selectById(6);
        System.out.println("事务二 select by id "+user.toString());
        Thread.sleep(5000);
        user.setAge(user.getAge()-50);
        System.out.println("事务二 read_view check"+userMapper.selectById(6).toString());
        int row = userMapper.updateById(user);
        System.out.println("事务二 update by id"+user.toString());
        // throw new RuntimeException("事务二 回滚"); // 只有throw RuntimeException才能回滚成功
        return "事务二 结束";
    }
}
