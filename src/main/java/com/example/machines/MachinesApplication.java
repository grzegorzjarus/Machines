package com.example.machines;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication(exclude =  {DataSourceAutoConfiguration.class })
@SpringBootApplication

public class MachinesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MachinesApplication.class, args);
	}

}
