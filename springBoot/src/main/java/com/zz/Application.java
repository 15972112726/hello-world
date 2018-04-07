package com.zz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by dell on 2017/10/20.
 *
 * @EnableTransactionManagement
 @EnableTransactionManagement注解开启注解式事务的支持。
 注解@EnableTransactionManagement通知Spring，@Transactional注解的类被事务的切面包围。这样@Transactional就可以使用了。
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.zz.mapper")
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
        System.out.println("aa");
    }
}
