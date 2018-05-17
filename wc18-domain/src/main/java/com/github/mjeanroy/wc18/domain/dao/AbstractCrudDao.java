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
public abstract class AbstractCrudDao<T extends AbstractEntity> extends AbstractReadOnlyDao<T> {

	/**
	 * Persist given entity.
	 *
	 * @param entity The entity to persist.
	 * @return Persisted entity.
	 */
	public T save(T entity) {
		getEntityManager().persist(entity);
		return entity;
	}
}
