package io.backend.blogproject.configuration;

import org.hibernate.jpa.HibernatePersistenceConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.persistence.EntityManagerFactory;

@Configuration
public class EntityManagerFactoryConfiguration {
	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;


	@Bean
	public EntityManagerFactory getEntityManagerFactory() {

		HibernatePersistenceConfiguration hpc = new HibernatePersistenceConfiguration("blogproject-hibernate")
			.jdbcDriver(driverClassName)
			.jdbcUrl(url)
			.jdbcUsername(username)
			.jdbcPassword("");

		return hpc.createEntityManagerFactory();
	}
}
