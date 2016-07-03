package com.echo.rst.operlog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * Created by echo on 16-6-26.
 */
public interface OperLogRepository extends JpaRepository<OperLog, Long> {
    @Async
    OperLog save(OperLog log);
    List<OperLog> findByState(Integer state);
}

