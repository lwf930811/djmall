package com.dj.mall.platform;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description:平台消费者启动类
 * @Author: Liuwf
 * @Date:
 * @return: null
 **/
@SpringBootApplication
@EnableDubbo
public class PlatFormApplication {
    public static void main(String[] args) {
        SpringApplication.run(PlatFormApplication.class,args);
    }
}
