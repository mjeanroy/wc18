/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.configuration.liquibase;

import com.google.common.base.MoreObjects;

import java.util.Objects;

/**
 * Liquibase properties.
 */
public final class LiquibaseProperties {

	/**
	 * The liquibase {@code "changeLog"} property.
	 * @see liquibase.integration.spring.SpringLiquibase#changeLog
	 */
	private final String changeLog;

	/**
	 * The liquibase {@code "shouldRun"} property.
	 * @see liquibase.integration.spring.SpringLiquibase#shouldRun
	 */
	private final boolean shouldRun;

	/**
	 * The liquibase {@code "dropFirst"} property.
	 * @see liquibase.integration.spring.SpringLiquibase#dropFirst
	 */
	private final boolean dropFirst;

	/**
	 * The liquibase {@code "contexts"} property.
	 * @see liquibase.integration.spring.SpringLiquibase#contexts
	 */
	private final String contexts;

	/**
	 * Create liquibase properties.
	 *
	 * @param changeLog Changelog path.
	 * @param shouldRun The liquibase {@code shouldRun} flag.
	 * @param dropFirst The liquibase {@code dropFirst} flag.
	 * @param contexts The liquibase contexts.
	 */
	public LiquibaseProperties(String changeLog, boolean shouldRun, boolean dropFirst, String contexts) {
		this.changeLog = changeLog;
		this.shouldRun = shouldRun;
		this.dropFirst = dropFirst;
		this.contexts = contexts;
	}

	/**
	 * Get {@link #changeLog}
	 *
	 * @return {@link #changeLog}
	 */
	public String getChangeLog() {
		return changeLog;
	}

	/**
	 * Get {@link #shouldRun}
	 *
	 * @return {@link #shouldRun}
	 */
	public boolean isShouldRun() {
		return shouldRun;
	}

	/**
	 * Get {@link #dropFirst}
	 *
	 * @return {@link #dropFirst}
	 */
	public boolean isDropFirst() {
		return dropFirst;
	}

	/**
	 * Get {@link #contexts}
	 *
	 * @return {@link #contexts}
	 */
	public String getContexts() {
		return contexts;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (o instanceof LiquibaseProperties) {
			LiquibaseProperties p = (LiquibaseProperties) o;
			return Objects.equals(changeLog, p.changeLog)
					&& Objects.equals(shouldRun, p.shouldRun)
					&& Objects.equals(dropFirst, p.dropFirst)
					&& Objects.equals(contexts, p.contexts);
		}

		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(changeLog, shouldRun, dropFirst, contexts);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(getClass())
				.add("changeLog", changeLog)
				.add("shouldRun", shouldRun)
				.add("dropFirst", dropFirst)
				.add("contexts", contexts)
				.toString();
	}
}
