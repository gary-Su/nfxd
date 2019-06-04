package com.edu.nfxd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages="com.edu.nfxd.dao")
public class NfxdApplication {

	public static void main(String[] args) {
		SpringApplication.run(NfxdApplication.class, args);
	}

}
