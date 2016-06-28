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

	@RequestMapping(value = "/list/{page}", method = RequestMethod.GET)
	public Result<List<Order>> querys(@PathVariable Integer page) {
		Objects.requireNonNull(page);
		log.debug("query order page={}", page);
		Result<List<Order>> result = new Result<List<Order>>("query success");
		String contentFailure = "query order failure";
		List<Order> orderList = new ArrayList<>();
		try {
			Page<Order> orders = orderService.queryOrders(page);
			orders.map((u) -> {
				orderList.add(u);
				return u;
			});
			log.debug("total items=" + orders.getTotalElements());
			log.debug("total pages=" + orders.getTotalPages());
			result.setData(orderList);
			return result;
		} catch (AppException e) {
			log.warn("query order page={} failure", page, e);
			operLogService.failure(Category.USER, contentFailure, e.getMessage());
		} catch (Exception e) {
			log.warn("query order page={} failure", page, e);
			operLogService.failure(Category.USER, contentFailure, null);
		}

		result.setMsg(contentFailure);
		result.setState(Result.State.FAILURE);
		return result;
	}
}
