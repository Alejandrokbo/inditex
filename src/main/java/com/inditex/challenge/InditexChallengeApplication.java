package com.inditex.challenge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InditexChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(InditexChallengeApplication.class, args);

        Logger logger = LogManager.getLogger(InditexChallengeApplication.class);
        logger.info("****************************************************************************************");
        logger.info("Application started :), check URL: http://localhost:8080/status to check server online");
        logger.info("****************************************************************************************");
        logger.info("Check H2 database in URL: http://localhost:8080/h2-console with the following parameters:");
        logger.info("Username: sa");
        logger.info("Password: password");
        logger.info("===========================================================================================");

    }

}
