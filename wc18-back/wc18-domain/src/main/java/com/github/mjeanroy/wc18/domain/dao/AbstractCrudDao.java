/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
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
	 * @param entity Entity to remove.
	 */
	public void delete(T entity) {
		log.debug("Removing entity: {}", entity);
		getEntityManager().remove(entity);
	}
}
