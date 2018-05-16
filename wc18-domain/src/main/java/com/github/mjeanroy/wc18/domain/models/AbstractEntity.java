/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.models;

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

	public UUID getId() {
		return id;
	}

	private Date getCreationDate() {
		return creationDate;
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
}
