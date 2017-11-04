package com.capital.one.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.capital.one.datamodelbeans.Redemption;

@Repository
public class RedemptionDaoImpl implements RedemptionDao {

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

}
