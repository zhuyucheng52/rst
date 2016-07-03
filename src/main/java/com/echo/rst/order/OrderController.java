package com.echo.rst.order;

import com.echo.rst.entity.AppException;
import com.echo.rst.entity.Category;
import com.echo.rst.entity.Result;
import com.echo.rst.operlog.OperLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by echo on 16-6-27.
 */
@RestController
@RequestMapping("/order")
public class OrderController {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private OrderService orderService;

	@Autowired
	private OperLogService operLogService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Result<Order> addOrder(@RequestParam Order order) {
		Objects.requireNonNull(order);
		Objects.requireNonNull(order.getMenu());
		log.info("add order gTime={}, comment={}, copies={}, menuId={}",
				order.getgTime(), order.getComment(), order.getCopies(), order.getMenu().getId());
		Result<Order> result = new Result<Order>("add success");
		String contentFailure = "add order comment " + order.getComment() + " failure";
		try {
			Order o = orderService.addOrder(order);
			result.setData(o);
			return result;
		} catch (AppException e) {
			log.warn("add order={} failure", order, e);
			operLogService.failure(Category.ORDER, contentFailure, e.getMessage());
		} catch (Exception e) {
			log.warn("add order={} failure", order, e);
			operLogService.failure(Category.ORDER, contentFailure, null);
		}

		result.setMsg("add order failure");
		return result;
	}

	@RequestMapping(value = "/list/{page}", method = RequestMethod.GET)
	public Result<List<Order>> findByPage(@PathVariable Integer page) {
		Objects.requireNonNull(page);
		log.debug("find order page={}", page);
		Result<List<Order>> result = new Result<List<Order>>("find success");
		String contentFailure = "find order failure";
		List<Order> orderList = new ArrayList<>();
		try {
			Page<Order> orders = orderService.findOrders(page);
			orderList = orders.getContent();
			result.setData(orderList);
			return result;
		} catch (AppException e) {
			log.warn("find order page={} failure", page, e);
			operLogService.failure(Category.USER, contentFailure, e.getMessage());
		} catch (Exception e) {
			log.warn("find order page={} failure", page, e);
			operLogService.failure(Category.USER, contentFailure, null);
		}

		result.setMsg(contentFailure);
		result.setState(Result.State.FAILURE);
		return result;
	}
}
