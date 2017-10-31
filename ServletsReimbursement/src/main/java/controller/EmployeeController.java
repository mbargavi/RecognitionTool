package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.ReimbursementUsersBean;
import service.EmployeeService;


public class EmployeeController {

	public void addReimbursement(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		EmployeeService employeeService = new EmployeeService();
		employeeService.addReimbusrementService(req, resp);

	}

	public void getAllTicketsForEmployee(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		ManagerController mgrController = new ManagerController();
		EmployeeService employeeService = new EmployeeService();
		String option = mgrController.getPartOfURL(req);
		HttpSession session = req.getSession(true);
		ReimbursementUsersBean employee = (ReimbursementUsersBean) session.getAttribute("currentUser");
		int employeeID = employee.getErsUserId();
		String json = employeeService.getReimbursementsForEmployee(employeeID, option);
		resp.getWriter().println(json);

	}

}
