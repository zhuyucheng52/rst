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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by echo on 16-7-3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@Transactional
public class UserRepositoryTest {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserRepository userRepository;

	@Test
	@Rollback
	public void testFindAll() {
		saveUser();
		saveUser();
		List<User> userList = userRepository.findAll();
		Assert.assertEquals(2, userList.size());
		logger.debug("user list size is {}", userList.size());
	}

	@Test
	@Rollback
	public void testSave() {
		saveUser();
	}

	@Test
	@Rollback
	public void testDelete() {
		User u = saveUser();

		userRepository.delete(u);
		logger.debug("username={} delete success", u.getUserName());
	}

	@Test
	@Rollback
	public void testFindByLoginName() {
		User u = saveUser();
		User user = userRepository.findByLoginName(u.getLoginName());
		logger.debug("find user loginname={}", user.getLoginName());
	}

	private User saveUser() {
		User user = new User("zyc", "zyc", "zyc", "desc");
		User u = userRepository.save(user);
		logger.debug("username={} save success", user.getUserName());
		return u;
	}
}
