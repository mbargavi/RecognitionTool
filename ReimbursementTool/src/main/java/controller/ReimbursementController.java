package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.servlets.DefaultServlet;

public class ReimbursementController extends DefaultServlet {
	private LoginController loginController = new LoginController();
	ManagerController mgrReimbursementsController = new ManagerController();
	EmployeeController empController = new EmployeeController();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	
		String requestUrl = req.getRequestURI().substring(req.getContextPath().length());

		if (requestUrl.startsWith("/static/")) {
			super.doGet(req, resp);
			return;
		}
		if (requestUrl.startsWith("/manager/approve/")) {
			mgrReimbursementsController.approveReimbursement(req, resp);
			return;
		}
		if (requestUrl.startsWith("/manager")) {
			mgrReimbursementsController.getReimbursements(req, resp);
			return;
		}
		if (requestUrl.startsWith("/employee")) {
			empController.getAllTicketsForEmployee(req, resp);
			return;
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestUrl = req.getRequestURI().substring(req.getContextPath().length());

		if (requestUrl.startsWith("/users/login")) {
			loginController.validateUser(req, resp);
			return;
		}
		if (requestUrl.startsWith("/employee")) {
			EmployeeController employeeController = new EmployeeController();
			employeeController.addReimbursement(req, resp);
			return;
			
		}
		
	}

}
