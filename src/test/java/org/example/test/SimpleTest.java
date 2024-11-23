package org.example.test;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.repository.mapper.UserMapper;
import org.example.repository.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
/*
在test中新建一个路径，与main相同，这样java在执行的时候会打包到一起
这样就可以找到包了
 */
@SpringBootTest
public class SimpleTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void testSelect(){
        System.out.println("----- selectAll method test ------");
        List<User> userList=userMapper.selectList(null);
        for(User user:userList){
            System.out.println(user.toString());
        }
    }
    @Test
    public void testPageQuery(){
        //设置分页参数
        Page<User> page = new Page<>(1, 5);
        userMapper.selectPage(page, null);
        //获取分页数据
        List<User> list = page.getRecords();
        list.forEach(System.out::println);
        System.out.println("当前页："+page.getCurrent());
        System.out.println("每页显示的条数："+page.getSize());
        System.out.println("总记录数："+page.getTotal());
        System.out.println("总页数："+page.getPages());
        System.out.println("是否有上一页："+page.hasPrevious());
        System.out.println("是否有下一页："+page.hasNext());
        // 插入数据
        User user=new User();
        user.setName("wzh");
        user.setAge(24);
        user.setEmail("1052951572@qq.com");
        Integer row=userMapper.insert(user);
        System.out.println("主键回显测试"+user.getId()); // -> 发现在 POJO类 User使用了@TableId 指定type=AUTO 之后，实现了主键回显
    }
}
