package com.echo.rst.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by echo on 16-6-26.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUserName(String userName);
}

