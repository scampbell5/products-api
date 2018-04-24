package com.sean.productsapi;

import com.sean.productsapi.loader.CSVProductLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

@Slf4j
@SpringBootApplication
public class ProductsApiApplication {

    @Configuration
    @Profile("h2")
    private class SeedDataConfiguration {

    	private final CSVProductLoader csvProductLoader;
		private final String filePath;

    	public SeedDataConfiguration(CSVProductLoader csvProductLoader, @Value("${seedData.filePath}") String filePath) {
    		this.csvProductLoader = csvProductLoader;
    		this.filePath = filePath;
		}

    	@PostConstruct
        public void init() {
			csvProductLoader.load(filePath);
		}
    }

	public static void main(String[] args) {
		SpringApplication.run(ProductsApiApplication.class, args);
	}
}