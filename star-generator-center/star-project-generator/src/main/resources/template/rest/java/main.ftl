package com.${packageName};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ${projectDesc} 启动类
 */
@SpringBootApplication(scanBasePackages = {"com.${packageName}", "com.star.commons"})
public class ${className}Application {

    public static void main(String[] args) {

        SpringApplication.run(${className}Application.class, args);
    }

}
