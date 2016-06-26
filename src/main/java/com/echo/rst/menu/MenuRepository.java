package com.echo.rst.menu;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by echo on 16-6-26.
 */
public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByName(String menu01);
}

