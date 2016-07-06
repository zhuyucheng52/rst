package com.echo.rst.operlog;

import com.echo.rst.domain.AppException;
import com.echo.rst.domain.Category;
import com.echo.rst.domain.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by echo on 16-6-27.
 */
@RestController
@RequestMapping("/operlog")
public class OperLogController {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private OperLogService operLogService;

	@RequestMapping(value = "/list/{page}", method = RequestMethod.GET)
	public Result<List<OperLog>> queryByPage(@PathVariable Integer page) {
		Objects.requireNonNull(page);
		log.debug("query operLog page={}", page);
		Result<List<OperLog>> result = new Result<List<OperLog>>("query success");
		String contentFailure = "query operLog failure";
		List<OperLog> operLogList = new ArrayList<>();
		try {
			Page<OperLog> operLogs = operLogService.queryOperLogs(page);
			operLogList = operLogs.getContent();
			result.setData(operLogList);
			return result;
		} catch (AppException e) {
			log.warn("query operLog page={} failure", page, e);
			operLogService.failure(Category.USER, contentFailure, e.getMessage());
		} catch (Exception e) {
			log.warn("query operLog page={} failure", page, e);
			operLogService.failure(Category.USER, contentFailure, null);
		}

		result.setMsg(contentFailure);
		result.setState(Result.State.FAILURE);
		return result;
	}
}
