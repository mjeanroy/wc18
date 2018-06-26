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

package com.github.mjeanroy.wc18.api.tests.junit;

import com.github.mjeanroy.spring.mappers.configuration.EnableMapper;
import com.github.mjeanroy.wc18.api.configuration.JacksonConfiguration;
import com.github.mjeanroy.wc18.domain.configuration.jpa.JpaConfiguration;
import com.github.mjeanroy.wc18.domain.configuration.liquibase.LiquibaseConfiguration;
import com.github.mjeanroy.wc18.domain.tests.junit.EmbeddedDaoConfiguration;
import com.github.mjeanroy.wc18.security.service.SecurityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static org.mockito.Mockito.mock;

@Configuration
@EnableMapper
@ComponentScan({
	"com.github.mjeanroy.wc18.api.mappers",
	"com.github.mjeanroy.wc18.api.services",
	"com.github.mjeanroy.wc18.domain.services",
	"com.github.mjeanroy.wc18.domain.dao"
})
@Import({
	JpaConfiguration.class,
	LiquibaseConfiguration.class,
	JacksonConfiguration.class,
	EmbeddedDaoConfiguration.class
})
class ApiServiceTestConfiguration {

	@Bean
	public SecurityService securityService() {
		return mock(SecurityService.class);
	}
}
