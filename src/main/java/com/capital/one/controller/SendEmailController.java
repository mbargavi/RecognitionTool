package com.capital.one.controller;


import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.capital.one.dao.DAOUtilities;
import com.capital.one.dao.EmployeeDao;
import com.capital.one.datamodelbeans.Employee;

@CrossOrigin(origins="*")
@Controller
public class SendEmailController {
 
    @Autowired
    private JavaMailSender mailSender;
    
    EmployeeDao empDao = DAOUtilities.getEmployeeDao();
    
    /***
     * This controller function is used to send emails following a recognition
     * @param recipCount - 3 (self, nominee, and nominee manager)
     *                     4 (self, nominee, nominee manaager, and self manaager)
     * @param subject - The subject for all the emails
     * @param message - The recognition feedback part of the message for all the emails
     * @param manMessage - If this is not "", it will be additional message content added only to the nominee manager's email
     * @param competencies - String of competencies...should be in html formal for one line, or new line and list
     * @param nomineeId
     * @param nominatorId
     * @param credits - the credit award...should be "1 Credit" or "5 Capital One Bucks" unless additional credit types added later
     * @return - success or failure as a string
     */
    @RequestMapping(value="/sendEmail", method = RequestMethod.POST)
    public @ResponseBody String buildAndSendEmail(@RequestParam("recipientCount") int recipCount, @RequestParam("subject") String subject,
    										 @RequestParam("message") String message, @RequestParam("manMessage") String manMessage, 
    										 @RequestParam("competencies") String competencies, @RequestParam("nomineeId") int nomineeId,
    										 @RequestParam("nominatorId") int nominatorId, @RequestParam("credits") String credits,
    										 @RequestParam("entityType") String entityType){
        
    		// Go to DAO and get the recipient employee list
    		// Order of Employees in List: Nominee, Nominator, NomineeManager, NominatorManager
    	    if (entityType.equals("Team")) {
    	    		recipCount = 2;
    	    		nomineeId = empDao.getTeamManagerById(nomineeId);
    	    }
    		List<Employee> recipList = empDao.getRecipientList(nomineeId, nominatorId);
    		String nomineeAddress = recipList.get(0).getEmail();
    		String nominatorAddress = recipList.get(1).getEmail();
    		String nomineeManAddress = recipList.get(2).getEmail();
    		String nominatorManAddress = recipList.get(3).getEmail();

    		String nomineeMessage = buildNomineeMessage(recipList, competencies, message, credits);
    		String nominatorMessage = buildNominatorMessage(recipList, competencies, message, manMessage, credits);
    		String nomineeManMessage = buildNomineeManagerMessage(recipList, competencies, message, manMessage, credits);
    		String nominatorManMessage = buildNominatorManagerMessage(recipList, competencies, message, credits);
    		
    		// String string = String.format("A String %s %2d", aStringVar, anIntVar);
    		
        // prints debug info
        // System.out.println("To: " + toAddress);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);
        
        if (recipCount==2) {
        		// recognition is for team; send only to team manager and recipient
        	    this.sendEmail(nominatorAddress, subject, nominatorMessage);
        	    this.sendEmail(nomineeAddress, subject, nominatorManMessage);
        	
        }else if(recipCount==3) {
        		// send to self, nominee, and nominee manager
        		this.sendEmail(nominatorAddress, subject, nominatorMessage);
        		this.sendEmail(nomineeAddress,  subject,  nomineeMessage);
        		this.sendEmail(nomineeManAddress, subject, nomineeManMessage);
        }else if(recipCount==4) {
        		// send to self, nominee, nominee manaager, and self manaager
	    		this.sendEmail(nominatorAddress, subject, nominatorMessage);
	    		this.sendEmail(nomineeAddress,  subject,  nomineeMessage);
	    		this.sendEmail(nomineeManAddress, subject, nomineeManMessage);
	    		this.sendEmail(nominatorManAddress, subject, nominatorManMessage);
        }else {
        		return "Failure";
        }
         
        return "Success";
    }
    
    private String sendEmail(String toAddress, String subject, String emailMessage) {
    	
        // creates a simple e-mail object
//      SimpleMailMessage email = new SimpleMailMessage();
//      email.setTo(toAddress);
//      email.setSubject(subject);
//      email.setText(message);
       
      // sends the e-mail
//      mailSender.send(email);
      
      // CREATE AN HTML message instead of simple message above
      mailSender.send(new MimeMessagePreparator() {
      	  public void prepare(MimeMessage mimeMessage) throws MessagingException {
      	    MimeMessageHelper email = new MimeMessageHelper(mimeMessage, true, "UTF-8");
      	    email.setFrom("recognizinggreatness@gmail.com");
      	    email.setTo(toAddress);
      	    email.setSubject(subject);
      	    email.setText(emailMessage, true); // Message should be in HTML format
      	    // message.addAttachment("CoolStuff.doc", new File("CoolStuff.doc"));
      	  }
      	});
       
      // forwards to the view named "Result"
      return "Success";
    }
    
	private String buildNomineeMessage(List<Employee> recipList, String competencies, String message, String credits) {
		String nomineeTemplate = "<h4>Dear %s:</h4>\n" + 
				"<p>\n" + 
				"We are proud of your <strong><em>exceptional performance</em></strong> and would like to take this time to pass on some encouraging feedback sent to you from <em>%s</em> using<strong>Capital One’s Recognition Feedback System.</strong> %s would like to pass on the following feedback, and has awarded you <em>%s</em>…</p>\n" + 
				"<p>\n" + 
				"<strong><span style=\"text-decoration: underline;\">Feedback:</span></strong></p>\n" + 
				"Competencies observed: %s<br>\n" + 
				"<p>\n" + 
				"Message: %s\n" + 
				"</p>\n" + 
				"<p>\n" + 
				"Keep up the great work!  Thanks from everyone at the Capital One Recognition Team!\n" + 
				"</p>";
		
		// String string = String.format("A String %s %2d", aStringVar, anIntVar);
		// Order of Employees in List: Nominee, Nominator, NomineeManager, NominatorManager
		String nominatorname = recipList.get(1).getFirstName() + " " + recipList.get(1).getLastName();
		String finalMessage = String.format(nomineeTemplate,recipList.get(0).getFirstName(), nominatorname, recipList.get(1).getFirstName(), credits, competencies, message);
		
		return finalMessage;
		
	}
	private String buildNominatorMessage(List<Employee> recipList, String competencies, String message, String manMessage, String credits) {
		String nominatorTemplate = "<p><strong>Dear %s:</strong></p>\n" + 
				"<p><em>Great job</em> in fostering encouragement at Capital One!&nbsp; Thank you for using&nbsp;the <em><strong>Capital One Recognition Program</strong></em>! People like you that recognize great work in others helps to make Capital One a better place to work!</p>\n" + 
				"<p>Sincerely,</p>\n" + 
				"<p>The Capital One Recognition Team</p>\n" + 
				"<p>Below is your nomination:</p>\n" + 
				"<p><span style=\"text-decoration: underline;\"><strong>Nominee:</strong></span> %s</p>\n" + 
				"<p><span style=\"text-decoration: underline;\"><strong>Nominee's Manager:</strong></span> %s</p>\n" + 
				"<p><span style=\"text-decoration: underline;\"><strong>Competencies observed:</strong> </span>%s</p>\n" + 
				"<p><span style=\"text-decoration: underline;\"><strong>Feedback given:</strong></span> %s</p>\n" + 
				"<p><span style=\"text-decoration: underline;\"><strong>Credits awarded:</strong></span> %s</p>";
		String optionalToMan = "You also gave the <span style=\\\"text-decoration: underline;\\\"><em>additional private message to %s's manager</em></span>: %s</p>\n";
		
		// String string = String.format("A String %s %2d", aStringVar, anIntVar);
		// Order of Employees in List: Nominee, Nominator, NomineeManager, NominatorManager
		String nomineename = recipList.get(0).getFirstName() + " " + recipList.get(0).getLastName();
		String nomineeManagername = recipList.get(2).getFirstName() + " " + recipList.get(2).getLastName();
		String finalMessage;
		if (manMessage==null) {
			finalMessage = String.format(nominatorTemplate, recipList.get(1).getFirstName(), nomineename, nomineeManagername, competencies, message, credits);
		}else {
			String optManMessage = String.format(optionalToMan,recipList.get(0).getFirstName(), manMessage);
			finalMessage = String.format(nominatorTemplate, recipList.get(1).getFirstName(), nomineename, nomineeManagername, competencies, message, credits) + optManMessage;
		}
		return finalMessage;
	}
	private String buildNomineeManagerMessage(List<Employee> recipList, String competencies, String message, String manMessage, String credits) {
		String nomineeManTemplate = "<p><strong>Dear %s:</strong></p>\n" + 
				"<p><strong><em>%s</em> </strong>would like to recognize <em><strong>%s's</strong> </em>great work!&nbsp; Below you can see the Nomination Feedback.&nbsp; %s" + 
				"<p>Sincerely,</p>\n" + 
				"<p>The Capital One Recognition Team</p>\n" + 
				"<p>Below is&nbsp;the nomination:</p>\n" + 
				"<p><span style=\"text-decoration: underline;\"><strong>Nominee:</strong></span> %s</p>\n" + 
				"<p><span style=\"text-decoration: underline;\"><strong>Nominee's Manager:</strong></span> %s</p>\n" + 
				"<p><span style=\"text-decoration: underline;\"><strong>Competencies observed:</strong> </span>%s</p>\n" + 
				"<p><span style=\"text-decoration: underline;\"><strong>Feedback given:</strong></span> %s</p>\n" + 
				"<p><span style=\"text-decoration: underline;\"><strong>Credits awarded:</strong></span> %s</p>";
		String optionalToMan = "%s also had the <span style=\\\"text-decoration: underline;\\\"><em>additional private message just for you</em></span>: %s</p>\n";
		
		// String string = String.format("A String %s %2d", aStringVar, anIntVar);
		// Order of Employees in List: Nominee, Nominator, NomineeManager, NominatorManager
		String nominatorname = recipList.get(1).getFirstName() + " " + recipList.get(1).getLastName();
		String nomineename = recipList.get(0).getFirstName() + " " + recipList.get(0).getLastName();
		String nomineeManagername = recipList.get(2).getFirstName() + " " + recipList.get(2).getLastName();
		String finalMessage;
		if (manMessage==null) {
			finalMessage = String.format(nomineeManTemplate, recipList.get(2).getFirstName(), nominatorname, nomineename, "",nomineename, nomineeManagername, competencies, message, credits );
		} else {
			String optManMessage = String.format(optionalToMan,recipList.get(1).getFirstName(), manMessage);
			finalMessage = String.format(nomineeManTemplate, nomineeManagername, nominatorname, nomineename, optManMessage,nomineename, nomineeManagername, competencies, message, credits );
		}
		return finalMessage;
	}
	private String buildNominatorManagerMessage(List<Employee> recipList, String competencies, String message, String credits) {
		String nominatorManTemplate = "<p><strong>Dear %s:</strong></p>\n" + 
				"<p><em>Great job</em> in fostering encouragers!&nbsp; <strong>%s</strong> from your team has just recognized another associate through the Capital One Recognition Program! Thanks for inspiring your team to encourage others at Capital One for great work!</p>\n" + 
				"<p>Sincerely,</p>\n" + 
				"<p>The Capital One Recognition Team</p>\n" + 
				"<p>&nbsp;</p>\n" + 
				"<p><span style=\"text-decoration: underline;\"><strong>Nominee:</strong></span> %s</p>\n" + 
				"<p><span style=\"text-decoration: underline;\"><strong>Nominee's Manager:</strong></span> %s</p>\n" + 
				"<p><span style=\"text-decoration: underline;\"><strong>Competencies observed:</strong> </span>%s</p>\n" + 
				"<p><span style=\"text-decoration: underline;\"><strong>Feedback given:</strong></span> %s</p>\n" + 
				"<p><span style=\"text-decoration: underline;\"><strong>Credits awarded:</strong></span>  %s</p>";
		
		// String string = String.format("A String %s %2d", aStringVar, anIntVar);
		// Order of Employees in List: Nominee, Nominator, NomineeManager, NominatorManager
		String nomineename = recipList.get(0).getFirstName() + " " + recipList.get(0).getLastName();
		String nomineeManagername = recipList.get(2).getFirstName() + " " + recipList.get(2).getLastName();
		String finalMessage = String.format(nominatorManTemplate,recipList.get(3).getFirstName(), recipList.get(1).getFirstName(), nomineename, nomineeManagername, competencies, message, credits);
		return finalMessage;

	}
}
