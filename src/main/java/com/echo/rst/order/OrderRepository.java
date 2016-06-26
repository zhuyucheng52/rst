package com.echo.rst.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by echo on 16-6-26.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
   List<Order> findByNum(int i);
}

