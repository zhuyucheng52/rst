package com.echo.rst.user;

/**
 * Created by echo on 16-7-3.
 */

import com.echo.rst.Application;
import com.echo.rst.domain.Result;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
//@WebIntegrationTest({"server.port=0", "management.port=0"})
@Transactional
public class UserControllerTest {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserController userController;

	@Autowired
	private UserService userService;

	@Test
	@Rollback
	public void testFindByPage() throws Exception {
		Result<List<User>> page = userController.findByPage(0);
		Assert.assertEquals(Result.State.SUCCESS, page.getState());
		logger.debug("find user by page success");
	}

	@Test
	@Rollback
	public void testLogin() throws Exception {
		User u = addUser();
		Result<User> page = userController.login(u);
		Assert.assertEquals(Result.State.SUCCESS, page.getState());
		logger.debug("loginname={} login success", u.getLoginName());
	}

	private User addUser() {
		User u = new User("zyc", "zyc", "zyc", "desc");
		User user = userService.addUser(u);
		logger.debug("username={} add success", user.getUserName());
		return user;
	}
}
