package ec.edu.espe.computerstore.repository;

import ec.edu.espe.computerstore.model.Customer;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Francisco Demera, Bytes & Fabric Devs, @ESPE
 */
public interface CustomerRepository extends MongoRepository<Customer, String> {

    Optional<Customer> findById(int id);

    void deleteById(int id);
}
