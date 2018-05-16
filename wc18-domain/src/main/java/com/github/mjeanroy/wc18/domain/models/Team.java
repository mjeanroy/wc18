/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "teams")
public class Team extends AbstractEntity {

	@Column(name = "flag", nullable = false)
	private String flag;

	@Column(name = "name", nullable = false)
	private String name;

	public String getFlag() {
		return flag;
	}

	public String getName() {
		return name;
	}
}
