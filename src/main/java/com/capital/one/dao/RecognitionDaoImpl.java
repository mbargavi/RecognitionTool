package com.capital.one.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.capital.one.datamodelbeans.Recognition;

@Repository
public class RecognitionDaoImpl implements RecognitionDao{

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

}
