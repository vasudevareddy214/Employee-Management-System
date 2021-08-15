package com.whatsappweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.whatsappweb.dao.WhatsappDAOInterface;
import com.whatsappweb.entity.WhatsappUser;
import com.whatsappweb.utility.ControllerFactory;



public class LoginSevlet extends HttpServlet {
	

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email =request.getParameter("email");
		String password =request.getParameter("password");
		
		
		WhatsappUser ic = new WhatsappUser();
		ic.setEmail(email);
		ic.setPassword(password);
		
		WhatsappDAOInterface id = ControllerFactory.createObjectHibernate();
		boolean b = id.loginProfile(ic);
		//System.out.println(b);
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<html><body><br><br>");
		out.println(ic.getEmail());
		if(b)
		{
			ServletContext sc=getServletContext();
			
			
			
			HttpSession ss=request.getSession(true);
			ss.setAttribute("em",email);
			ss.setAttribute("pwd", password);
			
			RequestDispatcher rd =getServletContext().getRequestDispatcher("/Successlogin");
			rd.forward(request, response);
			
		}
		else {
			
			out.println("<font size=5 color=red><b>Invalid id and password </b></font>");
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/Login.html");
			rd.include(request, response);
			
		}
		
		out.println("</center></body></html>");
		
		
	}
	}
	/*protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("i am post method");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("i am get method");
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("i am put method");
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("i am delete method");
	}


}*/
