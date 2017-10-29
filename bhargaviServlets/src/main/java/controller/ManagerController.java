package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.ManagerService;

public class ManagerController {
	private ManagerService allReimbursementsService = new ManagerService();

	public boolean getReimbursements(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		String requestUrl = req.getRequestURI().substring(req.getContextPath().length());
		String json = null;
		String option = getPartOfURL(req);

		if ((requestUrl).contains("/manager/deny/")) {
			String status = "deny";
			allReimbursementsService.acceptReimbursement(Integer.parseInt(option), status);
		} else {
			json = allReimbursementsService.getReimbursementsForManager(option);
		}
		resp.getWriter().println(json);
		return false;

	}

	public void approveReimbursement(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String option = getPartOfURL(req);
		String status = "approve";
		allReimbursementsService.acceptReimbursement(Integer.parseInt(option), status);

	}

	public String getPartOfURL(HttpServletRequest req) {
		String requestUrl = req.getRequestURI().substring(req.getContextPath().length());
		String[] segments = requestUrl.split("/");
		String option = segments[segments.length - 1];
		return option;

	}

}
