package com.capital.one.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.RootLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins="*")
@Controller
public class ImageController implements ServletContextAware {
	
	@Autowired
	static ServletContext context;
	
	Logger log = RootLogger.getLogger("ImageController");
	
	@RequestMapping(value="/uploadImage", method = RequestMethod.POST)
	public @ResponseBody String uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("empId") String empId) {
		
		String pathImage = uploadFile(file, empId);
		
		if (pathImage != null) {
			// TODO: CALL SERVICE OR DAO LAYER TO STORE PATH TO IMAGE WITH EMPID IN DATABASE
			return pathImage;
		}else {
			return "NoImage";
		}
		
		//return "path-to-image-on-server-to-put-in-src-tag-in-html";
	}
	
	
	@RequestMapping(value="/retrieveImage/{empId}", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
	public @ResponseBody Resource retrieveImage(@PathVariable("empId") int empId) {
		
		// TODO: NEED TO CALL SERVICE OR DAO LAYER TO QUERY TABLE AND GET ABSOLUTE PATH TO EMPID PROFILE IMAGE
		// pathImage = dao.[lookupandreturn];
		
		// CREATING THE PATH TO RETRIEVE THE PIC
		String rootPath = context.getRealPath("");
		String profilepicsPath = null;
		if (rootPath.endsWith(File.separator)) {
			profilepicsPath = "WEB-INF" + File.separator + "images" + File.separator + "profilepics";
		} else {
			profilepicsPath = File.separator + "WEB-INF" + File.separator + "images" + File.separator + "profilepics";
		}
		System.out.println("profilepicsPath = " + profilepicsPath);
		
		// CREATING THE FILE NAME FOR ACCESSING THE FILE TO SEND BACK TO CLIENT
		String name = (empId + "_Profile.png");  //******Defaulting to PNG UNTIL WE GET THIS FROM THE DATABASE
		
		File serverPathToFile = new File(profilepicsPath + File.separator + name);
		log.debug("The serverPathToFile = " + profilepicsPath + File.separator + name);
		
		return new ServletContextResource(context, profilepicsPath + File.separator + name);
		
	    
	}
	
	private String uploadFile(MultipartFile file, String empId) {
		
		if (!file.isEmpty()) {
			try {
				// STORING THE IMAGE IN BYTES IN A VARIABLE
				byte[] bytes = file.getBytes();
				
				// CREATING THE PATH TO STORE THE PICS
				String rootPath = context.getRealPath("");
				String profilepicsPath = null;
				if (rootPath.endsWith(File.separator)) {
					profilepicsPath = context.getRealPath("") +
							"WEB-INF" + File.separator + "images" + File.separator + "profilepics";
				} else {
					profilepicsPath = context.getRealPath("") +
							File.separator + "WEB-INF" + File.separator + "images" + File.separator + "profilepics";
				}
				System.out.println("profilepicsPath = " + profilepicsPath);
				
				// CREATING THE DIRECTORY IF NOT THERE
				File dir = new File(profilepicsPath); //place to store the profile images
				if (! dir.exists()) {
					dir.mkdirs();
				}
				// NAMING THE FILE AND GETTING THE SERVER PATH TO FILE TO PASS BACK TO CLIENT
				String name = (empId + "_Profile" + file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".")));
				System.out.println("New name of profile image for EmpId " + empId + " is " + name);
				File serverPathToFile = new File(dir.getAbsolutePath() + File.separator + name);
				
				//WRITING THE BYTES TO THE NEW FILE ON THE SERVER
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverPathToFile));
				stream.write(bytes);
				stream.close();
				
				//RETURN THE SERVER PATH - (example I copied just returned the new file name but I want to save a step and display w/o another call)
				return ("http://localhost:8080/RecognitionTool/retrieveImage/" + empId);
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else {
			// Do nothing
		}
		return null;
		// return "path-to-image-on-server-to-put-in-src-tag-in-html";
	}
	
	

	@Override
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		ImageController.context = servletContext;
		
	}

}
