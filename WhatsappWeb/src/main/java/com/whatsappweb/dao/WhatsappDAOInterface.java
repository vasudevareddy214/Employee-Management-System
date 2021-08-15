package com.whatsappweb.dao;

import java.util.List;

import com.whatsappweb.entity.WhatsappUser;

public interface WhatsappDAOInterface {

	int createProfileDAO(WhatsappUser iu);

	boolean loginProfile(WhatsappUser ic);

	WhatsappUser viewProfile(WhatsappUser ic);

	List getTimeline(WhatsappUser ws);

	int editProfile(WhatsappUser ws);

	WhatsappUser searchProfile(WhatsappUser ws);

	int deleteProfile(WhatsappUser ws);

	List<WhatsappUser> allUser();

}
