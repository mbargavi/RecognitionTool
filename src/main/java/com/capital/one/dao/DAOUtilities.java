package com.capital.one.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * Class used to retrieve DAO Implementations. Serves as a factory. Also used to create JDBC connections.
 * 
 * @author David Crites
 *
 */
public class DAOUtilities {

    
//    private static EmployeeDAOImpl employeeDaoImpl;
//    private static FinanceManagerDao fianceManagerDaoImpl;

    private static Connection connection;
    private static Properties dbProps = new Properties();
//	private static String connectionUsername = "";
//	private static String connectionPassword = "";
	private static String connectionURL = "";
    
    private static Logger log = Logger.getLogger("DAOUtilities");
/*
 * We can write some functions below that will return a DAOimplementation but return it as an Interface, and manage this
 * so that if we already have one defined we return the same one.
 */
//    public static synchronized EmployeeDAO getEmployeeDao() {
//        if (employeeDaoImpl == null) {
//            employeeDaoImpl = new EmployeeDAOImpl();
//        }
//        return employeeDaoImpl;
//    }
//
//    public static synchronized FinanceManagerDao getFinanceManagerDao() {
//        if (fianceManagerDaoImpl == null) {
//            fianceManagerDaoImpl = new FinanceManagerDaoImpl();
//        }
//        return fianceManagerDaoImpl;
//    }

    /***
     * This function is managing connections by returning the same connection for use if we still have it
     * @return returns a Connection to the DAO implementation needing a connection to connect to the database
     * @throws SQLException
     */
    public static synchronized Connection getConnection() throws SQLException {
    	
    	    log.debug("Attempting to get connection");
        //Properties dbProps = new Properties();

        if (connection == null) {
            log.trace("connection was null, registering driver");
            try {
                Class.forName("org.postgresql.Driver");
                log.trace("getting initial pull of connection parameters from database.properties and storing");
                // TODO: FIND RELATIVE PATH FOR TOMCAT SERVER TO FIND PROPERTIES FILE
                dbProps.load(new FileInputStream(
                        "/Users/den421/RecognitionTool/src/main/resources/database.properties"));
                
//                connectionUsername = dbProps.getProperty("username");
//                connectionPassword = dbProps.getProperty("password");
                connectionURL = dbProps.getProperty("url");
                //Setting the below properties and changing the format of the Driver call to (URL,dbProps)
                //to try to prevent socket connections from closing
                dbProps.setProperty("tcpKeepAlive", "true");
                dbProps.setProperty("loginTimeout", "5");   //specified in seconds
                dbProps.setProperty("connectTimeout", "0"); //"0" means this is disabled
                dbProps.setProperty("socketTimeout", "0");  //"0" means this is disabled

            }
            catch (ClassNotFoundException e) {
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
            //connection = DriverManager.getConnection(connectionURL, connectionUsername, connectionPassword);
            connection = DriverManager.getConnection(connectionURL, dbProps);
            log.trace("retreived connection from data source");
        }
        if (connection.isClosed()) {
	            	log.trace("Connection was closed: getting new connection from data source");
	            //connection = DriverManager.getConnection(connectionURL, connectionUsername, connectionPassword);
	            	connection = DriverManager.getConnection(connectionURL, dbProps);
	            	log.trace("retreived connection from data source");
	    }
	    return connection;

    }
    /***
     * This function lets you pass in an object and then writes that object to the response, so that it can be read by client
     * @param o is the object passed in to be written (for example, a List could be passed in filled with results from a query)
     * @param resp is the HttpServletResponse that will be returned to the client
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

}
