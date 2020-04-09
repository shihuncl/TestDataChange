package com.yshu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by chenlei on 2020/4/3.
 */
@SpringBootApplication
@EnableScheduling
@MapperScan(basePackages = {"com.yshu.dao"})
public class YshuTimeTaskApplication {
    public static void main(String[] args) {
        SpringApplication.run(YshuTimeTaskApplication.class, args);
    }
}
