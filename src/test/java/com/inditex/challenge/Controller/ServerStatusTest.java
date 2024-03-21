package com.inditex.challenge.Controller;

import com.inditex.challenge.controller.api.ServerStatus;
import com.inditex.challenge.dto.ResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertSame;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServerStatusTest {

    Logger logger = Logger.getLogger(ServerStatusTest.class.getName());

    @Autowired
    private ServerStatus serverStatus;

    @Test
    void contextLoads() {
        assertThat(serverStatus).isNotNull();
    }

    @Test
    void greetingShouldReturnDefaultMessage() {
        ResponseEntity<ResponseDTO> result = this.serverStatus.hello();

        logger.info("Response: " + result.toString());

        assertSame(result.getStatusCode(), HttpStatus.OK);
        assertSame(Objects.requireNonNull(result.getBody()).statusMessage, "OK");
        assertSame(result.getBody().message, "All good");

    }
}
