package com.echo.rst.order;

import org.springframework.data.domain.Page;

/**
 * Created by echo on 16-6-26.
 */
public interface OrderService {
	Order addOrder(Order order);
	Order deleteOrder(Long id);
	Order updateOrder(Order order);
	Page<Order> queryOrders(int page);
}
