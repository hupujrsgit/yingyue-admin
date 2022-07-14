package org.exeplm;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.exeplm.dao")
public class SysAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(SysAdminApplication.class,args);
    }
}
