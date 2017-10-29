package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import beans.ReimbursementBean;
import beans.ReimbursementUsersBean;
import impl.EmployeeReimbursementImpl;

public class EmployeeService {
	private EmployeeReimbursementImpl reimbursementImpl= new EmployeeReimbursementImpl();
	public void addReimbusrementService(HttpServletRequest req,HttpServletResponse resp) throws IOException
	{
		
		HttpSession session = req.getSession(true);
		ReimbursementUsersBean employee = (ReimbursementUsersBean) session.getAttribute("currentUser");
		ReimbursementBean reimb= new ReimbursementBean();
		reimb.setReimbAmount(Integer.parseInt(req.getParameter("amount")));
	    reimb.setReimbDescription(req.getParameter("message"));
	    reimb.setReimbDescription(req.getParameter("message"));
	    reimb.setReimbAuthor(employee.getErsUserId());
	    reimbursementImpl.addReimbursement(reimb);
		resp.sendRedirect("/ReimbursementTool/static/addSuccess.html");
	
	}

	public String getReimbursementsForEmployee(int employeeID, String option) {
		String json = null;
		List<ReimbursementBean> reimbList = reimbursementImpl.getReimbursement(employeeID, option);
		ObjectMapper om = new ObjectMapper();
		ObjectWriter ow = om.writer().withDefaultPrettyPrinter();
		try {
			json = ow.writeValueAsString(reimbList);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;

	}

}
