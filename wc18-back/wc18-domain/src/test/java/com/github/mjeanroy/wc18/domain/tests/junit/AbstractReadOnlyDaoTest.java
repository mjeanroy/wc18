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

package com.github.mjeanroy.wc18.domain.tests.junit;

import com.github.mjeanroy.wc18.domain.dao.AbstractReadOnlyDao;
import com.github.mjeanroy.wc18.domain.models.AbstractEntity;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Basic structure to test read-only repositories (repositories without save, update or delete operations).
 *
 * @param <T> Entity Type.
 */
public abstract class AbstractReadOnlyDaoTest<T extends AbstractEntity, U extends AbstractReadOnlyDao<T>> extends AbstractRepositoryTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Test
	public void it_should_find_all() {
		int expectedCount = Math.toIntExact(countAll());
		Iterable<T> results = getDao().findAll();
		assertThat(results).hasSize(expectedCount).doesNotHaveDuplicates();
	}

	@Test
	public void it_should_find_one() {
		String id = getOneId();
		Optional<T> result = getDao().findOne(id);
		assertThat(result).isPresent();

		T entity = result.orElseThrow(AssertionError::new);
		assertThat(entity).isNotNull();
		assertThat(entity.getId()).isEqualTo(id);
		checkOne(entity);
	}

	@Test
	public void it_should_return_empty_with_unknown_id() {
		String id = UUID.randomUUID().toString();
		Optional<T> result = getDao().findOne(id);
		assertThat(result).isEmpty();
	}

	/**
	 * Get the tested repository object.
	 *
	 * @return The repository object.
	 */
	protected abstract Class<T> getEntityClass();

	/**
	 * Get the tested repository object.
	 *
	 * @return The repository object.
	 */
	protected abstract U getDao();

	/**
	 * Get an id to retrieve in {@link #it_should_find_one()} test.
	 *
	 * @return A valid identifier.
	 */
	protected abstract String getOneId();

	/**
	 * Ensure given entity is valid.
	 *
	 * @param one The entity.
	 */
	protected abstract void checkOne(T one);

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

	/**
	 * Find one, or fail if it does not exist.
	 *
	 * @param entityClass The entity class to look for.
	 * @param id The ID.
	 * @return The single result.
	 */
	protected <X> X findOne(Class<X> entityClass, String id) {
		return entityManager.find(entityClass, id);
	}
}
