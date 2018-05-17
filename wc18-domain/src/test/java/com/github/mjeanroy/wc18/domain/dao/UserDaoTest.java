/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.dao;

import com.github.mjeanroy.wc18.domain.models.User;

import javax.inject.Inject;

public class UserDaoTest extends AbstractReadOnlyDaoTest<User, UserDao> {

	@Inject
	private UserDao userRepository;

	@Override
	Class<User> getEntityClass() {
		return User.class;
	}

	@Override
	UserDao getDao() {
		return userRepository;
	}
}
