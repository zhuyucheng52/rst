package com.echo.rst.menu;

import com.echo.rst.domain.AppException;
import com.echo.rst.domain.ErrorCodes;
import com.echo.rst.domain.Result;
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
public class MenuServiceImpl implements MenuService {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private MenuRepository menuRepository;

	@Override
	@Transactional
	public Menu addMenu(Menu menu) {
		Objects.requireNonNull(menu);
		log.info("add menu name={}", menu.getName());
		return menuRepository.save(menu);
	}

	@Override
	@Transactional
	public Menu deleteMenu(Long id) {
		Objects.requireNonNull(id);
		log.info("delete menu id={}", id);
		Menu menuPo = menuRepository.findOne(id);
		if (menuPo == null) {
			log.warn("menu id={} is not exists", id);
			throw new AppException(ErrorCodes.USER_NOT_EXISTS);
		}

		log.info("delete menu id={}, name={}", menuPo.getId(), menuPo.getName());
		menuRepository.delete(id);
		return menuPo;
	}

	@Override
	@Transactional
	public Menu updateMenu(Menu menu) {
		Objects.requireNonNull(menu);
		Long id = menu.getId();
		log.info("update menu id={}, name={}", id, menu.getName());
		Menu menuPo = menuRepository.findOne(id);
		if (menuPo == null) {
			log.warn("menu id={}, name={} is not exists", id, menu.getId(), menu.getName());
			throw new AppException(ErrorCodes.MENU_NOT_EXISTS);
		}

		log.info("original menu id={}, name={}", menuPo.getId(), menuPo.getName());
		return menuRepository.save(menu);
	}

	@Override
	public Page<Menu> findMenus(int page) {
		log.debug("query menus by page={}", page);
		Pageable pageable = new PageRequest(page, Result.PAGE_SIZE);
		return menuRepository.findAll(pageable);
	}

	@Override
	public Menu findById(Long id) {
		log.debug("query menu by id={}", id);
		return menuRepository.findOne(id);
	}
}
