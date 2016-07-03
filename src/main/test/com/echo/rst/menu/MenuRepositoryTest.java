package com.echo.rst.menu;

import com.echo.rst.Application;
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

/**
 * Created by echo on 16-7-3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@Transactional
public class MenuRepositoryTest {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MenuRepository menuRepository;

	@Test
	@Rollback
	public void testFindAll() {
		saveMenu();
		saveMenu();
		List<Menu> menuList = menuRepository.findAll();
		Assert.assertEquals(2, menuList.size());
		logger.debug("menu list size is {}", menuList.size());
	}

	@Test
	@Rollback
	public void testSave() {
		saveMenu();
	}

	@Test
	@Rollback
	public void testDelete() {
		Menu u = saveMenu();

		menuRepository.delete(u);
		logger.debug("menu name={} delete success", u.getName());
	}

	@Test
	@Rollback
	public void testFindByName() {
		Menu m = saveMenu();
		List<Menu> menuList = menuRepository.findByName(m.getName());
		logger.debug("find menu list size={}", menuList.size());
	}

	private Menu saveMenu() {
		Menu menu = new Menu("liangpi", "shanxiliangpi", 7.00);
		Menu u = menuRepository.save(menu);
		logger.debug("menu name={} save success", menu.getName());
		return u;
	}
}
