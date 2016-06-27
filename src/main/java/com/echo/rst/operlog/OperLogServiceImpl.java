package com.echo.rst.operlog;

import com.echo.rst.entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Objects;

/**
 * Created by echo on 16-6-26.
 */
@Service
public class OperLogServiceImpl implements OperLogService {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private OperLogRepository operLogRepository;

	private OperLog addOperLog(OperLog operLog) {
		Objects.requireNonNull(operLog);
		log.info("add operLog category={}, state={}, content={}, reason={}",
				operLog.getCategory(), operLog.getState(), operLog.getContent(), operLog.getReason());
		return operLogRepository.save(operLog);
	}

	@Override
	@Transactional
	public OperLog success(Integer category, String content) {
		OperLog operLog = new OperLog(new Date(), category, OperLog.STATE_SUCCESS, content, null);
		try {
			return addOperLog(operLog);
		} catch (Exception e) {
			log.warn("add success operLog failure", e);
		}

		return null;
	}

	@Override
	@Transactional
	public OperLog failure(Integer category, String content, String reason) {
		OperLog operLog = new OperLog(new Date(), category, OperLog.STATE_FAILURE, content, reason);
		try {
			return addOperLog(operLog);
		} catch (Exception e) {
			log.warn("add failure operLog failure", e);
		}

		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<OperLog> queryOperLogs(int page) {
		log.debug("query operLogs by page={}", page);
		Pageable pageable = new PageRequest(page, Result.PAGE_SIZE);
		return operLogRepository.findAll(pageable);
	}
}
