package com.computerstore.repository;

import com.computerstore.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    @Query("{ 'id': ?0 }")
    Optional<Customer> findByCustomerId(Integer id);
}
