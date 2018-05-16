/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.dao;

import com.github.mjeanroy.wc18.domain.models.AbstractEntity;
import org.springframework.core.ResolvableType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
		entityClass = (Class<T>) ResolvableType
			.forClass(getClass())
			.as(AbstractReadOnlyDao.class)
			.getGeneric(0)
			.getRawClass();
	}

	/**
	 * Find all entities.
	 *
	 * @return All Entities.
	 */
	@SuppressWarnings("unchecked")
	public Iterable<T> findAll() {
		return entityManager.createQuery("SELECT x FROM " + entityClass.getSimpleName() + " x").getResultList();
	}
}
