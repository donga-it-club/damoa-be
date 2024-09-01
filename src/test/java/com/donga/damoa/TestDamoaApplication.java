package com.donga.damoa;

import org.springframework.boot.SpringApplication;

public class TestDamoaApplication {

	public static void main(String[] args) {
		SpringApplication.from(DamoaApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
