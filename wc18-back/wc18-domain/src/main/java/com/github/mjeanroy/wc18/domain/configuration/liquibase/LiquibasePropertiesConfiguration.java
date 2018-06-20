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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.github.mjeanroy.wc18.commons.Environments.getEnvOrDefault;

@Configuration
public class LiquibasePropertiesConfiguration {

	/**
	 * Class Logger.
	 */
	private static final Logger log = LoggerFactory.getLogger(LiquibasePropertiesConfiguration.class);

	@Bean
	public LiquibaseProperties liquibaseProperties(
		@Value("${liquibase.dropFirst:false}") boolean dropFirst,
		@Value("${liquibase.shouldRun:true}") boolean shouldRun,
		@Value("${liquibase.contexts:}") String contexts,
		@Value("${liquibase.changeLog:classpath:/db/changelogs/db-changelogs.xml}") String changeLog) {

		log.info("Creating liquibaseProperties instance");
		String liquibaseChangelog = getEnvOrDefault("LIQUIBASE_CHANGELOG", changeLog);
		boolean liquibaseShouldRun = Boolean.valueOf(getEnvOrDefault("LIQUIBASE_SHOULD_RUN", Boolean.toString(shouldRun)));
		boolean liquibaseDropFirst = Boolean.valueOf(getEnvOrDefault("LIQUIBASE_DROP_FIRST", Boolean.toString(dropFirst)));
		String liquibaseContexts = getEnvOrDefault("LIQUIBASE_CONTEXTS", contexts);
		return new LiquibaseProperties(liquibaseChangelog, liquibaseShouldRun, liquibaseDropFirst, liquibaseContexts);
	}
}
