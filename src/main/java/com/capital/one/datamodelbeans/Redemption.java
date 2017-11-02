package com.capital.one.datamodelbeans;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Redemption {
	
	private int redemptionId;		// Primary key
	private int empRedeemerId;		// Foreign key to an employee; NOT NULL - if team redemption, a team lead or manager had to redeem
	private int creditsUsed;
	
	private int teamRedemptionId;		// Foreign key to a team; can be null if not a team redemption
	
	private int creditTypeId;		// Foreign key to a credit
	private int awardTypeId;			// Foreign key to an award
	
	@Autowired
	private Credit redemptionCredit;
	@Autowired
	private Award[] redemptionAwardList;
	@Autowired
	private Employee empRedeemer;
	@Autowired
	private Team teamRedeemed;
	
	
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
	public Credit getRedemptionCredit() {
		return redemptionCredit;
	}
	public void setRedemptionCredit(Credit redemptionCredit) {
		this.redemptionCredit = redemptionCredit;
	}
	public Award[] getRedemptionAwardList() {
		return redemptionAwardList;
	}
	public void setRedemptionAwardList(Award[] redemptionAwardList) {
		this.redemptionAwardList = redemptionAwardList;
	}
	public Employee getEmpRedeemer() {
		return empRedeemer;
	}
	public void setEmpRedeemer(Employee empRedeemer) {
		this.empRedeemer = empRedeemer;
	}
	public Team getTeamRedeemed() {
		return teamRedeemed;
	}
	public void setTeamRedeemed(Team teamRedeemed) {
		this.teamRedeemed = teamRedeemed;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + awardTypeId;
		result = prime * result + creditTypeId;
		result = prime * result + creditsUsed;
		result = prime * result + ((empRedeemer == null) ? 0 : empRedeemer.hashCode());
		result = prime * result + empRedeemerId;
		result = prime * result + Arrays.hashCode(redemptionAwardList);
		result = prime * result + ((redemptionCredit == null) ? 0 : redemptionCredit.hashCode());
		result = prime * result + redemptionId;
		result = prime * result + ((teamRedeemed == null) ? 0 : teamRedeemed.hashCode());
		result = prime * result + teamRedemptionId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Redemption other = (Redemption) obj;
		if (awardTypeId != other.awardTypeId)
			return false;
		if (creditTypeId != other.creditTypeId)
			return false;
		if (creditsUsed != other.creditsUsed)
			return false;
		if (empRedeemer == null) {
			if (other.empRedeemer != null)
				return false;
		} else if (!empRedeemer.equals(other.empRedeemer))
			return false;
		if (empRedeemerId != other.empRedeemerId)
			return false;
		if (!Arrays.equals(redemptionAwardList, other.redemptionAwardList))
			return false;
		if (redemptionCredit == null) {
			if (other.redemptionCredit != null)
				return false;
		} else if (!redemptionCredit.equals(other.redemptionCredit))
			return false;
		if (redemptionId != other.redemptionId)
			return false;
		if (teamRedeemed == null) {
			if (other.teamRedeemed != null)
				return false;
		} else if (!teamRedeemed.equals(other.teamRedeemed))
			return false;
		if (teamRedemptionId != other.teamRedemptionId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Redemption [redemptionId=" + redemptionId + ", empRedeemerId=" + empRedeemerId + ", creditsUsed="
				+ creditsUsed + ", teamRedemptionId=" + teamRedemptionId + ", creditTypeId=" + creditTypeId
				+ ", awardTypeId=" + awardTypeId + ", redemptionCredit=" + redemptionCredit + ", redemptionAwardList="
				+ Arrays.toString(redemptionAwardList) + ", empRedeemer=" + empRedeemer + ", teamRedeemed="
				+ teamRedeemed + "]";
	}
	
	
	

}
