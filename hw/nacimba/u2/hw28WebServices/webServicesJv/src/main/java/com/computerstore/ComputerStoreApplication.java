package com.computerstore;

import org.bson.Document;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class ComputerStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComputerStoreApplication.class, args);
        System.out.println("Mateo Nacimba's Computer Store running on port 3014");
    }

    @Bean
    CommandLineRunner checkConnection(MongoTemplate mongoTemplate) {
        return args -> {
            try {
                mongoTemplate.getDb().runCommand(new Document("ping", 1));
                System.out.println("Mateo Nacimba´s System connected to MOngoDB Database");
            } catch (Exception error) {
                System.err.println(error.getMessage());
            }
        };
    }
}
