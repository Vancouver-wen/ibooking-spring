package org.example.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.repository.pojo.User;
import org.springframework.stereotype.Component;

// 继承mybatis-plus提供的基础Mapper接口，自带crud方法
/*
尽管在配置类上加入@MapperScan之后，在mapper接口上加@Mapper注解不是必需的
但有些人选择在每个 Mapper 接口上添加 @Mapper 注解，这样可以提高代码的可读性。
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
