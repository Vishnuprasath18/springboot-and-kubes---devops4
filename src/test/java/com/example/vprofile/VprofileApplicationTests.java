package com.example.vprofile;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VprofileApplicationTests {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void healthEndpointReturnsOK() {
        String body = this.restTemplate.getForObject("/health", String.class);
        assertThat(body).isEqualTo("OK");
    }
} 