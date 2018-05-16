/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.dao;

import com.github.mjeanroy.wc18.domain.models.AbstractEntity;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Basic structure to test read-only repositories (repositories without save, update or delete operations).
 *
 * @param <ENTITY> Entity Type.
 */
abstract class AbstractReadOnlyDaoTest<ENTITY extends AbstractEntity> extends AbstractRepositoryTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Test
	public void it_should_find_all() {
		int expectedCount = Math.toIntExact(countAll());
		Iterable<ENTITY> results = getDao().findAll();
		assertThat(results).hasSize(expectedCount).doesNotHaveDuplicates();
	}

	/**
	 * Get the tested repository object.
	 *
	 * @return The repository object.
	 */
	abstract Class<ENTITY> getEntityClass();

	/**
	 * Get the tested repository object.
	 *
	 * @return The repository object.
	 */
	abstract AbstractReadOnlyDao<ENTITY> getDao();

	/**
	 * Count total number of rows in database.
	 *
	 * @return Total number of rows.
	 */
	private long countAll() {
		String query = "SELECT COUNT(x) FROM " + getEntityClass().getName() + " x";
		Long count = (Long) entityManager.createQuery(query).getSingleResult();
		return count == null ? 0 : count;
	}
}
