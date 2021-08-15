package com.whatsappweb.controller;



import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class Successlogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServletContext sc=getServletContext();
		
		
		HttpSession ss=request.getSession(true);
		Object oo=ss.getAttribute("em");
		System.out.println(ss.getAttribute("em"));
		Object oo1=ss.getAttribute("pwd");
		Cookie[] c=request.getCookies();
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<html><body><center><br><br>");
		
		out.println("<font size=5 color=blue><b>Welcome To Our World  "+oo+" and password is "+oo1+"<a href=ViewProfileServlet><br> view profile</a><br>   <a href=SearchProfileServlet>Search profile</a><br><a href=EditProfileServlet> Edit profile</a> </b><br><a href=TimelineServlet> TimeLine</a> </b></font>");
		out.println("<font size=5 color=red><a href=deleteProfileServlet><br> Delete Profile </a><br>");
		out.println("<font size=5 color=red><a href=Findalluser><br> Find all User </a><br>");
		for(int i=0;i<c.length;i++) {
			Cookie c1=c[i];
			out.println("<br><br><br>cookie name "+c1.getName());
			out.println("cookie value"+c1.getValue());
		out.println("</center></body></html>");
		
		
		
		
		
		
	}

}
}