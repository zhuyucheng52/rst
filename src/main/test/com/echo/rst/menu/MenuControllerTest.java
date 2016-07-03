package com.echo.rst.menu;

/**
 * Created by echo on 16-7-3.
 */

import com.echo.rst.Application;
import com.echo.rst.entity.Result;
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
public class MenuControllerTest {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MenuController menuController;

	@Autowired
	private MenuService menuService;

	@Test
	@Rollback
	public void testFindByPage() throws Exception {
		Result<List<Menu>> page = menuController.findByPage(0);
		Assert.assertEquals(Result.State.SUCCESS, page.getState());
		logger.debug("find menu by page success");
	}

	@Test
	@Rollback
	public void testAddMenu() {
		Menu m = new Menu("liangpi", "shanxiliangpi", 7.00);
		Result<Menu> result = menuController.addMenu(m);
		Assert.assertEquals(Result.State.SUCCESS, result.getState());
		logger.debug("add menu name={} success", m.getName());
	}

	@Test
	@Rollback
	public void testDeleteMenu() {
		Menu m = addMenu();
		Result<Menu> result = menuController.deleteMenu(m.getId());
		Assert.assertEquals(Result.State.SUCCESS, result.getState());
		logger.debug("delete menu id={}, name={}", m.getId(), m.getName());
	}

	@Test
	@Rollback
	public void testFindById() {
		Menu m = addMenu();
		Result<Menu> result = menuController.findById(m.getId());
		Assert.assertEquals(Result.State.SUCCESS, result.getState());
		logger.debug("find menu by id={} success", m.getId());
	}

	private Menu addMenu() {
		Menu m = new Menu("liangpi", "shanxiliangpi", 7.00);
		Menu menu = menuService.addMenu(m);
		logger.debug("add menu id={}, name={} success", m.getId(), m.getName());
		return m;
	}
}
