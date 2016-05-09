package com.hr.hotel.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableJpaRepositories("com.hr.onesuite.repository")
@EnableTransactionManagement
public class DatabaseConfig {

	@Autowired
	private ApplicationConfig config;

	@Bean
	public ComboPooledDataSource dataSource() throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setDriverClass(this.config.getJDBCDriver());
		dataSource.setJdbcUrl(this.config.getJDBCURL());
		dataSource.setUser(this.config.getJDBCUser());
		dataSource.setPassword(this.config.getJDBCPassword());
		// <!-- these are C3P0 properties -->
		dataSource.setAcquireIncrement(5);
		dataSource.setMaxIdleTime(3600);
		dataSource.setMaxIdleTimeExcessConnections(300);
		dataSource.setMaxPoolSize(100);
		dataSource.setMinPoolSize(20);
		dataSource.setNumHelperThreads(6);
		dataSource.setUnreturnedConnectionTimeout(3600);
		// <!-- Keep pool alive -->
		dataSource.setPreferredTestQuery("SELECT 1");
		dataSource.setTestConnectionOnCheckout(true);
		return dataSource;
	}

	/*
	 * public HikariDataSource dataSource() { // HikariConfig config = new
	 * HikariConfig(); // config.setJdbcUrl(this.config.getJDBCURL()); //
	 * config.setUsername(this.config.getJDBCUser()); //
	 * config.setPassword(this.config.getJDBCPassword()); // //
	 * config.addDataSourceProperty("cachePrepStmts", "true"); //
	 * config.addDataSourceProperty("prepStmtCacheSize", "250"); //
	 * config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048"); //
	 * config.addDataSourceProperty("useServerPrepStmts", "true");
	 * 
	 * HikariDataSource dataSource = new HikariDataSource();
	 * dataSource.setDriverClassName(this.config.getJDBCDriver());
	 * dataSource.setJdbcUrl(this.config.getJDBCURL());
	 * dataSource.setUsername(this.config.getJDBCUser());
	 * dataSource.setPassword(this.config.getJDBCPassword());
	 * 
	 * dataSource.setAutoCommit(false);
	 * 
	 * //dataSource.setConnectionTimeout(); dataSource.setIdleTimeout(3600);
	 * //dataSource.setMaxLifetime(maxLifetimeMs);
	 * 
	 * return dataSource; }
	 */

	@Bean
	public IsolationSupportHibernateJpaDialect jpaDialect() {
		return new IsolationSupportHibernateJpaDialect();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory()
			throws PropertyVetoException {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean
				.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		entityManagerFactoryBean.setPackagesToScan(this.config
				.getHPackageToScan());

		entityManagerFactoryBean.setJpaProperties(hibernateProp());
		entityManagerFactoryBean.setJpaDialect(jpaDialect());
		return entityManagerFactoryBean;
	}

	private Properties hibernateProp() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", this.config.getHDialect());
		properties.setProperty("hibernate.show_sql", this.config.getHShowSQL());
		// properties.setProperty("hibernate.transaction.flush_before_completion",
		// this.config.getHFlushOperation());
		// properties.setProperty("hibernate.hbm2ddl.auto",
		// this.config.getHHBM2DDL());
		return properties;
	}

	@Bean
	public JpaTransactionManager transactionManager()
			throws PropertyVetoException {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory()
				.getObject());
		return transactionManager;
	}
}
