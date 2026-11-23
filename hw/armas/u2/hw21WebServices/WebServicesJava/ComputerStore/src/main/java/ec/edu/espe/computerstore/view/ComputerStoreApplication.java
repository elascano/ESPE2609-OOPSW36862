package ec.edu.espe.computerstore.view;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 *
 * @author Francisco Demera, Bytes & Fabric Devs, @ESPE
 */
@SpringBootApplication(scanBasePackages = "ec.edu.espe.computerstore")
@EnableMongoRepositories(basePackages = "ec.edu.espe.computerstore.repository")
public class ComputerStoreApplication{

    public static void main(String[] args) {
        SpringApplication.run(ComputerStoreApplication.class, args);
    }
}