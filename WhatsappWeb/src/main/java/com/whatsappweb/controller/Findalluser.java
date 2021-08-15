package com.whatsappweb.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.whatsappweb.dao.WhatsappDAOInterface;
import com.whatsappweb.entity.WhatsappUser;
import com.whatsappweb.utility.ControllerFactory;


public class Findalluser extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		
		
		PrintWriter out=response.getWriter();
		
		WhatsappDAOInterface ww = ControllerFactory.createObjectHibernate();
		List<WhatsappUser> ws=ww.allUser();
		
		 String json =null;
		ObjectMapper objectMapper = new ObjectMapper();
	      try {
	        json= objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(ws);
	         System.out.println(json);
	      } catch(Exception e) {
	         e.printStackTrace();
	      }
	      
		out.println(json);
	}
	}


