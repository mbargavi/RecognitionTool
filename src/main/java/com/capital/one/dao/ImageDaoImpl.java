package com.capital.one.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.capital.one.datamodelbeans.Award;

@Repository
public class ImageDaoImpl implements ImageDao {
	
	private Logger log = Logger.getLogger("ImageDaoImpl");

	@Override
	public void insertImage(int empId, String name) {
		// TODO Auto-generated method stub
		Statement stmt = null;
		Connection conn = null;
		ResultSet rs;
		try {
			conn = DAOUtilities.getConnection();
			
			stmt = conn.createStatement();
			
			String sql = "SELECT * FROM images WHERE img_emp_id = " + empId + ";";
			rs = stmt.executeQuery(sql);
			if(rs.next()) { //There was already a row for this employee so update it
				sql = "UPDATE images SET img_name = '" + name + "'  WHERE img_emp_id= " + empId + ";";
				rs = stmt.executeQuery(sql);
				rs.next();
			}else {        //There is no record there so need to insert one
				sql = "INSERT INTO images (img_emp_id, img_name) VALUES (" + empId + ", '" + name + "');";
				rs = stmt.executeQuery(sql);
				log.info("Query to insert an image name into the database returned with a code of " + rs);
			}
	
			rs.close();
			
		}
		catch (SQLException sqle) {
			log.error("SQL Exception thrown");
			sqle.printStackTrace();
			return;
		}

		return;
		
	}

	@Override
	public String retrieveImageName(int empId) {
		// TODO Auto-generated method stub
		Statement stmt = null;
		Connection conn = null;
		ResultSet rs;
		String imageName;
		try {
			conn = DAOUtilities.getConnection();
			
			stmt = conn.createStatement();
	
			String sql = "SELECT img_name FROM images WHERE img_emp_id = " + empId + ";";
			
			rs = stmt.executeQuery(sql);
			
			rs.next();
			
			imageName = rs.getString("img_name");
			
			log.info("Image name we are returning from the retrieveImageName function is " + imageName);
			
			return imageName;
	
		}
		catch (SQLException sqle) {
			log.error("SQL Exception thrown");
			sqle.printStackTrace();
			return "NoImage";
		}

	}

}
