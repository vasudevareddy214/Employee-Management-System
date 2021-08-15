package com.whatsappweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.whatsappweb.dao.WhatsappDAOInterface;
import com.whatsappweb.entity.WhatsappUser;
import com.whatsappweb.utility.ControllerFactory;




public class RegistrationServlet extends HttpServlet {
	 private static Logger log = Logger.getLogger(RegistrationServlet.class);
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  log.info("entering into RegisterServlet file");

		String name1 = request.getParameter("fname");
		String email1 = request.getParameter("email");
		
		String address1 = request.getParameter("address");
		String password1 =request.getParameter("password");
		
		
		WhatsappUser iu =new WhatsappUser();
		iu.setName(name1);
		iu.setPassword(password1);
	
		iu.setEmail(email1);
		iu.setAddress(address1);
		
		WhatsappDAOInterface id=ControllerFactory.createObject();
		WhatsappDAOInterface id1=ControllerFactory.createObjectHibernate();
		
		int i = id1.createProfileDAO(iu);
		log.info("getting value of i from dao "+i);
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<html><body><center><br><br>");
		if(i>0) {
			out.println("<font size=5 color=green><b>Registration success <a href=Login.html>click here</a> to continue</b></font>");
		}
		else {
			out.println("<font size=5 color=red><b>Registration Fail try again</b></font>");
		}
		out.println("</center></body></html>");
	}

}
