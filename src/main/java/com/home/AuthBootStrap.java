package com.home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import tk.mybatis.spring.annotation.MapperScan;


/**
 *
 * @author cxf
 * @date 2018-03-15
 * @description 鉴权中心启动类
 *
 */
@SpringBootApplication
@ServletComponentScan("com.cxf.windex.config.druid")
@MapperScan(basePackages = "com.home.mapper")
public class AuthBootStrap {
    public static void main(String[] args) {
        SpringApplication.run(AuthBootStrap.class, args);
    }
}
