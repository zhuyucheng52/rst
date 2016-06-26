package com.echo.rst.user;

import org.springframework.data.domain.Page;

/**
 * Created by echo on 16-6-26.
 */
public interface UserService {
	User addUser(User user);
	User deleteUser(Long id);
	User updateUser(User user);
	Page<User> queryUsers(int page);
}
