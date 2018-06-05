/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.configuration;

import com.github.mjeanroy.wc18.domain.models.AbstractEntity;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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

	/**
	 * Hibernate HBM2DDL mode.
	 */
	private final String hbm2ddl;

	/**
	 * Create JPA Configuration.
	 *
	 * @param hbm2ddl Hibernate HBM2DDL mode, default is {@code "validate"}.
	 */
	public JpaConfiguration(@Value("${hibernate.hbm2ddl.auto:validate}") String hbm2ddl) {
		this.hbm2ddl = hbm2ddl;
	}

	@Bean(destroyMethod = "destroy")
	@DependsOn(LiquibaseConfiguration.LIQUIBASE_BEAN_NAME)
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
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

		Map<String, Object> jpaProperties = new HashMap<>();
		jpaProperties.put("hibernate.connection.tinyInt1isBit", true);
		jpaProperties.put("hibernate.connection.transformedBitIsBoolean", true);
		jpaProperties.put("hibernate.default_batch_fetch_size", 20);
		jpaProperties.put("hibernate.hbm2ddl.auto", hbm2ddl);

		log.debug("- Configure JPA properties: {}", jpaProperties);
		entityManagerFactory.setJpaPropertyMap(jpaProperties);

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
