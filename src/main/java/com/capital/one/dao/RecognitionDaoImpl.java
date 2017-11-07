package com.capital.one.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.capital.one.datamodelbeans.Credit;
import com.capital.one.datamodelbeans.Recognition;

@Repository
public class RecognitionDaoImpl implements RecognitionDao {

	@Autowired
	JDBCConfig con;

	private JdbcTemplate jdbcTemplate;

	public RecognitionDaoImpl() {
		super();
	}

	public RecognitionDaoImpl (DataSource con) {
		System.out.println("Test");
		jdbcTemplate = new JdbcTemplate(con);
		
	}

	@Override
	public void insertRecognitionRecord(String TEAMorEMPLOYEE, int nominatorId, int nomineeId, int creditTypeId,
			int creditAmount) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Recognition> getRecognitionHistory() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public List<Recognition> getCreditEarned() {
		String sql = "SELECT * FROM recognition where emp_nominator_id = ?";
		System.out.println("in DAO implementation");
		List<Recognition> listRecog = jdbcTemplate.query(sql, new RowMapper<Recognition>() {
			public Recognition mapRow(ResultSet rs, int nRows) throws SQLException {
				Recognition creditEarned = new Recognition();
				creditEarned.setEmpNominatorId(rs.getInt("emp_id"));
				
				return creditEarned;
			}
		});

		return listRecog;
	}
	
	@Override
	public List<Recognition> getCreditGiven(){
		String sql = "SELECT * FROM recognition where emp_nominator_id = ?";
		System.out.println("in DAO implementation");
		List<Recognition> listRecog = jdbcTemplate.query(sql, new RowMapper<Recognition>() {
			public Recognition mapRow(ResultSet rs, int nRows) throws SQLException {
				Recognition creditGiven = new Recognition();
				creditGiven.setEmpNominatorId(rs.getInt("emp_id"));
				creditGiven.setTeamNomineeId(rs.getInt("team_id"));
				
				return creditGiven;
			}
		});

		return listRecog;
	}
}
