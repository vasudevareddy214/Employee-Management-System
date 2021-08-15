package com.whatsappweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.SendResult;

import org.apache.log4j.Logger;

import com.whatsappweb.dao.WhatsappDAOInterface;
import com.whatsappweb.entity.WhatsappUser;
import com.whatsappweb.utility.ControllerFactory;

public class GlobalServlet extends HttpServlet {
	private Object choice;
	private static Logger log = Logger.getLogger(GlobalServlet.class);

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ss1 = request.getParameter("choice");
		if (ss1.equals("register")) {
			log.info("entering into RegisterServlet file");

			String name1 = request.getParameter("fname");
			String email1 = request.getParameter("email");
			
			String address1 = request.getParameter("address");
			String password1 = request.getParameter("password");

			WhatsappUser iu = new WhatsappUser();
			iu.setName(name1);
			iu.setPassword(password1);
			
			iu.setEmail(email1);
			iu.setAddress(address1);

			WhatsappDAOInterface id = ControllerFactory.createObject();
			WhatsappDAOInterface id1 = ControllerFactory.createObjectHibernate();

			int i = id1.createProfileDAO(iu);
			log.info("getting value of i from dao " + i);
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<html><body><center><br><br>");
			if (i > 0) {
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/log1.html");
			rd.include(request, response);
				out.println(
						"<font size=5 color=green><b>Registration success </font>");
			} else {
				out.println("<font size=5 color=red><b>Registration Fail try again</b></font>");
			}
			out.println("</center></body></html>");

		}
		if (ss1.equals("login")) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			WhatsappUser ic = new WhatsappUser();
			ic.setEmail(email);
			ic.setPassword(password);

			WhatsappDAOInterface id = ControllerFactory.createObjectHibernate();
			boolean b = id.loginProfile(ic);
			// System.out.println(b);

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<html><body><br><br>");
			out.println(ic.getEmail());
			if (b) {
				ServletContext sc = getServletContext();

				HttpSession ss = request.getSession(true);
				ss.setAttribute("em", email);
				ss.setAttribute("pwd", password);

				RequestDispatcher rd = getServletContext().getRequestDispatcher("/GlobalServlet?choice=success");
				rd.forward(request, response);

			} else {
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/log1.jsp");
				rd.include(request, response);

				out.println("<font size=5 color=red><b>Invalid id and password </b></font>");
				

			}

			out.println("</center></body></html>");

		}
		if (ss1.equals("success")) {
			ServletContext sc = getServletContext();

			HttpSession ss = request.getSession(true);
			Object oo = ss.getAttribute("em");
			System.out.println(ss.getAttribute("em"));
			Object oo1 = ss.getAttribute("pwd");

			response.setContentType("text/html");
			PrintWriter out1 = response.getWriter();
			out1.println("<html><body><center><br><br>");

			out1.println("<font size=20 color=green><b>Welcome To Our World <br></font>");
			out1.println("<font size=10 color=blue>");
			out1.println("<a href=GlobalServlet?choice=view><br> view profile</a></b><br>");
			out1.println("<a href=SearchProfileServlet>Search profile</a></b><br>");
			out1.println("<a href=GlobalServlet?choice=edit> Edit profile</a> </b><br>");
			out1.println("<a href=deleteProfileServlet> Delete Profile </a><br>");
			out1.println("<a href=GlobalServlet?choice=aboutus> About Us </a><br>");
			out1.println("<a href=Findalluser> Find all User </a><br>");
			out1.println("<a href=GlobalServlet?choice=logout> Logout Profile </a><br>");
			out1.println("</font></ol>");
			out1.println("</center></body></html>");
			
			response.sendRedirect("afterlogin.jsp");  

		}
		if (ss1.equals("delete")) {
			HttpSession ss = request.getSession(true);
			Object oo = ss.getAttribute("em");
			WhatsappUser ws = new WhatsappUser();
			WhatsappDAOInterface ww = ControllerFactory.createObjectHibernate();
			ws.setEmail(oo.toString());

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<html><body><center><br><br>");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/delete.jsp");
			rd.include(request, response);
			/*
			 * out.println("<table style='width:100px;border:3px solid black'>"); out.
			 * println("<font color=red><h2><center> Delete Profile</center></h2></font>");
			 * out.println("<form>  "); out.
			 * println("<tr><td style='border:1px solid black'>Email    : </td><td style='border:1px solid black'><input type=email name=email value="
			 * + ws.getEmail()+ " disabled></td></tr>"); out.
			 * println("<tr><td style='border:1px solid black'>Password : </td><td style='border:1px solid black'><input type=password name=password placeholder='Enter the password'></td></tr>"
			 * );
			 * 
			 * out.println("</form> </table> "); out.println("<button>Delete </button>");
			 * 
			 */
			//RequestDispatcher rd = getServletContext().getRequestDispatcher("/.jsp");
			//rd.include(request, response);
			out.println("</center></body></html>");

			String password1 = request.getParameter("password");
			System.out.println(oo.toString());

			ws.setPassword(password1);
			int i = ww.deleteProfile(ws);
			out.println("<html><body><center><br><br>");
			
			if (i > 0) {

				out.println("<font=5 color=orange><b>Profile Deleted Succesfully. We miss you.......</b></font>");
				try {
				Thread.sleep(5000);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				RequestDispatcher rd1 = getServletContext().getRequestDispatcher("/log1.html");
				//response.sendRedirect("searchprofile.jsp");  
				rd1.forward(request, response);	

			} else if (password1 != null) {
				out.println("Something went wrong, please try again");
			}

			out.println("</center></body></html>");
		}
		if (ss1.equals("edit")) {
			HttpSession ss = request.getSession(true);
			Object oo = ss.getAttribute("em");

			WhatsappUser ws = new WhatsappUser();
			ws.setEmail(oo.toString());

			WhatsappDAOInterface ws1 = ControllerFactory.createObjectHibernate();

			WhatsappUser b = ws1.viewProfile(ws);
			WhatsappUser ww = new WhatsappUser();
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			if (b != null) {
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/edit.jsp");
				rd.include(request, response);

			}

			else {
				out.println("<html><body><center><br><br>");
				out.println("<font size=5 color=red><b>Profile Not Found</font>");
			}

			out.println("</center></body></html>");
			if (b != null) {
				String name1 = request.getParameter("fname");
				// String email1 = oo.toString();
				int age1 = Integer.parseInt(request.getParameter("age"));
				String address1 = request.getParameter("address");
				String password1 = request.getParameter("password");
				ww.setName(name1);
				ww.setPassword(password1);
				
				ww.setEmail(oo.toString());
				ww.setAddress(address1);
				int i = ws1.editProfile(ww);
				response.setContentType("text/html");

				out.println("<html><body><center><br><br>");

				if (i > 0) {
					out.println("<font size=6 color=green><b>Profile Edited Successfully</font>");
				} else {
					out.println("<font size=6 color=red><b>Something gone wrong</font>");

				}

			}
			out.println("</center></body></html>");

		}
		if (ss1.equals("view")) {
			HttpSession ss = request.getSession(true);
			Object oo = ss.getAttribute("em");
			System.out.println(ss.getAttribute("em"));
			WhatsappUser ic = new WhatsappUser();

			ic.setEmail(oo.toString());
			WhatsappDAOInterface id = ControllerFactory.createObjectHibernate();
			WhatsappUser ww = id.viewProfile(ic);
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<html><body><br><br><center>");
			if (ww != null) {
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/view.jsp");
				//response.sendRedirect("searchprofile.jsp");  
				rd.include(request, response);	
				
				
				out.println("<table border=3 width=100 >");
				
				out.println("<tr><td style=' border:1px solid black' width=100> Name      :-</td><td style='border:1px solid black'> " + ww.getName() + "</td><tr>");
				out.println("<tr><td style='border:1px solid black' > Email Id     :-</td><td style='border:1px solid black'>" + ww.getEmail() + "</td><tr>");
				
				out.println("<tr><td style='border:1px solid black'> Location   :- </td><td style='border:1px solid black'>" + ww.getAddress() + "</td><tr>");
				out.println(" <tr><td style='border:1px solid black'> Password  :- </td><td style='border:1px solid black's>" + ww.getPassword() + "</td><tr>");
				
				out.println("</table>");
				out.println("</center></body></html>");
				
			}

		}
		if(ss1.equals("search"))
		{
			
			int i=1;
			WhatsappDAOInterface ww=ControllerFactory.createObjectHibernate();
			WhatsappUser ws= new WhatsappUser();
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			out.println("<html><body><center><br><br>");
				
					/*
					 * out.println("<form> "); out.
					 * println("  <font size=5 color=green><b>Enter Name to search Profile</font> <br><br>"
					 * );
					 * 
					 * out.
					 * println("Name : <input type=text name=namef placeholder='Enter the Name'><br><br>"
					 * ); out.println("<input type=submit value=Search  >");
					 * out.println("  </form>");
					 */
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/searchprofile.jsp");
			//response.sendRedirect("searchprofile.jsp");  
			rd.include(request, response);	
			
			out.println("</center></body></html>");
			out.println("<html><body><center><br><br>");
			String name1 = request.getParameter("fname");
			System.out.println(name1);
			ws.setName(name1);
			WhatsappUser w = ww.searchProfile(ws);
			
			if(w!=null) {
				out.println("<table style='border:5px solid black'><font color:white>");
				
			out.println("<tr><td style='border:1px solid white'>Name :-</td><td style='border:1px solid white'>"+w.getName()+"</td></tr>");
			
			out.println("<tr><td style='border:1px solid white'>Location :-</td><td style='border:1px solid white'>"+w.getAddress()+"</td></tr>");
			out.println("</font></table>");
			}
			
			else
			{
				out.println("<font size=5 color=red><b>Profile Not Found</font>");
				System.out.println("User not found");
			}
			System.out.println("User not found");
			out.println("</center></body></html>");
		}
		if(ss1.equals("aboutus"))
		{
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			out.println("<html><body><center><br><br>");
			out.println("<p>Hey hai everyone</p>");
			out.println("</center></body></html>");
		}
		if(ss1.equals("logout"))
		{
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/log1.html");
			rd.forward(request, response);
		}

	}
}
