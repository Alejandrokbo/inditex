package com.inditex.challenge;

import lombok.Value;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InditexChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(InditexChallengeApplication.class, args);

		Logger logger = LogManager.getLogger(InditexChallengeApplication.class);
		logger.info("Application started");
		logger.info("===========================================================================================");
		logger.info("You can access the API documentation at http://localhost:8080/swagger-ui.html");
		logger.info("===========================================================================================");
		logger.info("Check H2 database in URL: http://localhost:8080/h2-console with the following parameters:");
		logger.info("Username: sa");
		logger.info("Password: password");
		logger.info("===========================================================================================");

	}

}
