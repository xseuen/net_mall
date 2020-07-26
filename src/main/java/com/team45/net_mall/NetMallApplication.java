package com.team45.net_mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.team45.net_mall.mapper"})
public class NetMallApplication {

    public static void main(String[] args) {
        SpringApplication.run(NetMallApplication.class, args);
    }

}
