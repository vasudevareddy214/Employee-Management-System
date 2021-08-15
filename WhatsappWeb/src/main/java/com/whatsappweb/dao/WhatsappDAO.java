package com.whatsappweb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.whatsappweb.entity.Timeline;
import com.whatsappweb.entity.WhatsappUser;

public class WhatsappDAO implements WhatsappDAOInterface {
	private Connection con;
	
	public WhatsappDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webpage", "root", "ADShivankar0099@");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public int createProfileDAO(WhatsappUser iu) {
		

		
			int i = 0;
			try {

				PreparedStatement ps = con.prepareStatement("insert into whatsappuser values(?,?,?,?,?)");
				ps.setString(1, iu.getName());
				ps.setString(2, iu.getEmail());
			
				ps.setString(4, iu.getAddress());
				ps.setString(5, iu.getPassword());


				i = ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return i;

	}

	public boolean loginProfile(WhatsappUser ic) {
		boolean b = false;
		try {
			PreparedStatement ps = con.prepareStatement("select * FROM whatsappuser where email=? and password=?");
			ps.setString(1, ic.getEmail());
			ps.setString(2, ic.getPassword());
			ResultSet res = ps.executeQuery();
			boolean r=res.next();
			if(r) {
			
				b=true;
			
			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return b;
	}

	public WhatsappUser viewProfile(WhatsappUser ic) {
		WhatsappUser ww=null;
		try {
				PreparedStatement ps = con.prepareStatement("select * FROM whatsappuser where email=? ");
		ps.setString(1, ic.getEmail());
		
		ResultSet res = ps.executeQuery();
		 
		if(res.next()) {
		  ww = new WhatsappUser();
		  ww.setName(res.getString(1));
		  ww.setEmail(res.getString(2));
		  
		  ww.setAddress(res.getString(4));
		  ww.setPassword(res.getString(5));
		  
			
		
		}	
	}
	catch(Exception e)
	{
		
	e.printStackTrace();
	
		
	}
	return ww;
	}

	public List getTimeline(WhatsappUser ws) {
		List ll = new ArrayList();
		try {
		PreparedStatement ps = con.prepareStatement("select t.senderid,t.tmassage,t.receiverid ,w.name,w.address from whatsappuser w , timeline t where w.email=t.email and receiverid =?  ");
		ps.setString(1, ws.getEmail());
		ResultSet res = ps.executeQuery();
		while(res.next())
		{
			Timeline t= new Timeline();
			t.setSender(res.getString(1));
			t.setMassage(res.getString(2));
			WhatsappUser ww= new WhatsappUser();
			ww.setEmail(res.getString(3));
			ww.setName(res.getString(4));
			ww.setAddress(res.getString(5));
			ll.add(t);
			ll.add(ww);
			
		}
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ll;
	}

	public int editProfile(WhatsappUser ws) {
		// TODO Auto-generated method stub
		return 0;
	}

	public WhatsappUser searchProfile(WhatsappUser ws) {
		// TODO Auto-generated method stub
		return null;
	}

	public int deleteProfile(WhatsappUser ws) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<WhatsappUser> allUser() {
		// TODO Auto-generated method stub
		return null;
	}
}


