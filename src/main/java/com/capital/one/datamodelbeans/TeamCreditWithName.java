package com.capital.one.datamodelbeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeamCreditWithName {
	
	private int teamId;
	private int creditId;	
	private int creditEarnedBalance;
	private String creditName;
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public int getCreditId() {
		return creditId;
	}
	public void setCreditId(int creditId) {
		this.creditId = creditId;
	}
	public int getCreditEarnedBalance() {
		return creditEarnedBalance;
	}
	public void setCreditEarnedBalance(int creditEarnedBalance) {
		this.creditEarnedBalance = creditEarnedBalance;
	}
	public String getCreditName() {
		return creditName;
	}
	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + creditEarnedBalance;
		result = prime * result + creditId;
		result = prime * result + ((creditName == null) ? 0 : creditName.hashCode());
		result = prime * result + teamId;
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
		TeamCreditWithName other = (TeamCreditWithName) obj;
		if (creditEarnedBalance != other.creditEarnedBalance)
			return false;
		if (creditId != other.creditId)
			return false;
		if (creditName == null) {
			if (other.creditName != null)
				return false;
		} else if (!creditName.equals(other.creditName))
			return false;
		if (teamId != other.teamId)
			return false;
		return true;
	}
	
	public TeamCreditWithName() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TeamCreditWithName(int teamId, int creditId, int creditEarnedBalance, String creditName) {
		super();
		this.teamId = teamId;
		this.creditId = creditId;
		this.creditEarnedBalance = creditEarnedBalance;
		this.creditName = creditName;
	}
	@Override
	public String toString() {
		return "TeamCredit [teamId=" + teamId + ", creditId=" + creditId + ", creditEarnedBalance="
				+ creditEarnedBalance + ", creditName=" + creditName + "]";
	}	

}
