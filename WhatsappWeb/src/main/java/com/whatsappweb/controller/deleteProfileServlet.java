package com.whatsappweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.whatsappweb.dao.WhatsappDAOHibernate;
import com.whatsappweb.dao.WhatsappDAOInterface;
import com.whatsappweb.entity.WhatsappUser;
import com.whatsappweb.utility.ControllerFactory;

public class deleteProfileServlet extends HttpServlet {
	

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ss=request.getSession(true);
		Object oo=ss.getAttribute("em");
		WhatsappUser ws= new WhatsappUser();
		WhatsappDAOInterface ww= ControllerFactory.createObjectHibernate();
		ws.setEmail(oo.toString());
		 
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<html><body><center><br><br>");
		out.println("<table>");
		out.println("<font color=red><th><tr><h2><center> Delete Profile</center></h2></tr></td></font>");
		out.println("<form>  ");
		out.println("<th><tr>Email    : <input type=email name=email value="+ws.getEmail()+" disabled><br><br></tr></td>");
		out.println("<th><tr>Password : <input type=password name=password placeholder='Enter the password'><br><br></tr></td>");
		out.println("<input type=submit value=Delete  >");
		out.println("</form> </table> ");
		
		out.println("</center></body></html>");
		
		String password1 =request.getParameter("password");
		System.out.println(oo.toString());
		
		ws.setPassword(password1);
		int i = ww.deleteProfile(ws);
		out.println("<html><body><center><br><br>");
		if(i>0)
		{
			
			out.println("<font=5 color=orange><b>Profile Deleted Succesfully. We miss you.......</b></font>");
			
			
		}
		else if(password1!=null)
		{
			out.println("Something went wrong, please try again");
		}
		
		
			out.println("</center></body></html>");
	}

}
