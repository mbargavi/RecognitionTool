package com.capital.one.servlets;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capital.one.beans.Account;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FirstServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		System.out.println("initialized");
	}

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("serviced");
		super.service(arg0, arg1);
		System.out.println(arg0.getMethod());
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("destoyed");
		super.destroy();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("we did a get request");
		Enumeration<String> headers = req.getHeaderNames();
		while (headers.hasMoreElements()) {
			String header = headers.nextElement();
			System.out.println(header + " " + req.getHeader(header));
		}
		// System.out.println(req.getMethod());
		// Enumeration<String> headers = req.getHeaderNames();
		// while(headers.hasMoreElements())
		// System.out.println(headers.nextElement());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("posted");
		
		// get a single string from the body of the request
		String json = req.getReader().lines().reduce((acc,line) -> {
			return acc+=line + "\n";
		}).get();
		System.out.println("json: " + json);

		ObjectMapper mapper = new ObjectMapper();
		Account acc = mapper.readValue(json, Account.class);
		System.out.println(acc);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doDelete(req, resp);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPut(req, resp);
	}

}
