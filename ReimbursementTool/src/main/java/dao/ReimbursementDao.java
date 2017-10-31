package dao;

import java.util.List;

import beans.ReimbursementBean;

public interface ReimbursementDao {

	void addReimbursement(ReimbursementBean acc);

	List<ReimbursementBean> getReimbursement(int employeeID, String option);
	
}
