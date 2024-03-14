package com.inditex.challenge.Controller;

import com.inditex.challenge.controller.api.ServerStatus;
import com.inditex.challenge.dto.ResponseMessageDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertSame;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServerStatusTest {

    @Autowired
    private ServerStatus serverStatus;

    @Test
    void contextLoads() {
        assertThat(serverStatus).isNotNull();
    }

    @Test
    void greetingShouldReturnDefaultMessage() {
        ResponseEntity<ResponseMessageDTO> result = this.serverStatus.hello();
        assertSame(result.getStatusCode(), HttpStatus.OK);
        assertSame(Objects.requireNonNull(result.getBody()).statusMessage, "OK");
        assertSame(result.getBody().message, "All good");

    }
}
