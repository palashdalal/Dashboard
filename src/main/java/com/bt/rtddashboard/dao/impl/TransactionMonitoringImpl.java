/**
 * 
 */
package com.bt.rtddashboard.dao.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bt.rtddashboard.dao.TransactionMonitoringDao;
import com.bt.rtddashboard.model.ServerStatsHistory;
import com.bt.rtddashboard.model.TransactionMonitoringVo;
import com.bt.rtddashboard.utility.DateConversionUtility;

/**
 * @author 609669080
 *
 */
@Repository
public class TransactionMonitoringImpl implements TransactionMonitoringDao{

	@Autowired
	SessionFactory sessionFactory;

	public static final String FORMAT1="dd/MM/yyyy";
	public static final String FORMAT2="dd-MMM-yyyy";
	
	@Override
	public List<TransactionMonitoringVo> getTransactionSessions(String startDate, String enddate, String customerId,
			String billingId) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TransactionMonitoringVo> getTransactionListByDate(String startDate, String endDate) {
		// TODO Auto-generated method stub
		List<TransactionMonitoringVo> transactionList=new ArrayList<TransactionMonitoringVo>();
		Session session=null;
	try{
		session=sessionFactory.openSession();
	//	Query query;
		endDate=DateConversionUtility.incrementDateBy1(endDate,FORMAT1);
		startDate=DateConversionUtility.convertDateToAnotherFormat(startDate, FORMAT1, FORMAT2);
		endDate=DateConversionUtility.convertDateToAnotherFormat(endDate, FORMAT1, FORMAT2);
		Query query=session.createQuery("From TransactionMonitoringVo where date>=:startDate and date<=:endDate");
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		System.out.println(endDate);
		transactionList=(List<TransactionMonitoringVo>)query.list();
	}
	catch(Exception e){
		e.printStackTrace();
		//throw new Exception("Failed to Fetch all Inline Services ",e);
	}finally{
		session.close();
	}
		return transactionList;
	}

	@Override
	public List<TransactionMonitoringVo> getTransactionMonitoringSessionsByDateAndID(String startDate, String endDate,
			String paramId, String paramValue) {
		// TODO Auto-generated method stub
		List<TransactionMonitoringVo> transactionList=new ArrayList<TransactionMonitoringVo>();
		Session session=null;
	try{
		session=sessionFactory.openSession();
	//	Query query;
		endDate=DateConversionUtility.incrementDateBy1(endDate,FORMAT1);
		startDate=DateConversionUtility.convertDateToAnotherFormat(startDate, FORMAT1, FORMAT2);
		endDate=DateConversionUtility.convertDateToAnotherFormat(endDate, FORMAT1, FORMAT2);
		Query query;
		if(paramId.equals("sessionId")){
			query=session.createQuery("From TransactionMonitoringVo where date>=:startDate and date<=:endDate and sessionId=:sessionId");
			query.setParameter("sessionId", paramValue);
		}else{
			query=session.createQuery("From TransactionMonitoringVo where date>=:startDate and date<=:endDate  and customerId=:customerId");
			query.setParameter("customerId", paramValue);
		}
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		System.out.println(endDate);
		transactionList=(List<TransactionMonitoringVo>)query.list();
	}
	catch(Exception e){
		e.printStackTrace();
		//throw new Exception("Failed to Fetch all Inline Services ",e);
	}finally{
		session.close();
	}
		return transactionList;
	}
}
