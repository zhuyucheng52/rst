package com.echo.rst.user;

import com.echo.rst.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by echo on 16-7-3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@Transactional
public class UserServiceTest {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserService userService;

	private User addUser() {
		User u = new User("zyc", "zyc", "zyc", "desc");
		User user = userService.addUser(u);
		logger.debug("username={} add success", user.getUserName());
		return user;
	}

	@Test
	@Rollback
	public void testAddUser() {
		addUser();
	}

	@Test
	@Rollback
	public void testLogin() {
		User u = addUser();
		User user = userService.login(u);
		logger.debug("loginname={} login success", user.getLoginName());
	}

	@Test
	@Rollback
	public void testDeleteUser() {
		User u = addUser();
		User user = userService.deleteUser(u.getId());
		logger.debug("delete username={} success", user.getUserName());
	}

	@Test
	@Rollback
	public void testFindUsers() {
		addUser();
		addUser();
		Page<User> page = userService.findUsers(0);
		Assert.assertEquals(2, page.getTotalElements());
		logger.debug("find users total element is {}", page.getTotalElements());
	}

	@Test
	@Rollback
	public void testUpdateUser() {
		User u = addUser();
		u.setLoginName("hdq");
		User user = userService.updateUser(u);
		Assert.assertEquals("hdq", user.getLoginName());
		logger.debug("update loginname to {}", user.getLoginName());
	}

}
