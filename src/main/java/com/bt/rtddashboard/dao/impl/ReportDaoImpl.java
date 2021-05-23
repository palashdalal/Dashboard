/**
 * 
 */
package com.bt.rtddashboard.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bt.rtddashboard.dao.ReportDao;
import com.bt.rtddashboard.model.ServerStatsHistory;
import com.bt.rtddashboard.utility.DateConversionUtility;

/**
 * @author 609669080
 *
 */
@Repository
public class ReportDaoImpl implements ReportDao{
	
	@Autowired
	SessionFactory sessionFactory;
	

	public static final String FORMAT1="dd/MM/yyyy";
	public static final String FORMAT2="dd-MMM-yy";
	
	@SuppressWarnings("unchecked")
	public List<ServerStatsHistory> getAllStatsHistory(){
		List<ServerStatsHistory> serverStatList=new ArrayList<ServerStatsHistory>();
		Session session=null;
	try{
		session=sessionFactory.openSession();
		Query query=session.createQuery("From ServerStatsHistory");
		serverStatList=(List<ServerStatsHistory>)query.list();
	}
	catch(Exception e){
		e.printStackTrace();
		//throw new Exception("Failed to Fetch all Inline Services ",e);
	}finally{
		session.close();
	}
		return serverStatList;
	}

	@Override
	public List<ServerStatsHistory> getServerStatsByServer(Integer serverId) {
		List<ServerStatsHistory> serverStatList=new ArrayList<ServerStatsHistory>();
		Session session=null;
	try{
		session=sessionFactory.openSession();
		Query query=session.createQuery("From ServerStatsHistory where serverId=:serverId");
		query.setParameter("serverId", serverId);
		serverStatList=(List<ServerStatsHistory>)query.list();
	}
	catch(Exception e){
		e.printStackTrace();
		//throw new Exception("Failed to Fetch all Inline Services ",e);
	}finally{
		session.close();
	}
		return serverStatList;
	}

	@Override
	public List<ServerStatsHistory> getServerStatsByDate(Integer serverId, String startDate, String endDate) {
		// TODO Auto-generated method stub
		Session session=null;
		List<ServerStatsHistory> serverStatList=new ArrayList<ServerStatsHistory>();
	try{
		session=sessionFactory.openSession();
		if(startDate!=null && endDate!=null){
			endDate=DateConversionUtility.incrementDateBy1(endDate,FORMAT1);
			startDate=DateConversionUtility.convertDateToAnotherFormat(startDate, FORMAT1, FORMAT2);
			endDate=DateConversionUtility.convertDateToAnotherFormat(endDate, FORMAT1, FORMAT2);
			System.out.println("Start DAte" + startDate);
			System.out.println("End DAte" + endDate);
			Query query=session.createQuery("From ServerStatsHistory where serverId=:serverId and refreshTime >=:startDate and refreshTime<=:endDate order by refreshTime");
			query.setParameter("serverId", serverId);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
			serverStatList = (List<ServerStatsHistory>)query.list();
			if(serverStatList==null || serverStatList.size()<=0){
				throw new NullPointerException("No Data Available for entered Date");
			}
		}else{
			throw new NumberFormatException("Dates are not in proper format");
		}
	}
	catch(Exception e){
		e.printStackTrace();
		return serverStatList;
		//throw new Exception("Failed to Fetch all Inline Services ",e);
	}finally{
		session.close();
	}
	return serverStatList;
	}

}
