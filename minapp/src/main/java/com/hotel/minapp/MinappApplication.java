package com.hotel.minapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= {"com.hotel.*"})
@MapperScan({"com.hotel.*.mapper","com.hotel.*.dao"})
public class MinappApplication {

  public static void main(String[] args) {
    SpringApplication.run(MinappApplication.class, args);
  }

}
