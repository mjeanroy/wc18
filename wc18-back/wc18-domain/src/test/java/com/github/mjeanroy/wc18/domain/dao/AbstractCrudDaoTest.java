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
