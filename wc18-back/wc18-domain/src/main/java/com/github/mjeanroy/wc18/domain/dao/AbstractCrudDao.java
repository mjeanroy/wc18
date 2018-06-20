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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;

/**
 * Simple template structure for DAO Implementation.
 *
 * @param <T> Entity Type.
 */
public abstract class AbstractCrudDao<T extends AbstractEntity> extends AbstractReadOnlyDao<T> {

	/**
	 * Class Logger.
	 */
	private static final Logger log = LoggerFactory.getLogger(AbstractCrudDao.class);

	/**
	 * Persist given entity.
	 *
	 * @param entity The entity to persist.
	 * @return Persisted entity.
	 */
	public T save(T entity) {
		EntityManager entityManager = getEntityManager();

		if (entity.isNew() || entityManager.contains(entity)) {
			log.debug("Persisting entity: {}", entity);
			entityManager.persist(entity);
		} else {
			log.debug("Merging entity: {}", entity);
			entity = entityManager.merge(entity);
		}

		return entity;
	}

	/**
	 * Remove entity.
	 *
	 * @param entity Entity to remove.
	 */
	public void delete(T entity) {
		log.debug("Removing entity: {}", entity);
		getEntityManager().remove(entity);
	}
}
