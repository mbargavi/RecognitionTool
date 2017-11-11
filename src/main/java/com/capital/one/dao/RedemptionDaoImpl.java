package com.capital.one.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.capital.one.datamodelbeans.Award;
import com.capital.one.datamodelbeans.Credit;
import com.capital.one.datamodelbeans.EmployeeCredit;
import com.capital.one.datamodelbeans.Redemption;
import com.capital.one.datamodelbeans.TeamCredit;


public class RedemptionDaoImpl implements RedemptionDao {

	private JdbcTemplate jdbcTemplate;
	
	private Logger log = Logger.getLogger("RedemptionDaoImpl");

	public RedemptionDaoImpl(DataSource datasource) {
		System.out.println("Test");
		jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override
	public void insertRedemptionRequest(int redeemerId, int teamRedemptionId, int creditsUsed, int creditTypeId,
			int awardId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Redemption> getRedemptionHistory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Credit> getCreditType() {
		String sql = "SELECT * FROM credit";
		System.out.println("in DAO implementation");
		List<Credit> listCredit = jdbcTemplate.query(sql, new RowMapper<Credit>() {
			public Credit mapRow(ResultSet rs, int nRows) throws SQLException {
				Credit creditType = new Credit();
				creditType.setCreditId(rs.getInt("credit_id"));
				creditType.setCreditName(rs.getString("credit_name"));
				return creditType;
			}
		});

		return listCredit;
	}

	@Override
	public List<Award> getAwardsList(int creditId) {
		String sql = "SELECT * FROM award WHERE credit_id = ?";
		System.out.println("in DAO implementation");
		List<Award> awardsList = jdbcTemplate.query(sql, new RowMapper<Award>() {
			@Override
			public Award mapRow(ResultSet rs, int nRows) throws SQLException {
				Award awards = new Award();
				awards.setAwardId(rs.getInt("award_id"));
				awards.setAwardType(rs.getString("award_type"));
				awards.setAwardName(rs.getString("award_Name"));
				awards.setCreditCost(rs.getInt("credit_cost"));
				awards.setCreditId(rs.getInt("credit_id"));
				return awards;
			}
		}, creditId);

		return awardsList;
	}

	@Override
	public List<EmployeeCredit> getempCredits(int empId) {
		String sql = "SELECT * FROM employee_credit WHERE emp_id=?";
		System.out.println("in DAO implementation");
		List<EmployeeCredit> empCredits = jdbcTemplate.query(sql, new RowMapper<EmployeeCredit>() {
			@Override
			public EmployeeCredit mapRow(ResultSet rs, int nRows) throws SQLException {
				EmployeeCredit employeeCredits = new EmployeeCredit();
				employeeCredits.setEmployee_id(rs.getInt("emp_id"));
				employeeCredits.setCredit_id(rs.getInt("credit_id"));
				employeeCredits.setCreditToGiveBalance(rs.getInt("credit_togive_balance"));
				employeeCredits.setCreditEarnedBalance(rs.getInt("credit_earned_balance"));
				return employeeCredits;
			}
		}, empId);
		return empCredits;
	}

	@Override
	public List<TeamCredit> getteamCredits(int teamId) {
		String sql = "SELECT * FROM team_credit WHERE team_id=?";
		System.out.println("in DAO implementation");
		List<TeamCredit> teamCredits = jdbcTemplate.query(sql, new RowMapper<TeamCredit>() {
			@Override
			public TeamCredit mapRow(ResultSet rs, int nRows) throws SQLException {
				TeamCredit teamCredits = new TeamCredit();
				teamCredits.setTeamId(rs.getInt("team_id"));
				teamCredits.setCreditId(rs.getInt("credit_id"));
				teamCredits.setCreditEarnedBalance(rs.getInt("credit_earned_balance"));
				return teamCredits;
			}
		}, teamId);
		return teamCredits;
	}


}
