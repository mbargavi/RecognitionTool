package com.capital.one.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;


public class TeamCreditDaoImpl implements TeamCreditDao {
	
    private JdbcTemplate jdbcTemplate;
	
	public TeamCreditDaoImpl(DataSource datasource) {
		System.out.println("Test");
		jdbcTemplate = new JdbcTemplate(datasource);
	}
	@Override
	public int retrieveTeamCreditEarnedBalance(int empId, int credTypeId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateTeamCreditsEarnedBalance(int teamId, int creditId) {
		
	if(creditId==1){
			jdbcTemplate.update(
					"update team_credit set credit_earned_balance = credit_earned_balance+1 where team_id =? AND credit_id=? ",
					teamId, creditId);}
		else {
			jdbcTemplate.update(
					"update team_credit set credit_earned_balance = credit_earned_balance+5 where team_id=? AND credit_id=? ",
					teamId, creditId);}
		}

}
