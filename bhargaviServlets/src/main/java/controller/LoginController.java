package controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import beans.ReimbursementUsersBean;
import service.LoginService;

public class LoginController {
	private Logger log = Logger.getRootLogger();

	public boolean validateUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		ReimbursementUsersBean reimbUser = LoginService.userLogin(req);
		if (reimbUser == null) {
			resp.sendRedirect("/ReimbursementTool/static/loginRetry.html");
		}

		else if (reimbUser.getUserRoleId() == 101) {
			resp.sendRedirect("/ReimbursementTool/static/manager.html");
		} else {

			resp.sendRedirect("/ReimbursementTool/static/Employee.html");
		}

		return false;

	}

}
