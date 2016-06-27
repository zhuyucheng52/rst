package com.echo.rst.user;

import com.echo.rst.entity.AppException;
import com.echo.rst.entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Result<User> login(@RequestParam User user) {
		Objects.requireNonNull(user);
		Result<User> result = new Result<User>("login success");
		log.info("user loginName={} login", user.getLoginName());

		try {
			userService.login(user);
			return result;
		} catch (AppException e) {
			log.warn("user loginName={} login failure", user.getLoginName(), e);
		} catch (Exception e) {
			log.warn("user loginName={} login failure", user.getLoginName(), e);
		}

		result.setMsg("login failure");
		result.setState(Result.State.FAILURE);
		return result;
	}
}
