package com.capital.one.datamodelbeans;

import org.springframework.stereotype.Component;

@Component
public class Award {
	
	private int awardId;
	private String awardType;
	private String awardName;
	private int creditCost;
	private int creditId;
	// NOTE: I am not assigning a specific creditTypeId here...it will be a business decision what credit types can be used to purchase
	// a given awardType
	public int getAwardId() {
		return awardId;
	}
	public void setAwardId(int awardId) {
		this.awardId = awardId;
	}
	public String getAwardType() {
		return awardType;
	}
	public void setAwardType(String awardType) {
		this.awardType = awardType;
	}
	public String getAwardName() {
		return awardName;
	}
	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}
	public int getCreditCost() {
		return creditCost;
	}
	public void setCreditCost(int creditCost) {
		this.creditCost = creditCost;
	}
	public int getCreditId() {
		return creditId;
	}
	public void setCreditId(int creditId) {
		this.creditId = creditId;
	}
	@Override
	public String toString() {
		return "Award [awardId=" + awardId + ", awardType=" + awardType + ", awardName=" + awardName + ", creditCost="
				+ creditCost + ", creditId=" + creditId + "]";
	}
	

}
