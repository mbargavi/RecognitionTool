package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import beans.ReimbursementBean;
import dao.ReimbursementDao;
import utilities.ConnectionUtility;

abstract class ManagerReimbursementImpl implements ReimbursementDao {

	ConnectionUtility conUtil = new ConnectionUtility();
	private Logger log = Logger.getRootLogger();

	public static Timestamp getCurrentTimeStamp() {
		java.util.Date today = new java.util.Date();
		return new Timestamp(today.getTime());

	}

	public List<ReimbursementBean> getReimbursementsForManager(String type) {
		Connection conn = conUtil.getConnection();
		List<ReimbursementBean> reibursement_list = new ArrayList<ReimbursementBean>();
		String SQL;
		try {
			switch (type) {
			case "viewPending":
				SQL = "select * from reimbursement.ers_reimbursement where reimb_status_id=1";
				break;
			case "viewApproved":
				SQL = "select * from reimbursement.ers_reimbursement where reimb_status_id=2";
				break;
			case "viewDeclined":
				SQL = "select * from reimbursement.ers_reimbursement where reimb_status_id=3";
				break;
			default:
				SQL = "select * from reimbursement.ers_reimbursement";
				break;
			}

			PreparedStatement pstmt = conn.prepareStatement(SQL);

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
		return reibursement_list;

	}

	public void processReimbursement(int reimbID, String status) {
		Connection conn = conUtil.getConnection();
		ReimbursementBean reimb = new ReimbursementBean();
		System.out.println("im status" + status);
		if (status.equals("approve")) {
			reimb.setReimbStatusId(2);
		} else {
			reimb.setReimbStatusId(3);
		}

		try {
			String SQL = "UPDATE reimbursement.ers_reimbursement SET reimb_status_id=? WHERE reimb_id =?;";
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, reimb.getReimbStatusId());
			pstmt.setInt(2, reimbID);
			int numberOfRowsAffected = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
