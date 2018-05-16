/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.models;

import javax.persistence.Column;

public class League extends AbstractEntity {

	@Column(name = "name", nullable = false)
	private String name;

	public String getName() {
		return name;
	}
}
