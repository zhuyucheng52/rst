package com.echo.rst.menu;

import com.echo.rst.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
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
public class MenuServiceTest {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MenuService menuService;

	private Menu addMenu() {
		Menu u = new Menu("liangpi", "shanxiliangpi", 7.00);
		Menu menu = menuService.addMenu(u);
		logger.debug("add menu name={} success", menu.getName());
		return menu;
	}

	@Test
	@Rollback
	public void testAddMenu() {
		addMenu();
	}

	@Test
	@Rollback
	public void testDeleteMenu() {
		Menu u = addMenu();
		Menu menu = menuService.deleteMenu(u.getId());
		logger.debug("delete menu name={} success", menu.getName());
	}

	@Test
	@Rollback
	public void testFindMenus() {
		addMenu();
		addMenu();
		Page<Menu> page = menuService.findMenus(0);
		Assert.assertEquals(2, page.getTotalElements());
		logger.debug("find menus total element is {}", page.getTotalElements());
	}

	@Test
	@Rollback
	public void testUpdateMenu() {
		Menu u = addMenu();
		u.setName("hdq liangpi");
		Menu menu = menuService.updateMenu(u);
		Assert.assertEquals("hdq liangpi", menu.getName());
		logger.debug("update menu name to {}", menu.getName());
	}

}
