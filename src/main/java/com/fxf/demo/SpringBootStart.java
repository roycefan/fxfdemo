package com.fxf.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

import java.io.Console;

@MapperScan("com.fxf.demo.dao.*")
@SpringBootApplication
public class SpringBootStart {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(SpringBootStart.class, args);

        List<String> jdks = Arrays.asList("JDK6", "JDK8", "JDK10");
        List<String> colors = Stream.of("blue", "red", "yellow").collect(toList());

    }
}
