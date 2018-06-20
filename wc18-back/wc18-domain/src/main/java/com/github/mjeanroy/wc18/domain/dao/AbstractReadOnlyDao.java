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
import org.springframework.core.GenericTypeResolver;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Map;
import java.util.Optional;

/**
 * Simple template structure for DAO Implementation.
 *
 * @param <T> Entity Type.
 */
public abstract class AbstractReadOnlyDao<T extends AbstractEntity> {

	/**
	 * The entity class.
	 */
	private final Class<T> entityClass;

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	AbstractReadOnlyDao() {
		entityClass = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), AbstractReadOnlyDao.class);
	}

	/**
	 * Find all entities.
	 *
	 * @return All Entities.
	 */
	public Iterable<T> findAll() {
		return findAll("SELECT x FROM " + entityClass.getSimpleName() + " x");
	}

	/**
	 * Find single entity.
	 *
	 * @param id The primary key.
	 * @return The entity.
	 */
	public Optional<T> findOne(String id) {
		try {
			return Optional.ofNullable(entityManager.find(entityClass, id));
		} catch (NoResultException ex) {
			return Optional.empty();
		}
	}

	/**
	 * Find values in database.
	 *
	 * @param jpql The JPQL query.
	 * @param parameters Query parameters.
	 * @return The result value.
	 */
	Iterable<T> findAll(String jpql, Map<String, Object> parameters) {
		Query query = getEntityManager().createQuery(jpql);
		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}

		return findAll(query);
	}

	/**
	 * Find values in database.
	 *
	 * @param jpql The JPQL query.
	 * @return The result value.
	 */
	Iterable<T> findAll(String jpql) {
		Query query = getEntityManager().createQuery(jpql);
		return findAll(query);
	}

	/**
	 * Find single value in database and returns empty optional without any
	 * results.
	 *
	 * @param jpql The JPQL query.
	 * @param parameters The query parameters.
	 * @return The result value.
	 */
	Optional<T> findOne(String jpql, Map<String, Object> parameters) {
		Query query = getEntityManager().createQuery(jpql);
		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}

		return findOne(query);
	}

	/**
	 * Count values in database.
	 *
	 * @param jpql The JPQL query.
	 * @param parameters Query parameters.
	 * @return The result value.
	 */
	long count(String jpql, Map<String, Object> parameters) {
		Query query = getEntityManager().createQuery(jpql);
		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}

		return (long) query.getSingleResult();
	}

	/**
	 * Get the current entity manager.
	 *
	 * @return The entity manager.
	 */
	EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Find value in database, and return empty optional
	 * without any results.
	 *
	 * @param query The JPA query.
	 * @return The result value.
	 */
	@SuppressWarnings("unchecked")
	private Optional<T> findOne(Query query) {
		try {
			return Optional.of((T) query.getSingleResult());
		} catch (NoResultException ex) {
			return Optional.empty();
		}
	}

	@SuppressWarnings("unchecked")
	private Iterable<T> findAll(Query query) {
		return (Iterable<T>) query.getResultList();
	}
}
