package com.dj.mall.admin;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.admin
 * @ClassName: AdminApplication
 * @Author: Liuwf
 * @Description: 消费者启动类
 * @Date: 2020/3/24 19:06
 * @Version: 1.0
 */

@EnableDubbo
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

}
