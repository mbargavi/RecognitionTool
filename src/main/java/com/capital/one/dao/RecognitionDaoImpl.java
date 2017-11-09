package com.capital.one.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.capital.one.datamodelbeans.Recognition;

public class RecognitionDaoImpl implements RecognitionDao{
	
//	@Autowired
//	JDBCConfig con;
	
	private JdbcTemplate jdbcTemplate;
	
//	public RecognitionDaoImpl() {
//		super();
//	}
	
	public RecognitionDaoImpl (DataSource dataSource) {
		System.out.println("Test");
		jdbcTemplate = new JdbcTemplate(dataSource);
		
	}

	@Override
	public void insertRecognitionRecord(String TEAMorEMPLOYEE, int nominatorId, int nomineeId, int creditTypeId,
			int creditAmount) {
		// TODO Auto-generated method stub
		
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
    //creditGiven.setDate(rs.getDate("recognition_date"));
    creditGiven.setEmpNomineeId(rs.getInt("emp_nominee_id"));
    creditGiven.setTeamNomineeId(rs.getInt("team_nominee_id"));
    creditGiven.setCreditAmount(rs.getInt("credit_amount"));
    creditGiven.setCreditTypeId(rs.getInt("credit_type_id"));
	
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
	creditEarned.setEmpNomineeId(rs.getInt("emp_nominee_id"));
	creditEarned.setTeamNomineeId(rs.getInt("team_nominee_id"));
	return creditEarned;
	}
	}, empId, teamId);

	return listRecog;
	}



	
	
	
	
}
