/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.inject.Inject;

/**
 * Configure database connection and its datasource.
 */
@Configuration
public class DatabaseConfiguration {

	/**
	 * Class Logger.
	 */
	private static final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);

	/**
	 * Database JDBC driver.
	 */
	private final String driver;

	/**
	 * Database connection URL.
	 */
	private final String url;

	/**
	 * Database connection username.
	 */
	private final String user;

	/**
	 * Database connection password.
	 */
	private final String password;

	@Inject
	public DatabaseConfiguration(
			@Value("${database.driverClassName}") String driver,
			@Value("${database.url}") String url,
			@Value("${database.user}") String user,
			@Value("${database.password}") String password) {

		this.driver = driver;
		this.url = url;
		this.user = user;
		this.password = password;
	}

	@Bean(destroyMethod = "close")
	public HikariDataSource dataSource() {
		log.info("Configuration dataSource");
		log.debug("- Driver: {}", driver);
		log.debug("- URL: {}", url);
		log.debug("- User: {}", user);

		HikariConfig config = new HikariConfig();

		// Connection configuration
		config.setUsername(user);
		config.setPassword(password);
		config.setJdbcUrl(url);
		config.setDriverClassName(driver);

		// This property sets a SQL statement that will be executed after every new connection
		// creation before adding it to the pool.
		// If this SQL is not valid or throws an exception, it will be treated as a connection
		// failure and the standard retry logic will be followed.
		// Default: none
		config.setConnectionInitSql("SELECT 1");

		// This property represents a user-defined name for the connection pool and appears
		// mainly in logging and JMX management consoles to identify pools and pool configurations.
		// Default: auto-generated
		config.setPoolName("WorldTravelersDataSource");

		// This property controls the maximum size that the pool is allowed to reach,
		// including both idle and in-use connections.
		// Basically this value will determine the maximum number of actual connections to the database backend.
		// A reasonable value for this is best determined by your execution environment.
		// When the pool reaches this size, and no idle connections are available,
		// calls to getConnection() will block for up to connectionTimeout milliseconds before timing out.
		// Default: 10
		config.setMaximumPoolSize(10);

		// This property controls the amount of time that a connection can be out of the pool before a
		// message is logged indicating a possible connection leak.
		// A value of 0 means leak detection is disabled.
		// While the default is 0, and other connection pool implementations state that leak detection
		// is "not for production" as it imposes a high overhead, at least in the case of HikariCP the
		// imposed overhead is only 5μs (microseconds) split between getConnection() and close().
		// Maybe other pools are doing it wrong, but feel free to use leak detection under HikariCP in production
		// environments if you wish.
		// Lowest acceptable value for enabling leak detection is 10000 (10 secs).
		// Default: 0
		config.setLeakDetectionThreshold(100000);

		// This property controls the maximum amount of time (in milliseconds) that a connection
		// is allowed to sit idle in the pool.
		// Whether a connection is retired as idle or not is subject to a maximum variation of +30 seconds,
		// and average variation of +15 seconds.
		// A connection will never be retired as idle before this timeout.
		// A value of 0 means that idle connections are never removed from the pool.
		// Default: 600000
		config.setIdleTimeout(600000);

		// This property controls the maximum lifetime of a connection in the pool.
		// When a connection reaches this timeout, even if recently used, it will be retired from the pool.
		// An in-use connection will never be retired, only when it is idle will it be removed.
		// We strongly recommend setting this value, and using something reasonable like 30 minutes or 1 hour.
		// A value of 0 indicates no maximum lifetime (infinite lifetime), subject of course to the idleTimeout
		// setting.
		// Default: 1800000 (30 minutes)
		config.setMaxLifetime(3600000);

		// This property controls the maximum number of milliseconds that a client (that's you)
		// will wait for a connection from the pool.
		// If this time is exceeded without a connection becoming available, a SQLException will be thrown.
		// 100ms is the minimum value.
		// Default: 30000 (30 seconds)
		config.setConnectionTimeout(30000);

		// This is the name of the DataSource class provided by the JDBC driver.
		// Consult the documentation for your specific JDBC driver to get this class name, or see the table below.
		// Note XA data sources are not supported. XA requires a real transaction manager like bitronix.
		// Note that you do not need this property if you are using driverClassName to wrap an
		// old-school DriverManager-based JDBC driver.
		// The HikariCP team considers dataSourceClassName to be a superior method of creating connections
		// compared to driverClassName.
		// Default: none
		// config.setDataSourceClassName(MysqlDataSource.class.getName());

		// This property controls the minimum number of idle connections that HikariCP tries to maintain in the pool.
		// If the idle connections dip below this value, HikariCP will make a best effort to add additional connections
		// quickly and efficiently.
		// However, for maximum performance and responsiveness to spike demands,
		// we recommend not setting this value and instead allowing HikariCP to act as a fixed size
		// connection pool.
		// Default: same as maximumPoolSize
		// config.setMinimumIdle(2);

		// This property controls whether or not JMX Management Beans ("MBeans")
		// are registered or not.
		// Default: false
		config.setRegisterMbeans(false);

		return new HikariDataSource(config);
	}
}
