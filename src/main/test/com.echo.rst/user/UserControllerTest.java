package com.echo.rst.user;

/**
 * Created by echo on 16-7-3.
 */

import com.echo.rst.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest({"server.port=0", "management.port=0"})
@Transactional
public class UserControllerTest {
	private Logger logger = LoggerFactory.getLogger(getClass());

	private MockMvc mockMvc;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;

	@Autowired
	private UserController userController;

	@Autowired
	private UserService userService;

	@Before
	public void before() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
		this.request = new MockHttpServletRequest();
		this.response = new MockHttpServletResponse();
	}

	@Test
	public void testFindByPage() throws Exception {
		long page = 0;
		this.mockMvc.perform(get("/user/list/" + page))
				.andExpect(status().isOk());
		logger.debug("find user by page success");
	}

	@Test
	public void testLogin() throws Exception {
		User u = addUser();
		this.mockMvc.perform(post("/user/login")
//				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("logingName", "zyc")
				.param("password", "zyc"))
				.andExpect(status().isOk());

		logger.debug("loginname={} login success", u.getLoginName());
	}

	private User addUser() {
		User u = new User("zyc", "zyc", "zyc", "desc");
		User user = userService.addUser(u);
		logger.debug("username={} add success", user.getUserName());
		return user;
	}
}
