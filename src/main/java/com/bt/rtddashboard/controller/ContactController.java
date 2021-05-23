package com.bt.rtddashboard.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bt.rtddashboard.model.Contacts;
import com.bt.rtddashboard.service.ContactService;

@RestController
public class ContactController {
	@Autowired
	ContactService service;
	
	/*@RequestMapping(value="/rtd/getAllContacts", method=RequestMethod.GET)
	public ResponseEntity<List<Contacts>> getAllContacts(){
		List<Contacts> ilsList=new ArrayList<Contacts>();
		ResponseEntity<List<Contacts>> response=null;
		
		try{
			ilsList=service.getAllContacts();
			response=new ResponseEntity<List<Contacts>>(ilsList,HttpStatus.OK);
		}catch(Exception e){
			
		}
		return response;
	}*/
	
	@RequestMapping(value="/rtd/getAllContacts", method=RequestMethod.GET)
	public ResponseEntity<List<List<String>>> getAllContacts(){
		List<Contacts> ilsList=new ArrayList<Contacts>();
		ResponseEntity<List<List<String>>> response=null;
		List<List<String>> contactList=new ArrayList<List<String>>();
		try{
			ilsList=service.getAllContacts();
			for(Contacts contact:ilsList){
				List<String> obj=new ArrayList<String>();
				obj.add(contact.getName());
				obj.add(contact.getEmailId());
				obj.add(contact.getArea());
				obj.add(contact.getExtnsn());
				obj.add(contact.getTimings());
				obj.add(contact.getEscalationLead());
				contactList.add(obj);
			}
			response=new ResponseEntity<List<List<String>>>(contactList,HttpStatus.OK);
		}catch(Exception e){
			
		}
		return response;
	}
}
