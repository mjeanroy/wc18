/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Mickael Jeanroy
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.github.mjeanroy.wc18.domain.dao;

import com.github.mjeanroy.wc18.domain.models.User;
import com.github.mjeanroy.wc18.domain.models.User.Role;
import com.github.mjeanroy.wc18.domain.tests.builders.UserBuilder;
import com.github.mjeanroy.wc18.domain.tests.junit.AbstractCrudDaoTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class UserDaoTest extends AbstractCrudDaoTest<User, UserDao> {

	@Inject
	private UserDao userDao;

	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}

	@Override
	protected UserDao getDao() {
		return userDao;
	}

	@Override
	protected String getOneId() {
		return "e31195bd-1d4e-4915-a3dc-ce901f57903f";
	}

	@Override
	protected void checkOne(User one) {
		assertThat(one.getLogin()).isEqualTo("mickael");
		assertThat(one.getRole()).isEqualTo(Role.ADMIN);
		assertThat(one.getPassword()).isNotNull().isNotEmpty();
	}

	@Override
	protected User createOne() {
		return new UserBuilder()
			.withLogin("johndoe")
			.withPassword("foobar")
			.withRole(Role.USER)
			.build();
	}

	@Test
	void it_should_find_user_by_login() {
		Optional<User> optUser = userDao.findByLogin("mickael");
		assertThat(optUser).isPresent();

		User user = optUser.orElseThrow(AssertionError::new);
		assertThat(user.getId()).isEqualTo("e31195bd-1d4e-4915-a3dc-ce901f57903f");
	}

	@Test
	void it_should_not_find_user_with_unknown_login() {
		Optional<User> optUser = userDao.findByLogin("fake-login");
		assertThat(optUser).isNotPresent();
	}
}
