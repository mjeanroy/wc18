/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.configuration;

import com.github.mjeanroy.spring.mappers.configuration.EnableMapper;
import com.github.mjeanroy.wc18.domain.configuration.EnableWc18Domain;
import com.github.mjeanroy.wc18.security.configuration.EnableWc18Security;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.github.mjeanroy.wc18.api")
@EnableMapper
@EnableWc18Domain
@EnableWc18Security
public class Wc18ApiConfiguration {
}
