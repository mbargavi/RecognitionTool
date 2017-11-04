package com.capital.one.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.capital.one.datamodelbeans.Award;

@Repository
public class AwardDaoImpl implements AwardDao {

	@Override
	public void insertAward(String awardName, String awardType, int creditCost) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Award> getAwardList() {
		// TODO Auto-generated method stub
		return null;
	}

}
