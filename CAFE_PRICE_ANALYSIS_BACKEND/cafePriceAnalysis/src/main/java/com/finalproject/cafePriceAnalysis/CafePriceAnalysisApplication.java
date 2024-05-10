package com.finalproject.cafePriceAnalysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
	    "com.finalproject.cafePriceAnalysis"
	})
public class CafePriceAnalysisApplication {

	public static void main(String[] args) {
		SpringApplication.run(CafePriceAnalysisApplication.class, args);
	}

}
