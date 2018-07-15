/**
 * @Author 	: Rajiv Kumar
 * @Version : 1.0.0
 */
package com.rajiv.apps.news.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages={"com.rajiv.apps.news.controller", 
		"com.rajiv.apps.news.service", "com.rajiv.apps.news.dao"})
@EntityScan( basePackages = {"com.rajiv.apps.news.entity"})
public class NewsServiceAppStarter {
	public static void main(String[] args) {
		SpringApplication.run(NewsServiceAppStarter.class, args);
	}
}