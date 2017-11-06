package com.capital.one.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
public class JDBCConfig {

	Properties dbProp = new Properties();

	@Bean
	public DataSource getDataSource() {
		
		System.out.println("In Connection....");

		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		try {
			
			System.out.println("In Connection");
			
		//	SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		//	dataSource.setDriver(new org.postgresql.Driver());

			dbProp.load(new FileInputStream("/Users/dvh533/Desktop/BootCamp/RecognitionTool/src/main/resources/database.properties"));
			
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
	
//	@Bean
//	public RedemptionDao getRedemptionDao() {
//		return new RedemptionDaoImpl(getDataSource());
//	}

}
