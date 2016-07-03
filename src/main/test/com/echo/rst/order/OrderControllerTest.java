package com.echo.rst.order;

/**
 * Created by echo on 16-7-3.
 */

import com.echo.rst.Application;
import com.echo.rst.entity.Result;
import com.echo.rst.menu.Menu;
import com.echo.rst.menu.MenuService;
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

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
//@WebIntegrationTest({"server.port=0", "management.port=0"})
@Transactional
public class OrderControllerTest {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private OrderController orderController;

	@Autowired
	private OrderService orderService;

	@Autowired
	private MenuService menuService;

	@Test
	@Rollback
	public void testFindByPage() throws Exception {
		Result<List<Order>> page = orderController.findByPage(0);
		Assert.assertEquals(Result.State.SUCCESS, page.getState());
		logger.debug("find order by page success");
	}

	@Test
	@Rollback
	public void testAddOrder() {
		Menu m = new Menu("liangpi", "shanxiliangpi", 7.00);
		Menu menu = menuService.addMenu(m);
		Order o = new Order("", new Date(), Order.STATE_FINISHED, 2, 20);
		o.setMenu(m);
		Result<Order> result = orderController.addOrder(o);
		Assert.assertEquals(Result.State.SUCCESS, result.getState());
		logger.debug("add order num={} success", o.getNum());
	}

	private Order addOrder() {
		Menu m = new Menu("liangpi", "shanxiliangpi", 7.00);
		Menu menu = menuService.addMenu(m);
		Order o = new Order("", new Date(), Order.STATE_FINISHED, 2, 20);
		o.setMenu(menu);
		Order order = orderService.addOrder(o);
		logger.debug("add order num={} success", o.getNum());
		return o;
	}
}
