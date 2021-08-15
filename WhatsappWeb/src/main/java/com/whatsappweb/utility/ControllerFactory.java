package com.whatsappweb.utility;

import com.whatsappweb.dao.WhatsappDAO;
import com.whatsappweb.dao.WhatsappDAOHibernate;
import com.whatsappweb.dao.WhatsappDAOInterface;

public class ControllerFactory {

	public static WhatsappDAOInterface createObject() {
		// TODO Auto-generated method stub
		return new WhatsappDAO();
	}

	public static WhatsappDAOInterface createObjectHibernate() {
		// TODO Auto-generated method stub
		return new WhatsappDAOHibernate();
	}

}
