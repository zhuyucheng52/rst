package com.echo.rst.order;

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

import java.util.Date;
import java.util.List;

/**
 * Created by echo on 16-7-3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@Transactional
public class OrderRepositoryTest {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private OrderRepository orderRepository;

	@Test
	@Rollback
	public void testFindAll() {
		saveOrder();
		saveOrder();
		List<Order> orderList = orderRepository.findAll();
		Assert.assertEquals(2, orderList.size());
		logger.debug("order list size is {}", orderList.size());
	}

	@Test
	@Rollback
	public void testSave() {
		saveOrder();
	}

	@Test
	@Rollback
	public void testDelete() {
		Order u = saveOrder();

		orderRepository.delete(u);
		logger.debug("order num={} delete success", u.getNum());
	}

	@Test
	@Rollback
	public void testFindByNum() {
		Order m = saveOrder();
		List<Order> orderList = orderRepository.findByNum(m.getNum());
		Assert.assertEquals(1, orderList.size());
		logger.debug("find order list size={}", orderList.size());
	}

	private Order saveOrder() {
		Order order = new Order("", new Date(), Order.STATE_FINISHED, 2, 10);
		Order u = orderRepository.save(order);
		logger.debug("order num={} save success", order.getNum());
		return u;
	}
}
