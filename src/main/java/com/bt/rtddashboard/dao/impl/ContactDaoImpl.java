package com.bt.rtddashboard.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bt.rtddashboard.dao.ContactDao;
import com.bt.rtddashboard.model.Contacts;

@Repository
public class ContactDaoImpl implements ContactDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Contacts> getAllContacts(){
		List<Contacts> contactList=new ArrayList<Contacts>();
		Session session=null;
	try{
		session=sessionFactory.openSession();
		Query query=session.createQuery("From Contacts");
		contactList=(List<Contacts>)query.list();
	}
	catch(Exception e){
		e.printStackTrace();
		//throw new Exception("Failed to Fetch all Inline Services ",e);
	}finally{
		session.close();
	}
		return contactList;
	}
}
