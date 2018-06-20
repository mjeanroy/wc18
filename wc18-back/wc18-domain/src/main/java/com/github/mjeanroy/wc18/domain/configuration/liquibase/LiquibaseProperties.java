/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Mickael Jeanroy
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
