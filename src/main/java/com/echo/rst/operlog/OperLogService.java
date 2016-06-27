package com.echo.rst.operlog;

import org.springframework.data.domain.Page;

/**
 * Created by echo on 16-6-26.
 */
public interface OperLogService {
	OperLog success(Integer category, String content);
	OperLog failure(Integer category, String content, String reason);
	Page<OperLog> queryOperLogs(int page);
}
