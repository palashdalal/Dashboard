package com.bt.rtddashboard.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bt.rtddashboard.model.TransactionMonitoringVo;
import com.bt.rtddashboard.service.TransactionMonitoringService;


@RestController
public class TransactionMonitoringController {
	@Autowired
	TransactionMonitoringService service;
	/*@RequestMapping(value="/rtd/getAllContacts", method=RequestMethod.GET)
	public ResponseEntity<List<Contacts>> getAllContacts(){
		List<Contacts> ilsList=new ArrayList<Contacts>();
		ResponseEntity<List<Contacts>> response=null;
		
		try{
			ilsList=service2.getAllContacts();
			response=new ResponseEntity<List<Contacts>>(ilsList,HttpStatus.OK);
		}catch(Exception e){
			
		}
		return response;
	}*/
	
	@RequestMapping(value="/rtd/getTransactionMonitoring", method=RequestMethod.GET)
	public ResponseEntity<List<List<String>>> getTransactionMonitoringSessions(@QueryParam("startDate")String startDate,@QueryParam("endDate")String endDate,@QueryParam("customerId")String customerId,@QueryParam("accountId")String accountId){
		List<TransactionMonitoringVo> transactionList=new ArrayList<TransactionMonitoringVo>();
		ResponseEntity<List<List<String>>> response=null;
		List<List<String>> transactionMonitList=new ArrayList<List<String>>();
//		Gson g=new Gson();
		try{
			transactionList=service.getTransactionList(startDate, endDate, customerId, accountId);
			if(transactionList!=null && !transactionList.isEmpty())
			{
				for(TransactionMonitoringVo contact:transactionList){
					
					List<String> obj=new ArrayList<String>();
					obj.add(contact.getSessionId());
					obj.add(contact.getCustomerId());
					obj.add(contact.getAccountId());
					obj.add(contact.getRequest());
					//obj.add(contact.getCustomerDetailsDBEndTime());
					//obj.add(contact.getCustomerDetailsDBStartTime());
					
					//obj.add(contact.getRegradeMatrixEndTime());
					//obj.add(contact.getRegradeMatrixStartTime());
					
					
					obj.add(contact.getRequestStartTime());
					obj.add(contact.getRequestEndTime());
					/*obj.add(contact.getResponse());
					obj.add(contact.getSessionCleanUpTime());
					obj.add(contact.getSessionId());
					obj.add(contact.getDate().toString());*/
					//obj.add(contact.getDateInsert().toString());
					transactionMonitList.add(obj);
				}
			}
			System.out.println(transactionList);
		}catch(Exception e){
			e.printStackTrace();
		}
		response=new ResponseEntity<List<List<String>>>(transactionMonitList,HttpStatus.OK);
		return response;
	}
	
	/*@RequestMapping(value="/rtd/getTransactionMonitoringByDate", method=RequestMethod.GET)
	public ResponseEntity<List<List<String>>> getTransactionMonitoringSessionsByDate(@QueryParam("startDate")String startDate,@QueryParam("endDate")String endDate){//,@QueryParam("customerId")String customerId,@QueryParam("accountId")String accountId){
		System.out.println("Inside Transaction Controller");
		List<TransactionMonitoringVo> transactionList=new ArrayList<TransactionMonitoringVo>();
		ResponseEntity<List<List<String>>> response=null;
		List<List<String>> transactionMonitList=new ArrayList<List<String>>();
//		Gson g=new Gson();
		try{
			transactionList=service.getTransactionListByDate(startDate, endDate);
			if(transactionList!=null && !transactionList.isEmpty())
			{
				for(TransactionMonitoringVo contact:transactionList){
					List<String> obj=new ArrayList<String>();
					obj.add(contact.getAccountId());
					obj.add(contact.getAdvisorName());
					//obj.add(contact.getCustomerDetailsDBEndTime());
					//obj.add(contact.getCustomerDetailsDBStartTime());
					obj.add(contact.getCustomerId());
					//obj.add(contact.getRegradeMatrixEndTime());
					//obj.add(contact.getRegradeMatrixStartTime());
					obj.add(contact.getRequest());
					obj.add(contact.getRequestEndTime());
					obj.add(contact.getRequestStartTime());
					obj.add(contact.getResponse());
					obj.add(contact.getSessionCleanUpTime());
					obj.add(contact.getSessionId());
					obj.add(contact.getDate().toString());
					//obj.add(contact.getDateInsert().toString());
					transactionMonitList.add(obj);
				}
			}
			System.out.println(transactionList);
		}catch(Exception e){
			e.printStackTrace();
		}
		response=new ResponseEntity<List<List<String>>>(transactionMonitList,HttpStatus.OK);
		return response;
	}*/
	
	@RequestMapping(value="/rtd/getTransactionMonitoringByDate", method=RequestMethod.GET)
	public ResponseEntity<List<TransactionMonitoringVo>> getTransactionMonitoringSessionsByDate(@QueryParam("startDate")String startDate,@QueryParam("endDate")String endDate){//,@QueryParam("customerId")String customerId,@QueryParam("accountId")String accountId){
		System.out.println("Inside Transaction Controller");
		List<TransactionMonitoringVo> transactionList=new ArrayList<TransactionMonitoringVo>();
		ResponseEntity<List<TransactionMonitoringVo>> response=null;
		//List<List<String>> transactionMonitList=new ArrayList<List<String>>();
		transactionList=service.getTransactionListByDate(startDate, endDate);
		response=new ResponseEntity<List<TransactionMonitoringVo>>(transactionList,HttpStatus.OK);
		return response;
	}
	
	@RequestMapping(value="/rtd/getTransactionMonitoringByDate/{paramId}/{paramValue}", method=RequestMethod.GET)
	public ResponseEntity<List<TransactionMonitoringVo>> getTransactionMonitoringSessionsByDateAndID(@PathVariable(value="paramId")String paramId,@PathVariable(value="paramValue")String paramValue, @QueryParam("startDate")String startDate,@QueryParam("endDate")String endDate){//,@QueryParam("customerId")String customerId,@QueryParam("accountId")String accountId){
		System.out.println("Inside Transaction Controller");
		List<TransactionMonitoringVo> transactionList=new ArrayList<TransactionMonitoringVo>();
		ResponseEntity<List<TransactionMonitoringVo>> response=null;
		//List<List<String>> transactionMonitList=new ArrayList<List<String>>();
		transactionList=service.getTransactionMonitoringSessionsByDateAndID(startDate, endDate,paramId,paramValue);
		response=new ResponseEntity<List<TransactionMonitoringVo>>(transactionList,HttpStatus.OK);
		return response;
	}
	/*public static void main(String[] args) {
		TransactionMonitoringController tc=new TransactionMonitoringController();
		tc.getTransactionMonitoringSessions("","", "sacasvzfccsdvdsv","");
	}*/
}
