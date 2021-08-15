package com.whatsappweb.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.whatsappweb.dao.WhatsappDAOInterface;
import com.whatsappweb.entity.Timeline;
import com.whatsappweb.entity.WhatsappUser;
import com.whatsappweb.utility.ControllerFactory;


public class TimelineServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ss= request.getSession(true);
		Object oo = ss.getAttribute("em");
		WhatsappUser ws= new WhatsappUser();
	    ws.setEmail(oo.toString());
	    
	    WhatsappDAOInterface wd = ControllerFactory.createObject();
	    List ll = wd.getTimeline(ws);
	    PrintWriter out =response.getWriter();
	    for(Object oo1:ll)
	    {
	    	if(oo1 instanceof WhatsappUser)
	    	{
	    		WhatsappUser ww = (WhatsappUser)oo1;
	    		out.println("Message For "+ww.getEmail());
	    		out.println(ww.getAddress());
	    	}
	    	if(oo1 instanceof Timeline) {
	    		Timeline ww =(Timeline)oo1;
	    		out.println("Sender is "+ww.getSender());
	    		out.println(ww.getMassage());
	    	}
	    }
	}

}
