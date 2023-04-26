package com.star.statemachine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 状态机demo 启动类
 */
@SpringBootApplication(scanBasePackages = {"com.star.statemachine", "com.star.commons"})
public class StatemachineApplication {

    public static void main(String[] args) {

        SpringApplication.run(StatemachineApplication.class, args);
    }

}
