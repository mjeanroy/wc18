/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.configuration.jpa;

import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;

/**
 * JPA/Hibernate properties.
 */
public final class JpaProperties {

	/**
	 * Hibernate HBM2DDL mode.
	 */
	private final String hbm2ddl;

	/**
	 * Create JPA Properties.
	 *
	 * @param hbm2ddl The hibernate {@code "hmb2ddl"} mode.
	 */
	public JpaProperties(String hbm2ddl) {
		this.hbm2ddl = hbm2ddl;
	}

	/**
	 * Get {@link #hbm2ddl}
	 *
	 * @return {@link #hbm2ddl}
	 */
	public String getHbm2ddl() {
		return hbm2ddl;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (o instanceof JpaProperties) {
			JpaProperties p = (JpaProperties) o;
			return Objects.equals(hbm2ddl, p.hbm2ddl);
		}

		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(hbm2ddl);
	}

	@Override
	public String toString() {
		return toStringHelper(getClass())
				.add("hbm2ddl", hbm2ddl)
				.toString();
	}
}
