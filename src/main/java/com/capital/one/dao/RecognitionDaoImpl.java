package com.capital.one.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.RootLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.capital.one.datamodelbeans.Employee;
import com.capital.one.datamodelbeans.Recognition;

public class RecognitionDaoImpl implements RecognitionDao{
	
//	@Autowired
//	JDBCConfig con;
	
	private JdbcTemplate jdbcTemplate;
	Logger log = RootLogger.getLogger("RecognitionDaoImpl");
	
//	public RecognitionDaoImpl() {
//		super();
//	}
	
	public RecognitionDaoImpl (DataSource dataSource) {
		System.out.println("Test");
		jdbcTemplate = new JdbcTemplate(dataSource);
		
	}

	private static java.sql.Date getCurrentDate() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Date(today.getTime());
	}
	@Override
	public void insertRecognitionRecord(int creditTypeId, int nominatorId, int nomineeId, String nominee) {

		int increment;
		if (creditTypeId == 1) {
			increment = 1;
		} else {
			increment = 5;
		}
		if (nominee == "Employee") {

			jdbcTemplate.update(
					"INSERT INTO recognition(credit_amount, recognition_date, credit_type_id,emp_nominator_id,emp_nominee_id ) "
							+ "VALUES(?,?,?,?,?)",
					increment, getCurrentDate(), creditTypeId, nominatorId, nomineeId);
		} else {

			jdbcTemplate.update(
					"INSERT INTO recognition(credit_amount, recognition_date, credit_type_id,emp_nominator_id,team_nominee_id ) "
							+ "VALUES(?,?,?,?,?)",
					increment, getCurrentDate(), creditTypeId, nominatorId, nomineeId);
		}
	}

	@Override
	public List<Recognition> getRecognitionHistory() {
		String sql = "SELECT * FROM recognition";
		System.out.println("in DAO implementation 1");
		List<Recognition> listHist = jdbcTemplate.query(sql, new RowMapper<Recognition>() {
		@Override
		public Recognition mapRow(ResultSet rs, int nRows) throws SQLException {
		Recognition creditHistory = new Recognition();
		creditHistory.setRecognitionId(rs.getInt("recognition_id"));
		creditHistory.setEmpNominatorId(rs.getInt("emp_nominator_id"));
		creditHistory.setEmpNomineeId(rs.getInt("emp_nominee_id"));
		creditHistory.setTeamNomineeId(rs.getInt("team_nominee_id"));
		creditHistory.setCreditAmount(rs.getInt("credit_amount"));
		creditHistory.setCreditTypeId(rs.getInt("credit_type_id"));
		creditHistory.setDate(rs.getDate("recognition_date").toLocalDate());
		return creditHistory;
		}
		});
		return listHist;
		}
	
	@Override
	public List<Recognition> getHistoricalGiven(int empId){
	String sql = "SELECT * FROM public.recognition where emp_nominator_id = ?";
	System.out.println("in DAO implementation 2");
	List<Recognition> listRecog = jdbcTemplate.query(sql, new RowMapper<Recognition>() {
	@Override
	public Recognition mapRow(ResultSet rs, int nRows) throws SQLException {
	Recognition creditGiven = new Recognition();
	creditGiven.setEmpNominatorId(rs.getInt("emp_nominator_id"));
    creditGiven.setRecognitionId(rs.getInt("recognition_id"));
    creditGiven.setDate(rs.getDate("recognition_date").toLocalDate());
    // creditGiven.setDate(rs.getTimestamp("recognition_date").toLocalDateTime());
    creditGiven.setEmpNomineeId(rs.getInt("emp_nominee_id"));
    creditGiven.setTeamNomineeId(rs.getInt("team_nominee_id"));
    creditGiven.setCreditAmount(rs.getInt("credit_amount"));
    creditGiven.setCreditTypeId(rs.getInt("credit_type_id"));
    creditGiven.setDate(rs.getDate("recognition_date").toLocalDate());

    
	return creditGiven;}
	}, empId);

	return listRecog;
	}
	
	
	@Override
	public List<Recognition> getHistoricalEarned(int empId, int teamId) {
	String sql = "SELECT * FROM public.recognition where emp_nominee_id = ? OR team_nominee_id = ?";
	System.out.println("in DAO implementation 3");
	List<Recognition> listRecog = jdbcTemplate.query(sql, new RowMapper<Recognition>() {
	public Recognition mapRow(ResultSet rs, int nRows) throws SQLException {
	Recognition creditEarned = new Recognition();
	creditEarned.setRecognitionId(rs.getInt("recognition_id"));
	creditEarned.setEmpNominatorId(rs.getInt("emp_nominator_id"));
	creditEarned.setEmpNomineeId(rs.getInt("emp_nominee_id"));
	creditEarned.setTeamNomineeId(rs.getInt("team_nominee_id"));
	creditEarned.setCreditAmount(rs.getInt("credit_amount"));
	creditEarned.setCreditTypeId(rs.getInt("credit_type_id"));
	

	return creditEarned;
	}
	}, empId, teamId);

	return listRecog;
	}

	@Override
	public int getTotalHistoricalGiven(int empId) {
		String sql = ("SELECT SUM(credit_amount) FROM recognition WHERE emp_nominator_id="+ empId + ";");
		int totalGiven;
		try {
			log.debug("Getting Ready to make SQL cal for totalGiven");
			totalGiven = jdbcTemplate.queryForObject(sql,Integer.class);
			log.debug("totalGiven = " + totalGiven);
		}
		catch (Exception e) {
			totalGiven = 0;
		}
		return totalGiven;
	}

	@Override
	public int getTotalHistoricalEarned(int empId) {
		String sql = ("SELECT SUM(credit_amount) FROM recognition WHERE emp_nominee_id="+ empId + ";");
		int totalEarned;
		
		try {
			log.debug("Getting Ready to make SQL cal for totalEarned");
			totalEarned = jdbcTemplate.queryForObject(sql,Integer.class);
			log.debug("totalEarned = " + totalEarned);
		}
		catch (Exception e) {
			totalEarned = 0;
		}
		
		return totalEarned;
	}



	
	
	
	
}
