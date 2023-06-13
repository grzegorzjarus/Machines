package com.example.machines;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude =  {DataSourceAutoConfiguration.class })
public class MachinesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MachinesApplication.class, args);
	}

}
