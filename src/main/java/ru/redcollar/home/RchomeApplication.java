package ru.redcollar.home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class RchomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(RchomeApplication.class, args);
    }

}
