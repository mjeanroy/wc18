/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.dao;

import com.github.mjeanroy.wc18.domain.models.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.github.mjeanroy.wc18.commons.MoreCollections.newHashMap;
import static com.github.mjeanroy.wc18.commons.Tuple.tuple;

@Repository
public class UserDao extends AbstractCrudDao<User> {

	/**
	 * Find user by its login.
	 *
	 * @param login The login.
	 * @return The user, if it exists.
	 */
	public Optional<User> findByLogin(String login) {
		String query =
			"SELECT x " +
				"FROM User x " +
				"WHERE x.login = :login";

		return findOne(query, newHashMap(
				tuple("login", login)
		));
	}
}
