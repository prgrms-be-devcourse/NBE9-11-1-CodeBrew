package com.back.code_brew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CodeBrewApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodeBrewApplication.class, args);
    }

}
