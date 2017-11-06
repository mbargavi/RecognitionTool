package com.capital.one.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.web.context.ServletContextAware;

@Configuration
public class JDBCConfig implements ServletContextAware {

	Properties dbProp = new Properties();
	
	@Autowired
	static ServletContext context;

	@Bean
	public DataSource getDataSource() {
		
		System.out.println("In Connection....");

		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		try {
			
			System.out.println("In Connection");
			
		//	SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		//	dataSource.setDriver(new org.postgresql.Driver());
			
			String dbTempPath = context.getRealPath("");
			String dbPropertiesPath = null;
			if (dbTempPath.endsWith("/")) {
				dbPropertiesPath = context.getRealPath("") + "WEB-INF/database.properties";
			} else {
				dbPropertiesPath = context.getRealPath("") + "/WEB-INF/database.properties";
			}

			System.out.println("dbPropertiesPath = " + dbPropertiesPath);

			dbProp.load(new FileInputStream(dbPropertiesPath));
			
			System.out.println(dbProp.getProperty("url"));
		
			dataSource.setDriverClassName("org.postgresql.Driver");
			dataSource.setUrl(dbProp.getProperty("url"));
			dataSource.setUsername(dbProp.getProperty("username"));
			dataSource.setPassword(dbProp.getProperty("password"));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataSource;

	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		JDBCConfig.context = servletContext;
		
	}
	
//	@Bean
//	public RedemptionDao getRedemptionDao() {
//		return new RedemptionDaoImpl(getDataSource());
//	}

}
