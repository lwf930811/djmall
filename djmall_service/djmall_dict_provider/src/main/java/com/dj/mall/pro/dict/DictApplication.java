package com.dj.mall.pro.dict;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ProjectName: djmall
 * @Package: com.dj.mall.pro.auth
 * @ClassName: AuthApplication
 * @Author: Liuwf
 * @Description: auth启动类
 * @Date: 2020/3/24 18:07
 * @Version: 1.0
 */

@MapperScan("com.dj.mall.mapper")
@EnableDubbo
@SpringBootApplication
public class DictApplication {
    public static void main(String[] args) {
        SpringApplication.run(DictApplication.class,args);
    }

}
