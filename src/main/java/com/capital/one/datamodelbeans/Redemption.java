package com.capital.one.datamodelbeans;

import org.springframework.stereotype.Component;

@Component
public class Redemption {
	
	private int redemptionId;		// Primary key
	private int empRedeemerId;		// Foreign key to an employee; NOT NULL - if team redemption, a team lead or manager had to redeem
	private int creditsUsed;	
	private int teamRedemptionId;		// Foreign key to a team; can be null if not a team redemption	
	private int creditTypeId;		// Foreign key to a credit
	private int awardTypeId;			// Foreign key to an award
	private String creditTypeToUse;
	
	public int getRedemptionId() {
		return redemptionId;
	}
	public void setRedemptionId(int redemptionId) {
		this.redemptionId = redemptionId;
	}
	public int getEmpRedeemerId() {
		return empRedeemerId;
	}
	public void setEmpRedeemerId(int empRedeemerId) {
		this.empRedeemerId = empRedeemerId;
	}
	public int getCreditsUsed() {
		return creditsUsed;
	}
	public void setCreditsUsed(int creditsUsed) {
		this.creditsUsed = creditsUsed;
	}
	public int getTeamRedemptionId() {
		return teamRedemptionId;
	}
	public void setTeamRedemptionId(int teamRedemptionId) {
		this.teamRedemptionId = teamRedemptionId;
	}
	public int getCreditTypeId() {
		return creditTypeId;
	}
	public void setCreditTypeId(int creditTypeId) {
		this.creditTypeId = creditTypeId;
	}
	public int getAwardTypeId() {
		return awardTypeId;
	}
	public void setAwardTypeId(int awardTypeId) {
		this.awardTypeId = awardTypeId;
	}
	public String getCreditTypeToUse() {
		return creditTypeToUse;
	}
	public void setCreditTypeToUse(String creditTypeToUse) {
		this.creditTypeToUse = creditTypeToUse;
	}
	@Override
	public String toString() {
		return "Redemption [redemptionId=" + redemptionId + ", empRedeemerId=" + empRedeemerId + ", creditsUsed="
				+ creditsUsed + ", teamRedemptionId=" + teamRedemptionId + ", creditTypeId=" + creditTypeId
				+ ", awardTypeId=" + awardTypeId + ", creditTypeToUse=" + creditTypeToUse + "]";
	}
		
	public Redemption() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Redemption(int redemptionId, int empRedeemerId, int creditsUsed, int teamRedemptionId, int creditTypeId,
			int awardTypeId, String creditTypeToUse) {
		super();
		this.redemptionId = redemptionId;
		this.empRedeemerId = empRedeemerId;
		this.creditsUsed = creditsUsed;
		this.teamRedemptionId = teamRedemptionId;
		this.creditTypeId = creditTypeId;
		this.awardTypeId = awardTypeId;
		this.creditTypeToUse = creditTypeToUse;
	}	

}
