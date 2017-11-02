package com.capital.one.datamodelbeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeCredit {
	
	private int employee_id;
	private int credit_id;
	
	private int creditToGiveBalance;
	private int creditEarnedBalance;
	
	@Autowired
	private Employee emp;
	@Autowired
	private Credit cred;
	
	
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
	public Employee getEmp() {
		return emp;
	}
	public void setEmp(Employee emp) {
		this.emp = emp;
	}
	public Credit getCred() {
		return cred;
	}
	public void setCred(Credit cred) {
		this.cred = cred;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cred == null) ? 0 : cred.hashCode());
		result = prime * result + creditEarnedBalance;
		result = prime * result + creditToGiveBalance;
		result = prime * result + credit_id;
		result = prime * result + ((emp == null) ? 0 : emp.hashCode());
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
		EmployeeCredit other = (EmployeeCredit) obj;
		if (cred == null) {
			if (other.cred != null)
				return false;
		} else if (!cred.equals(other.cred))
			return false;
		if (creditEarnedBalance != other.creditEarnedBalance)
			return false;
		if (creditToGiveBalance != other.creditToGiveBalance)
			return false;
		if (credit_id != other.credit_id)
			return false;
		if (emp == null) {
			if (other.emp != null)
				return false;
		} else if (!emp.equals(other.emp))
			return false;
		if (employee_id != other.employee_id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "EmployeeCredit [employee_id=" + employee_id + ", credit_id=" + credit_id + ", creditToGiveBalance="
				+ creditToGiveBalance + ", creditEarnedBalance=" + creditEarnedBalance + ", emp=" + emp + ", cred="
				+ cred + "]";
	}
	
	

}
