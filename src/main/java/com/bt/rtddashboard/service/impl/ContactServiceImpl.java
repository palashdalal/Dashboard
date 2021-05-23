/**
 * 
 */
package com.bt.rtddashboard.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bt.rtddashboard.dao.ContactDao;
import com.bt.rtddashboard.model.Contacts;
import com.bt.rtddashboard.service.ContactService;

/**
 * @author 609669080
 *
 */
@Service
@Transactional(readOnly = false)
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactDao dataAccess;
	
	@Override
	public List<Contacts> getAllContacts() {
		// TODO Auto-generated method stub
		return dataAccess.getAllContacts();
	}

}
