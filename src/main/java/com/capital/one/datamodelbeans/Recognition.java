package com.capital.one.datamodelbeans;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

//@Component
public class Recognition {
	
	private int recognitionId;
	private int empNominatorId;
	
	private int empNomineeId;
	private int teamNomineeId;
	private int creditAmount;
	private int creditTypeId;
	private LocalDate date;
	private String nominee;
	private int nomineeId;
	
	@Autowired
	private Credit credit;
	@Autowired
	private Employee nominator;
	@Autowired
	private Employee empNominee;
	@Autowired
	private Team teamNominee;
	
	
	public int getRecognitionId() {
		return recognitionId;
	}
	public void setRecognitionId(int recognitionId) {
		this.recognitionId = recognitionId;
	}
	public int getEmpNominatorId() {
		return empNominatorId;
	}
	public void setEmpNominatorId(int empNominatorId) {
		this.empNominatorId = empNominatorId;
	}
	public int getEmpNomineeId() {
		return empNomineeId;
	}
	public void setEmpNomineeId(int empNomineeId) {
		this.empNomineeId = empNomineeId;
	}
	public int getTeamNomineeId() {
		return teamNomineeId;
	}
	public void setTeamNomineeId(int teamNomineeId) {
		this.teamNomineeId = teamNomineeId;
	}
	public int getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(int creditAmount) {
		this.creditAmount = creditAmount;
	}
	public int getCreditTypeId() {
		return creditTypeId;
	}
	public void setCreditTypeId(int creditTypeId) {
		this.creditTypeId = creditTypeId;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Credit getCredit() {
		return credit;
	}
	public void setCredit(Credit credit) {
		this.credit = credit;
	}
	public Employee getNominator() {
		return nominator;
	}
	public void setNominator(Employee nominator) {
		this.nominator = nominator;
	}
	public Employee getEmpNominee() {
		return empNominee;
	}
	public void setEmpNominee(Employee empNominee) {
		this.empNominee = empNominee;
	}
	public Team getTeamNominee() {
		return teamNominee;
	}
	public void setTeamNominee(Team teamNominee) {
		this.teamNominee = teamNominee;
	}
	public int getNomineeId() {
		return nomineeId;
	}
	public void setNomineeId(int nomineeId) {
		this.nomineeId = nomineeId;
	}
	public String getNominee() {
		return nominee;
	}
	public void setNominee(String nominee) {
		this.nominee = nominee;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((credit == null) ? 0 : credit.hashCode());
		result = prime * result + creditAmount;
		result = prime * result + creditTypeId;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + empNominatorId;
		result = prime * result + ((empNominee == null) ? 0 : empNominee.hashCode());
		result = prime * result + empNomineeId;
		result = prime * result + ((nominator == null) ? 0 : nominator.hashCode());
		result = prime * result + recognitionId;
		result = prime * result + ((teamNominee == null) ? 0 : teamNominee.hashCode());
		result = prime * result + teamNomineeId;
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
		Recognition other = (Recognition) obj;
		if (credit == null) {
			if (other.credit != null)
				return false;
		} else if (!credit.equals(other.credit))
			return false;
		if (creditAmount != other.creditAmount)
			return false;
		if (creditTypeId != other.creditTypeId)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (empNominatorId != other.empNominatorId)
			return false;
		if (empNominee == null) {
			if (other.empNominee != null)
				return false;
		} else if (!empNominee.equals(other.empNominee))
			return false;
		if (empNomineeId != other.empNomineeId)
			return false;
		if (nominator == null) {
			if (other.nominator != null)
				return false;
		} else if (!nominator.equals(other.nominator))
			return false;
		if (recognitionId != other.recognitionId)
			return false;
		if (teamNominee == null) {
			if (other.teamNominee != null)
				return false;
		} else if (!teamNominee.equals(other.teamNominee))
			return false;
		if (teamNomineeId != other.teamNomineeId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Recognition [recognitionId=" + recognitionId + ", empNominatorId=" + empNominatorId + ", empNomineeId="
				+ empNomineeId + ", teamNomineeId=" + teamNomineeId + ", creditAmount=" + creditAmount
				+ ", creditTypeId=" + creditTypeId + ", date=" + date + ", credit=" + credit + ", nominator="
				+ nominator + ", empNominee=" + empNominee + ", teamNominee=" + teamNominee + "]";
	}
	
	

}
