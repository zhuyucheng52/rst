package com.echo.rst.order;

import com.echo.rst.Application;
import com.echo.rst.menu.Menu;
import com.echo.rst.menu.MenuService;
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

import java.util.Date;

/**
 * Created by echo on 16-7-3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@Transactional
public class OrderServiceTest {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private OrderService orderService;

	@Autowired
	private MenuService menuService;

	private Order addOrder() {
		Menu m = new Menu("liangpi", "shanxiliangpi", 7.00);
		Menu menu = menuService.addMenu(m);
		Order o = new Order("", new Date(), Order.STATE_FINISHED, 1, 2);
		o.setMenu(menu);
		Order order = orderService.addOrder(o);
		logger.debug("add order num={} success", order.getNum());
		return order;
	}

	@Test
	@Rollback
	public void testAddOrder() {
		addOrder();
	}

	@Test
	@Rollback
	public void testDeleteOrder() {
		Order u = addOrder();
		Order order = orderService.deleteOrder(u.getId());
		logger.debug("delete order num={} success", order.getNum());
	}

	@Test
	@Rollback
	public void testFindOrders() {
		addOrder();
		addOrder();
		Page<Order> page = orderService.findOrders(0);
		Assert.assertEquals(2, page.getTotalElements());
		logger.debug("find orders total element is {}", page.getTotalElements());
	}

	@Test
	@Rollback
	public void testUpdateOrder() {
		Order u = addOrder();
		u.setState(Order.STATE_CANCEL);
		Order order = orderService.updateOrder(u);
		Assert.assertTrue(Order.STATE_CANCEL == order.getState());
		logger.debug("update order state to {}", order.getState());
	}

}
