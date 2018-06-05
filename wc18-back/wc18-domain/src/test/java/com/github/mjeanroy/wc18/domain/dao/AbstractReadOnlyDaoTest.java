/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.dao;

import com.github.mjeanroy.wc18.domain.models.AbstractEntity;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Basic structure to test read-only repositories (repositories without save, update or delete operations).
 *
 * @param <T> Entity Type.
 */
abstract class AbstractReadOnlyDaoTest<T extends AbstractEntity, U extends AbstractReadOnlyDao<T>> extends AbstractRepositoryTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Test
	public void it_should_find_all() {
		int expectedCount = Math.toIntExact(countAll());
		Iterable<T> results = getDao().findAll();
		assertThat(results).hasSize(expectedCount).doesNotHaveDuplicates();
	}

	/**
	 * Get the tested repository object.
	 *
	 * @return The repository object.
	 */
	abstract Class<T> getEntityClass();

	/**
	 * Get the tested repository object.
	 *
	 * @return The repository object.
	 */
	abstract U getDao();

	/**
	 * Count total number of rows in database.
	 *
	 * @return Total number of rows.
	 */
	long countAll() {
		String query = "SELECT COUNT(x) FROM " + getEntityClass().getName() + " x";
		Long count = (Long) entityManager.createQuery(query).getSingleResult();
		return count == null ? 0 : count;
	}

	/**
	 * Find one, or fail if it does not exist.
	 *
	 * @param entityClass The entity class to look for.
	 * @param id The ID.
	 * @return The single result.
	 */
	<X> X findOne(Class<X> entityClass, String id) {
		return entityManager.find(entityClass, id);
	}
}
