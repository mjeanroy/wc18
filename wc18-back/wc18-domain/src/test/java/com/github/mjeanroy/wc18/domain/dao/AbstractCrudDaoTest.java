/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.dao;

import com.github.mjeanroy.wc18.domain.models.AbstractEntity;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Basic structure to test read-only repositories (repositories without save, update or delete operations).
 *
 * @param <T> Entity Type.
 * @param <U> DAO Type.
 */
abstract class AbstractCrudDaoTest<T extends AbstractEntity, U extends AbstractCrudDao<T>> extends AbstractReadOnlyDaoTest<T, U> {

	@Test
	public void create_entry() {
		T newOne = createOne();
		T result = getDao().save(newOne);
		assertThat(result).isNotNull();
		assertThat(result.getId()).isNotNull().isNotEmpty();
	}

	@Test
	public void it_should_remove_entity() {
		String id = getOneId();
		T entity = findOne(getEntityClass(), id);
		assertThat(entity).isNotNull();

		getDao().delete(entity);

		assertThat(findOne(getEntityClass(), id)).isNull();
	}
	/**
	 * Create new entity that will be persisted in {@link #create_entry()} unit test.
	 *
	 * @return The new entity.
	 */
	abstract T createOne();
}
