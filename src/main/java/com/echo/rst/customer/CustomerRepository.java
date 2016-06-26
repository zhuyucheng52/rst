package com.echo.rst.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by echo on 16-6-26.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByLastName(String lastName);
}

