package com.example.zti;

import com.example.zti.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class ZtiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZtiApplication.class, args);
	}

}
