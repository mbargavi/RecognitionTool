package service;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import beans.ReimbursementUsersBean;
import impl.ReimbursementUsersImpl;

public class LoginService {
	private static Logger log = Logger.getRootLogger();

	public static ReimbursementUsersBean userLogin(HttpServletRequest req) {
		ReimbursementUsersImpl reimbUsers = new ReimbursementUsersImpl();
		log.debug(req.getRequestURI() + "inside login");
		String username = req.getParameter("UserId");
		String password = req.getParameter("Password");
		log.debug("user " + username + " is trying to login with" + password);
        ReimbursementUsersBean reimbUser = reimbUsers.findByUsername(username, password);
		if (reimbUser != null) {
			req.getSession().setAttribute("currentUser", reimbUser);
			log.info("user " + username + " succesfully logged in");
		}
		return reimbUser;

	}
}
