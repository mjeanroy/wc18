/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.dao;

import com.github.mjeanroy.wc18.domain.models.AbstractEntity;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.ResolvableType;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

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
		return findAll(entityManager.createQuery("SELECT x FROM " + entityClass.getSimpleName() + " x"));
	}

	/**
	 * Find single entity.
	 *
	 * @param id The primary key.
	 * @return The entity.
	 */
	public Optional<T> findOne(UUID id) {
		try {
			return Optional.of(entityManager.find(entityClass, id));
		} catch(NoResultException ex) {
			return Optional.empty();
		}
	}

	@SuppressWarnings("unchecked")
	Iterable<T> findAll(Query query) {
		return (Iterable<T>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	Optional<T> findOne(Query query) {
		try {
			return Optional.of((T) query.getSingleResult());
		} catch (NoResultException ex) {
			return Optional.empty();
		}
	}

	/**
	 * Get the current entity manager.
	 *
	 * @return The entity manager.
	 */
	EntityManager getEntityManager() {
		return entityManager;
	}
}
