package com.echo.rst.user;

import com.echo.rst.entity.AppException;
import com.echo.rst.entity.Category;
import com.echo.rst.entity.Result;
import com.echo.rst.operlog.OperLogService;
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
@RequestMapping("/user")
public class UserController {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserService userService;

	@Autowired
	private OperLogService operLogService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Result<User> login(@RequestParam User user) {
		Objects.requireNonNull(user);
		Result<User> result = new Result<User>("login success");
		log.info("user loginName={} login", user.getLoginName());
		String contentFailure = "user " + user.getLoginName() + " login failure";
		try {
			User u = userService.login(user);
			String contentSuccess = "user " + user.getLoginName() + " login success";
			operLogService.success(Category.USER, contentSuccess);
			result.setData(u);
			return result;
		} catch (AppException e) {
			log.warn("user loginName={} login failure", user.getLoginName(), e);
			operLogService.failure(Category.USER, contentFailure, e.getMessage());
		} catch (Exception e) {
			log.warn("user loginName={} login failure", user.getLoginName(), e);
			operLogService.failure(Category.USER, contentFailure, null);
		}

		result.setMsg("login failure");
		result.setState(Result.State.FAILURE);
		return result;
	}

	@RequestMapping(value = "/list/{page}", method = RequestMethod.GET)
	public Result<List<User>> queryByPage(@PathVariable Integer page) {
		Objects.requireNonNull(page);
		log.debug("query user page={}", page);
		Result<List<User>> result = new Result<List<User>>("query success");
		String contentFailure = "query user failure";
		List<User> userList = new ArrayList<>();
		try {
			Page<User> users = userService.findUsers(page);
			userList = users.getContent();
			result.setData(userList);
			return result;
		} catch (AppException e) {
			log.warn("query user page={} failure", page, e);
			operLogService.failure(Category.USER, contentFailure, e.getMessage());
		} catch (Exception e) {
			log.warn("query user page={} failure", page, e);
			operLogService.failure(Category.USER, contentFailure, null);
		}

		result.setMsg(contentFailure);
		result.setState(Result.State.FAILURE);
		return result;
	}
}
