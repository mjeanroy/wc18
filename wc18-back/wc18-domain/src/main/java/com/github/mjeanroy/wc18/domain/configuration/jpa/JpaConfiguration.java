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

package com.github.mjeanroy.wc18.domain.configuration.jpa;

import com.github.mjeanroy.wc18.domain.configuration.liquibase.LiquibaseConfiguration;
import com.github.mjeanroy.wc18.domain.models.AbstractEntity;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Configure JPA and hibernate specific configuration.
 */
@Configuration
public class JpaConfiguration {

	/**
	 * Class Logger.
	 */
	private static final Logger log = LoggerFactory.getLogger(JpaConfiguration.class);

	@Bean(destroyMethod = "destroy")
	@DependsOn(LiquibaseConfiguration.LIQUIBASE_BEAN_NAME)
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(JpaProperties jpaProperties, DataSource dataSource) {
		log.info("Configure hibernate JPA vendor adapter");
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setShowSql(false);

		log.info("Initialize entity manager factory");
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setJpaVendorAdapter(vendorAdapter);

		log.debug("- Configure entity manager persistence provider class");
		entityManagerFactory.setPersistenceProviderClass(HibernatePersistenceProvider.class);

		log.debug("- Configure entity manager data source");
		entityManagerFactory.setDataSource(dataSource);

		log.debug("- Configure packages to scan");
		entityManagerFactory.setPackagesToScan(AbstractEntity.class.getPackage().getName());

		Map<String, Object> props = new HashMap<>();
		props.put("hibernate.connection.tinyInt1isBit", true);
		props.put("hibernate.connection.transformedBitIsBoolean", true);
		props.put("hibernate.default_batch_fetch_size", 20);
		props.put("hibernate.hbm2ddl.auto", jpaProperties.getHbm2ddl());

		log.debug("- Configure JPA properties: {}", props);
		entityManagerFactory.setJpaPropertyMap(props);

		return entityManagerFactory;
	}

	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource, EntityManagerFactory emf) {
		log.info("Configure JPA transaction manager");
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setDataSource(dataSource);
		txManager.setEntityManagerFactory(emf);
		return txManager;
	}
}
