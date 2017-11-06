package com.capital.one.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.capital.one.datamodelbeans.Award;
import com.capital.one.datamodelbeans.Credit;
import com.capital.one.datamodelbeans.Redemption;

@Repository
public class RedemptionDaoImpl implements RedemptionDao {

	@Autowired
	JDBCConfig con;

	private JdbcTemplate jdbcTemplate;
	
	

	public RedemptionDaoImpl() {
		super();
		// Default Constructor
	}

	public RedemptionDaoImpl(DataSource con) {
		System.out.println("Test");
		jdbcTemplate = new JdbcTemplate(con);
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
	public List<Award> getAwardsList() {
		String sql = "SELECT * FROM award";
		System.out.println("in DAO implementation");
		List<Award> awardsList = jdbcTemplate.query(sql, new RowMapper<Award>() {
			public Award mapRow(ResultSet rs, int nRows) throws SQLException {
				Award awards = new Award();
				awards.setAwardId(rs.getInt("award_id"));
				awards.setAwardType(rs.getString("award_type"));
				awards.setAwardName(rs.getString("award_Name"));
				awards.setCreditCost(rs.getInt("credit_cost"));
				awards.setCreditId(rs.getInt("credit_id"));
				return awards;
			}
		});

		return awardsList;
	}

	//
	// private JdbcTemplate jdbcTemplate;
	//
	// public RedemptionDaoImpl(DataSource con) {
	// System.out.println("Test");
	// jdbcTemplate = new JdbcTemplate(con);
	// }
	//
	// @Override
	// public void insertRedemptionRequest(int redeemerId, int teamRedemptionId, int
	// creditsUsed, int creditTypeId,
	// int awardId) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public List<Redemption> getRedemptionHistory() {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public List<Credit> getCreditType() {
	//
	// String sql = "SELECT * FROM credit";
	// System.out.println("in DAO implementation");
	// List<Credit> listCredit = jdbcTemplate.query(sql, new RowMapper<Credit>() {
	// public Credit mapRow(ResultSet rs, int nRows) throws SQLException {
	// Credit creditType = new Credit();
	// creditType.setCreditId(rs.getInt("credit_id"));
	// creditType.setCreditName(rs.getString("credit_name"));
	// return creditType;
	// }
	// });
	//
	// return listCredit;
	// }
	//
	// @Override
	// public List<Award> getAwardsList() {
	// String sql = "SELECT * FROM award";
	// System.out.println("in DAO implementation");
	// List<Award> awardsList = jdbcTemplate.query(sql, new RowMapper<Award>() {
	// public Award mapRow(ResultSet rs, int nRows) throws SQLException {
	// Award awards = new Award();
	// awards.setAwardId(rs.getInt("award_id"));
	// awards.setAwardType(rs.getString("award_type"));
	// awards.setAwardName(rs.getString("award_Name"));
	// awards.setCreditCost(rs.getInt("credit_cost"));
	// awards.setCreditId(rs.getInt("credit_id"));
	// return awards;
	// }
	// });
	//
	// return awardsList;
	// }

}
