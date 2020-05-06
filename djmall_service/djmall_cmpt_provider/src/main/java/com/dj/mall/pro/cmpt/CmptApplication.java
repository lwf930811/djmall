package com.dj.mall.pro.cmpt;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDubbo
@EnableTransactionManagement
public class CmptApplication {

    public static void main(String[] args) {
    SpringApplication.run(CmptApplication.class, args);
    }

}
