package com.fxf.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.fxf.demo.dao.*")
public class SpringBootStart {
    public static void main(String [] args){
        ApplicationContext applicationContext= SpringApplication.run(SpringBootStart.class,args);
    }
}
