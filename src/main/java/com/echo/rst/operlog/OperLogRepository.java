package com.echo.rst.operlog;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by echo on 16-6-26.
 */
public interface OperLogRepository extends JpaRepository<OperLog, Long> {
    List<OperLog> findByState(Integer state);
}

