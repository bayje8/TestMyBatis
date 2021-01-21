package com.example.demo.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.jdbc.DataSourceHealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
@MapperScan("com.example.demo.mybatis.mapper")
public class MyBatisConfig {

	@Bean(destroyMethod = "close")
	@Primary
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/sakila");
		dataSource.setUsername("root");
		dataSource.setPassword("password");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setValidationQuery("Select 1");
		return dataSource;
	}

	@Bean
	@Autowired
	public SqlSessionFactoryBean sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource);
		sqlSessionFactory.setTypeAliasesPackage("com.example.demo.domain");

		Resource actorResource = new ClassPathResource("/com/example/demo/mybatis/repository/Actor.xml");

		sqlSessionFactory.setMapperLocations(new Resource[] { actorResource });
		
		return sqlSessionFactory;
	}

/*	@Bean
	@Autowired
	public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
		return sessionTemplate;
	}
	
	@Bean
	@Autowired
	public ActorMyBatisMapper getActorMapper(@Qualifier("sqlSessionTemplate") SqlSessionTemplate sessionTemplate) {
		return sessionTemplate.getMapper(ActorMyBatisMapper.class);
	}
	*/
	@Bean
	@Autowired
	public DataSourceHealthIndicator dataSourceHealthIndicator(DataSource dataSource) {
		return new DataSourceHealthIndicator(dataSource);
	}

}
