package org.example.repository.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "zwf.user") //批量读取配置文件。 @Value()方法不能读取列表
public class User {
    // MyBatis-Plus会自动开启驼峰命名风格映射
    @TableId(type = IdType.AUTO) // 这里可以实现主键回显(前提是mysql表user的id字段也是auto自增的)
    private Long id;
    private String name;
    private Integer age;
    private String email;
    @Version // 使用CAS乐观锁
    private Integer version;
    @TableField(exist = false) // 表明 extra 属性 并不存在于数据库表中（不对应数据库列名） -> 可以用来实现多表映射
    private String extra;
}
