package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import beans.ReimbursementUsersBean;
import dao.ReimbursementUsersDao;
import utilities.ConnectionUtility;

public class ReimbursementUsersImpl implements ReimbursementUsersDao {
	private Logger log = Logger.getRootLogger();
	ConnectionUtility conUtil = new ConnectionUtility();

	public ReimbursementUsersBean findByUsername(String username, String password) {
		log.debug("attempting to find User by username and password");
		try {
			Connection conn = conUtil.getConnection();

			PreparedStatement stmt = conn.prepareStatement(
					"select * from reimbursement.ers_users WHERE ers_username = ? AND ers_password = ?");
			stmt.setString(1, username);
			stmt.setString(2, password);

			ResultSet rs = stmt.executeQuery();
			ReimbursementUsersBean user = null;
			if (rs.next()) {
				user = new ReimbursementUsersBean(rs.getInt("ers_users_id"), rs.getString("ers_username"),
						rs.getString("ers_password"), rs.getString("user_first_name"), rs.getString("user_last_name"),
						rs.getString("user_email"), rs.getInt("user_role_id"));
				log.debug("successfully retreived user by id");
			}
			return user;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getUserRoleId() {
		// TODO Auto-generated method stub
		return 0;
	}
}
