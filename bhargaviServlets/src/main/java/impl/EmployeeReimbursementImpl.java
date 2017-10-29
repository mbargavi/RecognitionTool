package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import beans.ReimbursementBean;

public class EmployeeReimbursementImpl extends ManagerReimbursementImpl{


	public void addReimbursement(ReimbursementBean acc) {

		try {
			Connection conn = conUtil.getConnection();
			ReimbursementBean reimb = new ReimbursementBean();
			reimb=acc;
			String SQL = "INSERT INTO reimbursement.ERS_REIMBURSEMENT ( REIMB_AMOUNT, REIMB_SUBMITTED, REIMB_DESCRIPTION ,REIMB_RESOLVER ,REIMB_AUTHOR, REIMB_STATUS_ID,REIMB_TYPE_ID ) "
					+ "VALUES (?,?,?,?,?,?,?) ";
			
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			//pstmt.setInt(1, reimb.getReimbId());
			pstmt.setInt(1, reimb.getReimbAmount());
			pstmt.setTimestamp(2, getCurrentTimeStamp());
			pstmt.setString(3, reimb.getReimbDescription());
			pstmt.setInt(4, 1);
			pstmt.setInt(5, reimb.getReimbAuthor());
			pstmt.setInt(6, 1);
			pstmt.setInt(7, 1);

			int numberOfRowsAffected = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();

			System.out.println("num " + numberOfRowsAffected);
			

		}

		catch (SQLException e) {

			e.printStackTrace();
		}

	}
	
	public List<ReimbursementBean> getReimbursement(int employeeID, String option) {
		Connection conn = conUtil.getConnection();
		List<ReimbursementBean> reibursement_list = new ArrayList<ReimbursementBean>();

		try {
			String SQL;
			
			switch (option) {
			case "viewEmpPending":
				SQL = "select * from reimbursement.ers_reimbursement AS reimb JOIN reimbursement.ers_users AS users ON (reimb.reimb_author=users.ers_users_id) where users.ers_users_id=? AND reimb_status_id=1";
				break;
			case "viewEmpApproved":
				SQL = "select * from reimbursement.ers_reimbursement AS reimb JOIN reimbursement.ers_users AS users ON (reimb.reimb_author=users.ers_users_id) where users.ers_users_id=? AND reimb_status_id=2";
				break;
			case "viewEmpDeclined":
				SQL = "select * from reimbursement.ers_reimbursement AS reimb JOIN reimbursement.ers_users AS users ON (reimb.reimb_author=users.ers_users_id) where users.ers_users_id=? AND reimb_status_id=3";
				break;
			default:
				SQL = "select * from reimbursement.ers_reimbursement AS reimb JOIN reimbursement.ers_users AS users ON (reimb.reimb_author=users.ers_users_id) where users.ers_users_id=?";
				break;
			}

			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			pstmt.setInt(1, employeeID);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				reibursement_list.add(new ReimbursementBean(rs.getInt("reimb_id"),

						rs.getInt("reimb_amount"), rs.getTimestamp("reimb_submitted"),
						rs.getTimestamp("reimb_resolved"), rs.getString("reimb_description"),
						rs.getByte("reimb_receipt"), rs.getInt("reimb_author"), rs.getInt("reimb_resolver"),
						rs.getInt("reimb_status_id"), rs.getInt("reimb_type_id")));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		System.out.println(reibursement_list);
		return reibursement_list;

	}
	
	   public List<ReimbursementBean> getAllTicketsForEmployee(int employeeId) {
		   List<ReimbursementBean> reibursement_list = new ArrayList<ReimbursementBean>();
	        Connection conn = null;
	        String SQL = "SELECT * FROM reimbursement.ers_reimbursement WHERE reimb_author = ?";
	        PreparedStatement pstmt = null;
	        try {
	            conn = conUtil.getConnection();
	            pstmt = conn.prepareStatement(SQL);
	            pstmt.setInt(1, employeeId);
	            ResultSet rs = pstmt.executeQuery();

	            while (rs.next()) {
	            	ReimbursementBean reimb = new ReimbursementBean();

	                reimb.setReimbId(rs.getInt("reimb_id"));
	                reimb.setReimbAmount(rs.getInt("reimb_amount"));
	                reimb.setReimbSubmitted(rs.getTimestamp("reimb_submitted"));
	                reimb.setReimbResolved(rs.getTimestamp("reimb_resolved"));
	                reimb.setReimbDescription(rs.getString("reimb_description"));
	                // add reimbReciept later
	                reimb.setReimbAuthor(rs.getInt("reimb_author"));
	                reimb.setReimbResolver(rs.getInt("reimb_resolver"));
	                reimb.setReimbStatusId(rs.getInt("reimb_status_id"));
	                reimb.setReimbTypeId(rs.getInt("reimb_type_id"));

	                reibursement_list.add(reimb);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (pstmt != null) {
	                    pstmt.close();
	                }
	                if (conn != null) {
	                    conn.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        return reibursement_list;
	    }
}
