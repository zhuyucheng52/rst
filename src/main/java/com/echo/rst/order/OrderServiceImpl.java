package com.echo.rst.order;

import com.echo.rst.entity.AppException;
import com.echo.rst.entity.ErrorCodes;
import com.echo.rst.entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * Created by echo on 16-6-26.
 */
@Service
public class OrderServiceImpl implements OrderService {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private OrderRepository orderRepository;

	@Override
	@Transactional
	public Order addOrder(Order order) {
		Objects.requireNonNull(order);
		log.info("add order gTime={}, state={}, copies={}, num={}",
				order.getgTime(), order.getState(), order.getCopies(), order.getNum());
		return orderRepository.save(order);
	}

	@Override
	@Transactional
	public Order deleteOrder(Long id) {
		Objects.requireNonNull(id);
		log.info("delete order id={}", id);
		Order orderPo = orderRepository.findOne(id);
		if (orderPo == null) {
			log.warn("order id={} is not exists", id);
			throw new AppException(ErrorCodes.ORDER_NOT_EXISTS);
		}

		log.info("delete order id={}, gTime={}, state={}, copies={}, num={}",
				orderPo.getId(), orderPo.getgTime(), orderPo.getState(), orderPo.getCopies(), orderPo.getNum());
		orderRepository.delete(id);
		return orderPo;
	}

	@Override
	@Transactional
	public Order updateOrder(Order order) {
		Objects.requireNonNull(order);
		Long id = order.getId();
		log.info("update order id={}, gTime={}, state={}, copies={}, num={}",
				order.getId(), order.getgTime(), order.getState(), order.getCopies(), order.getNum());
		Order orderPo = orderRepository.findOne(id);
		if (orderPo == null) {
			log.warn("order id={} is not exists", order.getId());
			throw new AppException(ErrorCodes.ORDER_NOT_EXISTS);
		}

		log.info("original order id={}, gTime={}, state={}, copies={}, num={}",
				orderPo.getId(), orderPo.getgTime(), orderPo.getState(), orderPo.getCopies(), orderPo.getNum());
		return orderRepository.save(order);
	}

	@Override
	public Page<Order> queryOrders(int page) {
		log.debug("query orders by page={}", page);
		Pageable pageable = new PageRequest(page, Result.PAGE_SIZE);
		return orderRepository.findAll(pageable);
	}
}
