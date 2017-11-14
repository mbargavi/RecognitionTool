package com.capital.one.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.capital.one.datamodelbeans.Award;
import com.capital.one.datamodelbeans.Credit;
import com.capital.one.datamodelbeans.EmployeeCreditName;
import com.capital.one.datamodelbeans.Redemption;
import com.capital.one.datamodelbeans.TeamCreditWithName;

public class RedemptionDaoImpl implements RedemptionDao {

	private JdbcTemplate jdbcTemplate;

	private Logger log = Logger.getLogger("RedemptionDaoImpl");

	public RedemptionDaoImpl(DataSource datasource) {
		System.out.println("Test");
		jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override
	public boolean insertEmpRedemptionRequest(int redeemerId, int creditsUsed, int creditTypeId, int awardId) {
		String insertRedeemRequest = "INSERT INTO redemption (emp_redeemer_id, credits_used, credit_type_id, award_type_id) VALUES (?, ?, ?, ?)";
		log.info("Inserting Employee Redemption");
		int insertRedeem = jdbcTemplate.update(insertRedeemRequest, redeemerId, creditsUsed, creditTypeId, awardId);
		if (insertRedeem > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateEmpCredit(int empRedeemerId, int creditTypeId, int creditsUsed) {
		String updateEmpCreditSQL = "UPDATE employee_credit SET credit_earned_balance = credit_earned_balance - ? WHERE emp_id=? AND credit_id=?";
		log.info("Updating Employee Credit");
		int updateEmpCredit = jdbcTemplate.update(updateEmpCreditSQL, creditsUsed, empRedeemerId, creditTypeId);
		if (updateEmpCredit > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean insertTeamRedemptionRequest(int creditsUsed, int teamRedemptionId, int creditTypeId, int awardId) {
		String insertTeamRedeemRequest = "INSERT INTO redemption (credits_used, team_redemption_id, credit_type_id, award_type_id) VALUES (?, ?, ?, ?)";
		log.info("Inserting Team Redemption");
		int insertTeamRedeem = jdbcTemplate.update(insertTeamRedeemRequest, creditsUsed, teamRedemptionId, creditTypeId,
				awardId);
		if (insertTeamRedeem > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateTeamCredit(int teamRedemptionId, int creditTypeId, int creditsUsed) {
		String updateEmpCreditSQL = "UPDATE team_credit SET credit_earned_balance = credit_earned_balance - ? WHERE team_id=? AND credit_id=?";
		log.info("Updating Team Credit available Balance");
		int updateEmpCredit = jdbcTemplate.update(updateEmpCreditSQL, creditsUsed, teamRedemptionId, creditTypeId);
		if (updateEmpCredit > 0) {
			return true;
		}
		return false;
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
	public List<EmployeeCreditName> getempCredits(int empId) {
		String sql = "SELECT a.emp_id as \"emp_id\", a.credit_id as \"credit_id\", b.credit_name as \"creditName\", a.credit_togive_balance as \"credit_togive_balance\","
				+ "a.credit_earned_balance as \"credit_earned_balance\" FROM public.employee_credit a JOIN public.credit b ON(a.credit_id = b.credit_id)"
				+ "WHERE emp_id=?";
		System.out.println("in DAO implementation");
		List<EmployeeCreditName> empCredits = jdbcTemplate.query(sql, new RowMapper<EmployeeCreditName>() {
			@Override
			public EmployeeCreditName mapRow(ResultSet rs, int nRows) throws SQLException {
				EmployeeCreditName employeeCredits = new EmployeeCreditName();
				employeeCredits.setEmployee_id(rs.getInt("emp_id"));
				employeeCredits.setCredit_id(rs.getInt("credit_id"));
				employeeCredits.setCreditToGiveBalance(rs.getInt("credit_togive_balance"));
				employeeCredits.setCreditEarnedBalance(rs.getInt("credit_earned_balance"));
				employeeCredits.setCreditName(rs.getString("creditName"));
				return employeeCredits;
			}
		}, empId);
		return empCredits;
	}

	@Override
	public List<TeamCreditWithName> getteamCredits(int teamId) {
		String sql = "SELECT a.team_id as \"team_id\", a.credit_id as \"credit_id\", b.credit_name as \"creditName\","
				+ "a.credit_earned_balance as \"credit_earned_balance\" FROM public.team_credit a JOIN public.credit b ON(a.credit_id = b.credit_id)"
				+ "WHERE team_id=?";
		System.out.println("in DAO implementation");
		List<TeamCreditWithName> teamCredits = jdbcTemplate.query(sql, new RowMapper<TeamCreditWithName>() {
			@Override
			public TeamCreditWithName mapRow(ResultSet rs, int nRows) throws SQLException {
				TeamCreditWithName teamCredits = new TeamCreditWithName();
				teamCredits.setTeamId(rs.getInt("team_id"));
				teamCredits.setCreditId(rs.getInt("credit_id"));
				teamCredits.setCreditEarnedBalance(rs.getInt("credit_earned_balance"));
				teamCredits.setCreditName(rs.getString("creditName"));
				return teamCredits;
			}
		}, teamId);
		return teamCredits;
	}

}
