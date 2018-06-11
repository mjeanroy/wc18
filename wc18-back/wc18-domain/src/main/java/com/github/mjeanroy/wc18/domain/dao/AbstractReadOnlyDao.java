/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
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
			return Optional.of(entityManager.find(entityClass, id));
		} catch(NoResultException ex) {
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
