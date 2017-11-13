package com.capital.one.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.RootLogger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.capital.one.datamodelbeans.Credit;


public class EmployeeCreditDaoImpl implements EmployeeCreditDao{
	
	private JdbcTemplate jdbcTemplate;
	
	Logger log = RootLogger.getLogger("EmployeeCreditDaoImpl");
	
	public EmployeeCreditDaoImpl(DataSource datasource) {
		System.out.println("Test");
		jdbcTemplate = new JdbcTemplate(datasource);
	}
	

	@Override
	public int retrieveEmpCreditToGiveBalance(int empId) {
		String sql = "SELECT SUM(credit_togive_balance) FROM employee_credit WHERE emp_id="+ empId; ;
		int creditsToGive = jdbcTemplate.queryForObject(sql,Integer.class);
		return creditsToGive;
	}
	public int creditBucksAvailable(int empId) {
		String sql = "SELECT credit_togive_balance FROM employee_credit WHERE credit_id=1 AND emp_id="+ empId; ;
		int availableCredits = jdbcTemplate.queryForObject(sql,Integer.class);
		return availableCredits;
	}

	public int capOneBucksAvailable(int empId) {
		String sql = "SELECT credit_togive_balance FROM employee_credit WHERE credit_id=2 AND emp_id="+ empId; ;
		int availableCredits = jdbcTemplate.queryForObject(sql,Integer.class);
		return availableCredits;
	}
	@Override
	public void updateEmpCreditsToGiveBalance(int empId, int creditId) throws Exception {

		int availableCredits = creditBucksAvailable(empId);
		log.info("availableCredits!" + availableCredits);
		int caponeBucksAvailable = capOneBucksAvailable(empId);
		log.info("caponeBucksAvailable!!" + caponeBucksAvailable);
		if (creditId == 1 && availableCredits > 0) {
			jdbcTemplate.update(
					"update employee_credit set credit_togive_balance = credit_togive_balance-1 where emp_id=? AND credit_id=? ",
					empId, creditId);}
		else if(creditId == 2 && caponeBucksAvailable >= 5 ){
			jdbcTemplate.update(
					"update employee_credit set credit_togive_balance = credit_togive_balance-5 where emp_id=? AND credit_id=? ",
					empId, creditId);
		}
		
		else {
			throw new Exception("No credits left!");}
	}

	@Override
	public int retrieveEmpCreditEarnedBalance(int empId) {
		String sql = "SELECT SUM(credit_earned_balance) FROM employee_credit WHERE emp_id="+ empId; ;
		int creditsToGive = jdbcTemplate.queryForObject(sql,Integer.class);
		return creditsToGive;
	}

	@Override
	public void updateEmpCreditsEarnedBalance(int nomineeId, int creditId) {
		if(creditId==1){
			jdbcTemplate.update(
					"update employee_credit set credit_earned_balance = credit_earned_balance+1 where emp_id =? AND credit_id=? ",
					nomineeId, creditId);}
		else {
			jdbcTemplate.update(
					"update employee_credit set credit_earned_balance = credit_earned_balance+5 where emp_id=? AND credit_id=? ",
					nomineeId, creditId);}
		}

	

}
