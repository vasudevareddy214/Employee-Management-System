package com.whatsappweb.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


import com.whatsappweb.entity.WhatsappUser;

public class WhatsappDAOHibernate implements WhatsappDAOInterface {
	private SessionFactory sf;

	public WhatsappDAOHibernate() {
		sf = new Configuration().configure().buildSessionFactory();
	}

	public int createProfileDAO(WhatsappUser iu) {
		int i = 0;

		Session s = sf.openSession();
		Transaction t = s.beginTransaction();

		s.save(iu);

		t.commit();
		i = 1;

		return i;
	}

	public boolean loginProfile(WhatsappUser ic) {
		boolean b=false;
		Session s=sf.openSession();
		Query q=s.createQuery("from WhatsappUser ic where ic.email='"+ic.getEmail()+"' and ic.password='"+ic.getPassword()+"'");
		List<WhatsappUser> ff=q.getResultList();
		if(ff.size()>0) {
			b=true;
		}
		return b;
	}

	public WhatsappUser viewProfile(WhatsappUser ic) {
		//System.out.println("Hello i am in hibernate");
		Session s = sf.openSession();
		Transaction t = s.beginTransaction();
		Query q = s.createQuery("from WhatsappUser ic where ic.email='" + ic.getEmail() + "'");
		WhatsappUser ww = (WhatsappUser) q.getSingleResult();
		return ww;
	}

	public List getTimeline(WhatsappUser ws) {
		// TODO Auto-generated method stub
		return null;
	}
	public int editProfile(WhatsappUser ws) {
		int i=0;
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
		s.update(ws);  //no 1 but not used in real
		//Query q=s.createQuery("update WhatsappUser ws set ws.name='"+ws.getName()+"'ws.age='"+ws.getAge()+"'ws.address='"+ws.getAddress()+"'ws.password='"+ws.getPassword()+"' where ws.email='"+ws.getEmail()+"'"); //no 2 and used in real
		//i=q.executeUpdate();
		t.commit();
		i=1;
	//	FacebookUser ff=s.load(FacebookUser.class, fu.getEmail());
		return i;
	}

	public WhatsappUser searchProfile(WhatsappUser ws) {
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
		
		Query q = s.createQuery("from WhatsappUser ws where ws.name='" + ws.getName() + "'");
		WhatsappUser ww = (WhatsappUser) q.getSingleResult();
		return ww;
	}

	public int deleteProfile(WhatsappUser ws) {
		int i=0;
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
		//s.delete(ws);
		Query q = s.createQuery( "delete from WhatsappUser ws where ws.email='" + ws.getEmail() + "' and  ws.password= '"+ws.getPassword()+"'");
		i =q.executeUpdate();
		t.commit();
	
		return i;
	}

	public List<WhatsappUser> allUser() {
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
		Query q=s.createQuery("from WhatsappUser ww"); 
		return q.getResultList();
	}

}
