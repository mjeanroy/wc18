/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.dao;

import com.github.mjeanroy.wc18.domain.models.User;
import com.github.mjeanroy.wc18.domain.models.User.Role;
import com.github.mjeanroy.wc18.domain.tests.builders.UserBuilder;
import org.junit.Test;

import javax.inject.Inject;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDaoTest extends AbstractCrudDaoTest<User, UserDao> {

	@Inject
	private UserDao userDao;

	@Override
	Class<User> getEntityClass() {
		return User.class;
	}

	@Override
	UserDao getDao() {
		return userDao;
	}

	@Override
	User createOne() {
		return new UserBuilder()
				.withLogin("johndoe")
				.withPassword("foobar")
				.withRole(Role.USER)
				.build();
	}

	@Test
	public void it_should_find_user_by_login() {
		Optional<User> optUser = userDao.findByLogin("mickael");
		assertThat(optUser).isPresent();

		User user = optUser.get();
		assertThat(user.getId()).isEqualTo("e31195bd-1d4e-4915-a3dc-ce901f57903f");
	}

	@Test
	public void it_should_not_find_user_with_unknown_login() {
		Optional<User> optUser = userDao.findByLogin("fake-login");
		assertThat(optUser).isNotPresent();
	}
}
