package com.whatsappweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.whatsappweb.dao.WhatsappDAOInterface;
import com.whatsappweb.entity.WhatsappUser;
import com.whatsappweb.utility.ControllerFactory;


public class SearchProfileServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int i=1;
		WhatsappDAOInterface ww=ControllerFactory.createObjectHibernate();
		WhatsappUser ws= new WhatsappUser();
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<html><body><center><br><br>");
			
				
		out.println("  <font size=5 color=green><b>Enter Name to search Profile</font> <br><br>");
		out.println("<form>  ");
		out.println("Name : <input type=text name=namef placeholder='Enter the Name'><br><br>");
		out.println("<input type=submit value=Search  >");
		out.println("</form>  ");
			
		out.println("</center></body></html>");
	
	
		String name1 = request.getParameter("namef");
		System.out.println(name1);
		ws.setName(name1);
		WhatsappUser w = ww.searchProfile(ws);
		
		out.println("<html><body><center><br><br>");
		if(w!=null)
		{
		out.println("Name Of User:-"+w.getName()+"<br>");
		
		out.println("Address Of User:-"+w.getAddress()+"<br>");
		
		}
		   
		else
		{
			
			out.println("<b>User not found</b>");
		}
		
		out.println("</center></body></html>");
		
	}

}
