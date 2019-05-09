package com.revature.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlets.DefaultServlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.RequestDao;
import com.revature.services.LoginService;

public class ViewReimbServlet extends DefaultServlet{
	RequestDao requestDao = new RequestDao();

	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.addHeader("Access-Control-Allow-Headers", "content-type");
		response.addHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		super.service(request, response);
	}
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		ObjectMapper om = new ObjectMapper();
		
		ArrayList reimbList = requestDao.viewRequestsByUserId(LoginService.userId);

		om.writeValue(response.getOutputStream(), reimbList);
		
		
	}
}
