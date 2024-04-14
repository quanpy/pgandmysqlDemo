package org.example.pgnosqldemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PgNosqlDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PgNosqlDemoApplication.class, args);
    }

}
