package com.echo.rst.user;

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
public class UserServiceImpl implements UserService {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public User addUser(User user) {
		Objects.requireNonNull(user);
		log.info("add user userName={}, loginName={}", user.getUserName(), user.getLoginName());
		return userRepository.save(user);
	}

	@Override
	@Transactional
	public User deleteUser(Long id) {
		Objects.requireNonNull(id);
		log.info("delete user id={}", id);
		User userPo = userRepository.findOne(id);
		if (userPo == null) {
			log.warn("user id={} is not exists", id);
			throw new AppException(ErrorCodes.USER_NOT_EXISTS);
		}

		log.info("delete user id={}, userName={}, loginName={}", id, userPo.getUserName(), userPo.getLoginName());
		userRepository.delete(id);
		return userPo;
	}

	@Override
	@Transactional
	public User updateUser(User user) {
		Objects.requireNonNull(user);
		Long id = user.getId();
		String userName = user.getUserName();
		String loginName = user.getLoginName();
		log.info("update user id={}, userName={}, loginNmae={}", id, userName, loginName);
		User userPo = userRepository.findOne(id);
		if (userPo == null) {
			log.warn("user id={}, userName={}, loginName={} is not exists", id, userName, loginName);
			throw new AppException(ErrorCodes.USER_NOT_EXISTS);
		}

		log.info("original user id={}, userName={}, loginName={}", userPo.getId(), userPo.getUserName(), userPo.getLoginName());
		return userRepository.save(user);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<User> findUsers(int page) {
		log.debug("find users by page={}", page);
		Pageable pageable = new PageRequest(page, Result.PAGE_SIZE);
		return userRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public User login(User user) {
		Objects.requireNonNull(user);
		String loginName = user.getLoginName();
		String password = user.getPassword();
		User userPo = userRepository.findByLoginName(loginName);
		if (userPo == null) {
			log.warn("user loginName={} is not exists", loginName);
			throw new AppException(ErrorCodes.USER_NOT_EXISTS);
		}

		if (password == null || !password.equals(userPo.getPassword())) {
			log.warn("user loginName={} password is incorrect");
			throw new AppException(ErrorCodes.USER_PASSWORD_INCORRECT);
		}

		return userPo;
	}
}
