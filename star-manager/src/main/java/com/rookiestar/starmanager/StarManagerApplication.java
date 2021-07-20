package com.rookiestar.starmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * starter
 *
 * @author springboot
 * @date 2021/7/8
 */
@ServletComponentScan
@SpringBootApplication
public class StarManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarManagerApplication.class, args);
    }

}
