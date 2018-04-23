package com.xw.blog4u;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author xw
 * @date 2018年4月12日09:31:43
 */
@SpringBootApplication
@EnableSwagger2
@EnableCaching
public class Blog4uApplication {

	public static void main(String[] args) {
		SpringApplication.run(Blog4uApplication.class, args);
	}
}
