/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.services;

import com.github.mjeanroy.wc18.domain.dao.UserDao;
import com.github.mjeanroy.wc18.domain.models.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Optional;

@Service
public class UserService {

	private final UserDao userDao;

	@Inject
	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * Find user from login.
	 *
	 * @param login The login.
	 * @return The user, if it exists.
	 */
	@Transactional(readOnly = true)
	public Optional<User> findByLogin(String login) {
		return userDao.findByLogin(login);
	}
}
