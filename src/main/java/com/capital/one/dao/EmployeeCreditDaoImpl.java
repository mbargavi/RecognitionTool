package com.capital.one.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.capital.one.datamodelbeans.Credit;


public class EmployeeCreditDaoImpl implements EmployeeCreditDao{
	
	private JdbcTemplate jdbcTemplate;
	
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

	@Override
	public void updateEmpCreditsToGiveBalance(int empId, int credTypeId, int credAmount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int retrieveEmpCreditEarnedBalance(int empId, int credTypeId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateEmpCreditsEarnedBalance(int empId, int credTypeId, int credAmount) {
		// TODO Auto-generated method stub
		
	}

	

}
