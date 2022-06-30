package com.ameya.sportyshoes;

import com.ameya.sportyshoes.security.AppProperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SportyShoesApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SportyShoesApplication.class, args);
	}
	
//	@Bean
//	public BCryptPasswordEncoder bCryptPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
	
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SportyShoesApplication.class);
	}

	@Bean
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}
	
	@Bean
	public AppProperties etAppProperties() {
		return new AppProperties();
	}

}
