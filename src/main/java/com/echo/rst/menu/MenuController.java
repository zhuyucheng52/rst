package com.echo.rst.menu;

import com.echo.rst.entity.AppException;
import com.echo.rst.entity.Category;
import com.echo.rst.entity.Result;
import com.echo.rst.operlog.OperLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * Created by echo on 16-6-27.
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private MenuService menuService;

	@Autowired
	private OperLogService operLogService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Result<Menu> addMenu(@RequestBody Menu menu) {
		Objects.requireNonNull(menu);
		Result<Menu> result = new Result<Menu>("add menu success");
		String name = menu.getName();
		String contentFailure = "add menu name=" + name + " failure";
		try {
			Menu m = menuService.addMenu(menu);
			String contentSuccess = "add menu name=" + name + " success";
			operLogService.success(Category.MENU, contentSuccess);
			result.setData(m);
			return result;
		} catch (AppException e) {
			log.warn("add menu name={} failure", name, e);
			operLogService.failure(Category.MENU, contentFailure, e.getMessage());
		} catch (Exception e) {
			log.warn("add menu name={} failure", name, e);
			operLogService.failure(Category.MENU, contentFailure, null);
		}

		result.setState(Result.State.FAILURE);
		result.setMsg("add menu failure");
		return result;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public Result<Menu> deleteMenu(@RequestParam Long id) {
		Objects.requireNonNull(id);
		Result<Menu> result = new Result<Menu>("delete menu success");
		String contentFailure = "delete menu id=" + id + " failure";
		try {
			Menu m = menuService.deleteMenu(id);
			String contentSuccess = "delete menu id=" + id + " success";
			operLogService.success(Category.MENU, contentSuccess);
			result.setData(m);
			return result;
		} catch (AppException e) {
			log.warn("delete menu id={} failure", id, e);
			operLogService.failure(Category.MENU, contentFailure, e.getMessage());
		} catch (Exception e) {
			log.warn("delete menu id={} failure", id, e);
			operLogService.failure(Category.MENU, contentFailure, null);
		}

		result.setState(Result.State.FAILURE);
		result.setMsg("delete menu failure");
		return result;
	}
}
