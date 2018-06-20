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

import liquibase.integration.spring.SpringLiquibase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class LiquibaseConfiguration {

	/**
	 * Class Logger.
	 */
	private static final Logger log = LoggerFactory.getLogger(LiquibaseConfiguration.class);

	/**
	 * The bean name.
	 * Note that this bean <strong>must</strong> be created before JPA entity manager
	 * as hibernate validate schema on startup, so liquibase migration have to be executed.
	 */
	public static final String LIQUIBASE_BEAN_NAME = "liquibase";

	@Bean(name = LIQUIBASE_BEAN_NAME)
	public SpringLiquibase liquibase(LiquibaseProperties liquibaseProperties, DataSource dataSource) {
		log.info("Creating liquibase with properties: {}", liquibaseProperties);

		SpringLiquibase liquibase = new SpringLiquibase();
		liquibase.setBeanName(LIQUIBASE_BEAN_NAME);
		liquibase.setDataSource(dataSource);
		liquibase.setDropFirst(liquibaseProperties.isDropFirst());
		liquibase.setContexts(liquibaseProperties.getContexts());
		liquibase.setShouldRun(liquibaseProperties.isShouldRun());
		liquibase.setChangeLog(liquibaseProperties.getChangeLog());
		return liquibase;
	}
}
