package com.capital.one.datamodelbeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeamCredit {
	
	private int teamId;
	private int creditId;
	
	private int creditEarnedBalance;
	
	@Autowired
	private Team team;
	@Autowired
	private Credit credit;
	
	
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
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public Credit getCredit() {
		return credit;
	}
	public void setCredit(Credit credit) {
		this.credit = credit;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((credit == null) ? 0 : credit.hashCode());
		result = prime * result + creditEarnedBalance;
		result = prime * result + creditId;
		result = prime * result + ((team == null) ? 0 : team.hashCode());
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
		TeamCredit other = (TeamCredit) obj;
		if (credit == null) {
			if (other.credit != null)
				return false;
		} else if (!credit.equals(other.credit))
			return false;
		if (creditEarnedBalance != other.creditEarnedBalance)
			return false;
		if (creditId != other.creditId)
			return false;
		if (team == null) {
			if (other.team != null)
				return false;
		} else if (!team.equals(other.team))
			return false;
		if (teamId != other.teamId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TeamCredit [teamId=" + teamId + ", creditId=" + creditId + ", creditEarnedBalance="
				+ creditEarnedBalance + ", team=" + team + ", credit=" + credit + "]";
	}
	
	

}
