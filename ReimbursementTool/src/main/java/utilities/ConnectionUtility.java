package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtility {

	public Connection getConnection() {
		Properties dbProps = new Properties();
		
		
		
		try {
			Class.forName("org.postgresql.Driver");
			dbProps.load(new FileInputStream("/Users/ins315/Documents/workspace/BootCampWeek1/ReimbursementTool/src/main/resources/database.properties"));

			return DriverManager.getConnection(dbProps.getProperty("url"), dbProps.getProperty("username"),
					dbProps.getProperty("password"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}
