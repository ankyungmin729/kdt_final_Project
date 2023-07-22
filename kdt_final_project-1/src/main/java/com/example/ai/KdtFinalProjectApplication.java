package com.example.ai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"travelspot", "user", "controller","community","FAQ"})
@ComponentScan(basePackages = "errors")
@MapperScan(basePackages = {"travelspot"})
public class KdtFinalProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(KdtFinalProjectApplication.class, args);
	}

}
