package com.example.inhamonchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class InhaMonChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(InhaMonChallengeApplication.class, args);
    }

}
