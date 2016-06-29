package com.echo.rst.menu;

import org.springframework.data.domain.Page;

/**
 * Created by echo on 16-6-26.
 */
public interface MenuService {
	Menu addMenu(Menu menu);
	Menu deleteMenu(Long id);
	Menu updateMenu(Menu menu);
	Page<Menu> queryMenus(int page);
	Menu queryById(Long id);
}
