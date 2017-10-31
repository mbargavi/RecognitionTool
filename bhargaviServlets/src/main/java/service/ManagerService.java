package service;

import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import beans.ReimbursementBean;
import impl.EmployeeReimbursementImpl;

public class ManagerService {
	EmployeeReimbursementImpl reimbursementImpl = new EmployeeReimbursementImpl();

	public String getReimbursementsForManager(String option) {
		String json = null;
		List<ReimbursementBean> reimbList = reimbursementImpl.getReimbursementsForManager(option);
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

	public void acceptReimbursement(int reimbID, String status) {

		reimbursementImpl.processReimbursement(reimbID, status);

	}

}
