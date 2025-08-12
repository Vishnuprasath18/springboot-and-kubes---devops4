package com.example.vprofile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class VprofileApplication {
    public static void main(String[] args) {
        SpringApplication.run(VprofileApplication.class, args);
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
} 