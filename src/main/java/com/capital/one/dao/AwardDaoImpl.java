package com.capital.one.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.capital.one.datamodelbeans.Award;


@Repository
public class AwardDaoImpl implements AwardDao {
	
	private Logger log = Logger.getLogger("AwardDaoImpl");

	@Override
	public void insertAward(String awardName, String awardType, int creditCost) {
		// TODO Auto-generated method stub
		
	
		
	}

	@Override
	public List<Award> getAwardList() {
		// TODO Auto-generated method stub
		Statement stmt = null;
		Connection conn = null;
		ResultSet rs;
		List<Award> awardsList = new ArrayList<Award>();
		try {
			conn = DAOUtilities.getConnection();
			
			stmt = conn.createStatement();
	
			String sql = "SELECT * FROM award";
			
			rs = stmt.executeQuery(sql);
	
			while (rs.next()) {
			System.out.println("in AwardDAO implementation");
			
					Award awards = new Award();
					awards.setAwardId(rs.getInt("award_id"));
					awards.setAwardType(rs.getString("award_type"));
					awards.setAwardName(rs.getString("award_Name"));
					awards.setCreditCost(rs.getInt("credit_cost"));
					awards.setCreditId(rs.getInt("credit_id"));
					awardsList.add(awards);
			}
		}
		catch (SQLException sqle) {
			log.error("SQL Exception thrown");
			sqle.printStackTrace();
			return null;
		}

		return awardsList;
	}

}
