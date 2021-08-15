package com.whatsappweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.whatsappweb.dao.WhatsappDAOInterface;
import com.whatsappweb.entity.WhatsappUser;
import com.whatsappweb.utility.ControllerFactory;


public class ViewProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
		HttpSession ss=request.getSession(true);
		Object oo=ss.getAttribute("em");
		System.out.println(ss.getAttribute("em"));
		WhatsappUser ic = new WhatsappUser();
		
		
		ic.setEmail(oo.toString());
		WhatsappDAOInterface id = ControllerFactory.createObjectHibernate();
		WhatsappUser ww= id.viewProfile(ic);
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<html><body><br><br>");
		if(ww!=null)
		{
			out.println(" User Name      :- "+ww.getName()+"<br>");
			
			out.println(" User Email     :- "+ww.getEmail()+"<br>");
			
			out.println(" User Address   :- "+ww.getAddress()+"<br>");
			out.println(" User Password  :- "+ww.getPassword()+"<br>");	
		}

}
}
