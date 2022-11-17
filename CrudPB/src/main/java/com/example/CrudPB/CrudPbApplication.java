package com.example.CrudPB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.example.CrudPB.entities")
@EnableJpaRepositories("com.example.CrudPB.repository")
public class CrudPbApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudPbApplication.class, args);
	}

}
