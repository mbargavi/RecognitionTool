package com.capital.one.datamodelbeans;

import org.springframework.stereotype.Component;

@Component
public class EmployeeCreditName {
	
	private int employee_id;
	private int credit_id;	
	private int creditToGiveBalance;
	private int creditEarnedBalance;
	private String creditName;
	public EmployeeCreditName() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public int getCredit_id() {
		return credit_id;
	}
	public void setCredit_id(int credit_id) {
		this.credit_id = credit_id;
	}
	public int getCreditToGiveBalance() {
		return creditToGiveBalance;
	}
	public void setCreditToGiveBalance(int creditToGiveBalance) {
		this.creditToGiveBalance = creditToGiveBalance;
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
		result = prime * result + ((creditName == null) ? 0 : creditName.hashCode());
		result = prime * result + creditToGiveBalance;
		result = prime * result + credit_id;
		result = prime * result + employee_id;
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
		EmployeeCreditName other = (EmployeeCreditName) obj;
		if (creditEarnedBalance != other.creditEarnedBalance)
			return false;
		if (creditName == null) {
			if (other.creditName != null)
				return false;
		} else if (!creditName.equals(other.creditName))
			return false;
		if (creditToGiveBalance != other.creditToGiveBalance)
			return false;
		if (credit_id != other.credit_id)
			return false;
		if (employee_id != other.employee_id)
			return false;
		return true;
	}
	public EmployeeCreditName(int employee_id, int credit_id, int creditToGiveBalance, int creditEarnedBalance,
			String creditName) {
		super();
		this.employee_id = employee_id;
		this.credit_id = credit_id;
		this.creditToGiveBalance = creditToGiveBalance;
		this.creditEarnedBalance = creditEarnedBalance;
		this.creditName = creditName;
	}
	@Override
	public String toString() {
		return "EmployeeCreditName [employee_id=" + employee_id + ", credit_id=" + credit_id + ", creditToGiveBalance="
				+ creditToGiveBalance + ", creditEarnedBalance=" + creditEarnedBalance + ", creditName=" + creditName
				+ "]";
	}

}
