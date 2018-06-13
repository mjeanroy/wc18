/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.services;

import com.github.mjeanroy.wc18.domain.dao.UserDao;
import com.github.mjeanroy.wc18.domain.exceptions.UserNotFoundException;
import com.github.mjeanroy.wc18.domain.models.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Optional;

@Service
public class UserService {

	private final UserDao userDao;
	private final PasswordService passwordService;

	@Inject
	public UserService(PasswordService passwordService, UserDao userDao) {
		this.userDao = userDao;
		this.passwordService = passwordService;
	}

	/**
	 * Find all users.
	 *
	 * @return Users.
	 */
	@Transactional(readOnly = true)
	public Iterable<User> findAll() {
		return userDao.findAll();
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

	/**
	 * Find user by login/password.
	 *
	 * @param login The login.
	 * @param password The password (in plain text).
	 * @return The user, if it exists.
	 */
	@Transactional(readOnly = true)
	public Optional<User> findByLoginAndPassword(String login, String password) {
		Optional<User> optUser = userDao.findByLogin(login);
		if (!optUser.isPresent()) {
			// In theory, we should try a "random" match here to avoid timing attack.
			// This is a simple app, avoid that.
			return Optional.empty();
		}

		User user = optUser.get();
		String hashPassword = user.getPassword();
		boolean match = passwordService.match(password, hashPassword);
		return match ? optUser : Optional.empty();
	}

	/**
	 * Update user password.
	 *
	 * @param user The user.
	 * @param newPassword The new password.
	 */
	@Transactional
	public void updatePassword(User user, String newPassword) {
		String hashPassword = passwordService.encode(newPassword);
		user.updatePassword(hashPassword);
		userDao.save(user);
	}

	/**
	 * Create new user account with given login/password.
	 *
	 * @param login The account login.
	 * @param password The account password.
	 * @return The created user.
	 */
	@Transactional
	public User create(String login, String password) {
		String hashPassword = passwordService.encode(password);
		User user = new User(login, hashPassword);
		return userDao.save(user);
	}

	/**
	 * Remove user account.
	 *
	 * @param id The user identifier.
	 * @throws UserNotFoundException If user does not exist.
	 */
	@Transactional
	public void remove(String id) {
		User user = userDao.findOne(id).orElseThrow(() ->
			new UserNotFoundException(id)
		);

		userDao.delete(user);
	}
}
