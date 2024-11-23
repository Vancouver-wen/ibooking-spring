package org.example;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/*
@SpingBootApplication 是springboot框架的核心注解
添加在启动类上（配置类上），是一个组合注解，包括：
@SpringBootConfiguration -> @Configuration -> 声明启动类是一个配置类
@ComponentScan -> 开启组件扫描
@EnableAutoConfiguration -> 自动给出组件扫描的范围
*/
/*
如果你的项目中使用的是 Spring Boot 并且配置了 @MapperScan 注解，
通常不需要在每个 Mapper 接口上单独添加 @Mapper 注解。
@MapperScan 注解会告诉 Spring Boot 在指定的包路径下自动扫描并注册所有接口为 MyBatis 的 Mapper，
Spring Boot 会自动为这些接口创建代理实现，并注册为 Spring 的 Bean，
这样你就可以直接使用 @Autowired 来注入这些 Mapper 接口。
 */
@MapperScan("org.example.repository.mapper")
@SpringBootApplication
@EnableTransactionManagement
public class Main {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL)); // 分页拦截器
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor()); // CAS锁的拦截器 @Version
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());  // 防止删库跑路的拦截器
        return interceptor;
    }
    public static void main(String[] args) {
        SpringApplication.run(Main.class,args); // run是SpringApplication类的一个static方法

        /*
        SpringApplication.run 会启动springBoot工程
        启动tomcat，于是程序就可以处理来自前端的请求了
        后端调试可以使用 postman 来模拟前端请求，进行并行开发
         */
        /*
        在@Controller里面 来处理 springMVC, 也就是写Handler（调用service层）来处理request并返回response
        在@Service里面 调用mapper接口的方法
        在@Repository里面 在mapper接口上使用mybatis的select等注解来写sql语句
         */
        /* Controller层代码 -> 主要是springMVC
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RestController;
        import org.springframework.beans.factory.annotation.Autowired;

        @RestController
        public class MyController {

            @Autowired
            private final MyService myService;

            @GetMapping("/example")
            public String getExample() {
                return myService.exampleMethod();
            }
        }
         */
        /* Service层代码 -> 业务代码。主要是调用mapper接口的方法
        import org.springframework.stereotype.Service;
        import org.springframework.beans.factory.annotation.Autowired;

        @Service
        public class MyService {

            @Autowired
            private final MyRepository myRepository;

            public String exampleMethod() {
                // 业务逻辑处理
                return myRepository.findSomething();
            }
        }
         */
        /* Repository层代码
        import org.springframework.stereotype.Repository;
        import org.springframework.transaction.annotation.Transactional;
        import org.apache.ibatis.annotations.Mapper;
        import org.apache.ibatis.annotations.Select;

        @Mapper
        @Repository
        public interface MyRepository {

            @Select("SELECT * FROM my_table WHERE id = #{id}")
            @Transactional(readOnly = true)
            String findSomething();
        }
         */
    }
}