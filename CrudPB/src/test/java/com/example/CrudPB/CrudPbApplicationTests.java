package com.example.CrudPB;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootTest
@EntityScan("com.example.CrudPB.entities")
@EnableJpaRepositories("com.example.CrudPB.repository")
class CrudPbApplicationTests {

	@Test
	void contextLoads() {
	}

}
