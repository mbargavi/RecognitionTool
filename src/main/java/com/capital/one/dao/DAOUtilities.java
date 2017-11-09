package com.capital.one.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.ServletContextAware;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * Class used to retrieve DAO Implementations. Serves as a factory. Also used to
 * create JDBC connections.
 * 
 * @author David Crites
 *
 */
@Repository
public class DAOUtilities implements ServletContextAware {

	private static EmployeeDaoImpl employeeDaoImpl;
	private static AwardDaoImpl awardDaoImpl;
	private static RecognitionDaoImpl recognitionDaoImpl;
	private static EmployeeCreditDaoImpl employeeCreditDaoImpl;
	private static TeamCreditDaoImpl teamCreditDaoImpl;
	private static RedemptionDaoImpl redemptionDaoImpl;

	private static Connection connection;
	private static Properties dbProps = new Properties();
	// Below two Strings are not being used for the Connection because using the
	// option to just pass in Properties to the connection.
	private static String connectionUsername = "";
	private static String connectionPassword = "";
	private static String connectionURL = "";

	private static Logger log = Logger.getLogger("DAOUtilities");

	@Autowired
	static ServletContext context;
	
	@Autowired
	static JDBCConfig con;

	/*
	 * First set of functions below will return a DAOimplementation but return it as
	 * an Interface, and manage this so that if we already have one defined we
	 * return the same one. This will allow the services to call DAO implementation
	 * methods.
	 */
	public static synchronized EmployeeDao getEmployeeDao() {
		if (employeeDaoImpl == null) {
			employeeDaoImpl = new EmployeeDaoImpl();
		}
		return employeeDaoImpl;
	}

	public static synchronized AwardDao getAwardDao() {
		if (awardDaoImpl == null) {
			awardDaoImpl = new AwardDaoImpl();
		}
		return awardDaoImpl;
	}

	public static synchronized RecognitionDao getRecognitionDao() {
		if (recognitionDaoImpl == null) {
		//	recognitionDaoImpl = new RecognitionDaoImpl();
		}
		return recognitionDaoImpl;
	}

//	public static synchronized RedemptionDao getRedemptionDao() {
//		if (redemptionDaoImpl == null) {
//			redemptionDaoImpl = new RedemptionDaoImpl();
//			//redemptionDaoImpl = new RedemptionDaoImpl(con.getDataSource());
//		}
//		return redemptionDaoImpl;
//	}

	public static synchronized EmployeeCreditDao getEmployeeCreditDao() {
		if (employeeCreditDaoImpl == null) {
			employeeCreditDaoImpl = new EmployeeCreditDaoImpl();
		}
		return employeeCreditDaoImpl;
	}

	public static synchronized TeamCreditDao getTeamCreditDao() {
		if (teamCreditDaoImpl == null) {
			teamCreditDaoImpl = new TeamCreditDaoImpl();
		}
		return teamCreditDaoImpl;
	}

	/***
	 * This function is managing connections by returning the same connection for
	 * use if we still have it
	 * 
	 * @return returns a Connection to the DAO implementation needing a connection
	 *         to connect to the database
	 * @throws SQLException
	 */
	public static synchronized Connection getConnection() throws SQLException {

		log.debug("Attempting to get connection");
		// Properties dbProps = new Properties();

		if (connection == null) {
			log.trace("connection was null, registering driver");
			try {
				Class.forName("org.postgresql.Driver");
				log.trace("getting initial pull of connection parameters from database.properties and storing");
				// FOUND A WAY TO FIND RELATIVE PATH FOR TOMCAT SERVER TO FIND PROPERTIES FILE I
				// THINK
				// NEED TO DO THE BELOW (plus I had to have this class implement
				// ServletContextAware, override setServletContext,
				// and autowire a ServletContext variable); also, to make work had to move the
				// database.properties to WEB-INF
				String dbTempPath = context.getRealPath("");
				String dbPropertiesPath = null;
				if (dbTempPath.endsWith("/")) {
					dbPropertiesPath = context.getRealPath("") + "WEB-INF/database.properties";
				} else {
					dbPropertiesPath = context.getRealPath("") + "/WEB-INF/database.properties";
				}

				System.out.println("dbPropertiesPath = " + dbPropertiesPath);

				dbProps.load(new FileInputStream(dbPropertiesPath));

				connectionUsername = dbProps.getProperty("username");
				connectionPassword = dbProps.getProperty("password");
				connectionURL = dbProps.getProperty("url");
				// Setting the below properties and changing the format of the Driver call to
				// (URL,dbProps)
				// to try to prevent socket connections from closing
				dbProps.setProperty("tcpKeepAlive", "true");
				dbProps.setProperty("loginTimeout", "5"); // specified in seconds
				dbProps.setProperty("connectTimeout", "0"); // "0" means this is disabled
				dbProps.setProperty("socketTimeout", "0"); // "0" means this is disabled

			} catch (ClassNotFoundException e) {
				System.out.println("Could not register driver!");
				e.printStackTrace();
				return null;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			log.trace("getting first connection from data source");
			connection = DriverManager.getConnection(connectionURL, connectionUsername, connectionPassword);
			//connection = DriverManager.getConnection("jdbc:postgresql://postgres.c4upqzkkv1a8.us-east-1.rds.amazonaws.com:5432/postgres", "postgres", "postgres");
			// connection = DriverManager.getConnection(connectionURL, dbProps);
			log.trace("retreived connection from data source");
		}
		if (connection.isClosed()) {
			log.trace("Connection was closed: getting new connection from data source");
			connection = DriverManager.getConnection(connectionURL, connectionUsername, connectionPassword);
			//connection = DriverManager.getConnection("jdbc:postgresql://postgres.c4upqzkkv1a8.us-east-1.rds.amazonaws.com:5432/postgres", "postgres", "postgres");
			// connection = DriverManager.getConnection(connectionURL, dbProps);
			log.trace("retreived connection from data source");
		}
		return connection;

	}

	/***
	 * This function lets you pass in an object and then writes that object to the
	 * response, so that it can be read by client
	 * 
	 * @param o
	 *            is the object passed in to be written (for example, a List could
	 *            be passed in filled with results from a query)
	 * @param resp
	 *            is the HttpServletResponse that will be returned to the client
	 */
	public static void writeJSONtoResponse(Object o, HttpServletResponse resp) {
		log.debug("writing js to response");
		try {
			// jackson code for converting to json
			ObjectMapper om = new ObjectMapper();
			ObjectWriter ow = om.writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(o);
			log.debug("MY JSON after converting the Object is: " + json);

			// write to response body
			resp.getWriter().print(json);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		DAOUtilities.context = servletContext;

	}

	// package com.capital.one.jdbc;
	//
	// import java.sql.ResultSet;
	// import java.sql.SQLException;
	// import java.util.List;
	//
	// import org.springframework.jdbc.core.JdbcTemplate;
	// import org.springframework.jdbc.core.RowMapper;
	// import org.springframework.jdbc.datasource.SimpleDriverDataSource;
	//
	// public class JDBCDRIVER {
	//
	// public static void main(String[] args) {
	//
	// SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
	// dataSource.setDriver(new org.postgresql.Driver());
	// dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres?currentSchema=chinook");
	// dataSource.setUsername("dvh533");
	// dataSource.setPassword("");
	//
	// JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	// String sql = "";
	//
	// sql = "SELECT * FROM \"Artist\"";
	// List<Artist> listArtists = jdbcTemplate.query(sql, new RowMapper<Artist>() {
	// public Artist mapRow(ResultSet rs, int nRows) throws SQLException {
	// Artist a = new Artist();
	// a.setArtistId(rs.getInt("artistId"));
	// a.setName(rs.getString("name"));
	//
	// return a;
	// }
	// });
	//
	// for (Artist a : listArtists) {
	// System.out.println(a);
	// }

}
