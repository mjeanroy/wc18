/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.models;

import com.google.common.base.MoreObjects;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@MappedSuperclass
public abstract class AbstractEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	private UUID id;

	@Version
	@Column(name = "version", nullable = false)
	private int version;

	@Column(name = "creation_date", nullable = false)
	private Date creationDate;

	/**
	 * Get {@link #id}
	 *
	 * @return {@link #id}
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * Get {@link #creationDate}
	 *
	 * @return {@link #creationDate}
	 */
	private Date getCreationDate() {
		return creationDate;
	}

	/**
	 * Get {@link #id} as a {@link String} value.
	 *
	 * @return Id as a string.
	 */
	public String id() {
		return id == null ? null : id.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (o == null) {
			return false;
		}

		if (getClass() == o.getClass()) {
			AbstractEntity e = (AbstractEntity) o;
			return Objects.equals(id, e.id);
		}

		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(getClass())
			.add("id", id)
			.add("version", version)
			.add("creationDate", creationDate)
			.toString();
	}
}
