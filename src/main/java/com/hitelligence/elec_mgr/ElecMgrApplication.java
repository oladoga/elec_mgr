package com.hitelligence.elec_mgr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.hitelligence.elec_mgr.mapper") // 扫描 Mapper 的包
public class ElecMgrApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElecMgrApplication.class, args);
    }
}
