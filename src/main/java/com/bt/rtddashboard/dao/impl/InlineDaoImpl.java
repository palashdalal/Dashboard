/**
 * 
 */
package com.bt.rtddashboard.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bt.rtddashboard.dao.InlineDao;
import com.bt.rtddashboard.model.Alert;
import com.bt.rtddashboard.model.ILSModel;
import com.bt.rtddashboard.model.IlsStats;
import com.bt.rtddashboard.model.IlsStatsHistory;
import com.bt.rtddashboard.model.MaxRecomStats;
import com.bt.rtddashboard.model.RecommendationStats;
import com.bt.rtddashboard.model.ServerMonitor;
import com.bt.rtddashboard.model.ServerStats;
import com.bt.rtddashboard.model.ServerStatsHistory;
import com.bt.rtddashboard.utility.DateConversionUtility;


/**
 * @author 609669080
 *
 */
@Repository
public class InlineDaoImpl implements InlineDao{
	
	@Autowired
	SessionFactory sessionFactory;
	
	public static final String FORMAT1="dd/MM/yyyy";
	public static final String FORMAT2="dd-MMM-yy";
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ILSModel> getAllInlineService() {
		Session session=null;
		List<ILSModel> ilsList=new ArrayList<ILSModel>();
	try{
		session=sessionFactory.openSession();
		Query query=session.createQuery("From ILSModel");
		ilsList=(List<ILSModel>)query.list();
	}
	catch(Exception e){
		e.printStackTrace();
		//throw new Exception("Failed to Fetch all Inline Services ",e);
	}finally{
		session.close();
	}
	return ilsList;
	}

	@Override
	public ServerMonitor getAllInlineByServer(Integer serverId) {
		// TODO Auto-generated method stub
		Session session=null;
		System.out.println("Inside serverMonitor");
		ServerMonitor serverMonitor=new ServerMonitor();
	try{
		session=sessionFactory.openSession();
		
		serverMonitor=(ServerMonitor)session.get(ServerMonitor.class, serverId);
	}
	catch(Exception e){
		e.printStackTrace();
		//throw new Exception("Failed to Fetch all Inline Services ",e);
	}finally{
		session.close();
	}
	return serverMonitor;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ServerMonitor> getAllServers() {
		Session session=null;
		List<ServerMonitor> serverList=new ArrayList<ServerMonitor>();
		System.out.println("Inside servers");
	try{
		session=sessionFactory.openSession();
		Query query=session.createQuery("From ServerMonitor");
		serverList=(List<ServerMonitor>)query.list();
	}
	catch(Exception e){
		e.printStackTrace();
		//throw new Exception("Failed to Fetch all Inline Services ",e);
	}finally{
		session.close();
	}
	return serverList;
	}

	@Override
	public ServerStats getServerStats(Integer serverId) {
		Session session=null;
		System.out.println("Inside serverMonitor");
		ServerStats serverMonitor=new ServerStats();
	try{
		session=sessionFactory.openSession();
		
		serverMonitor=(ServerStats)session.get(ServerStats.class, serverId);
	}
	catch(Exception e){
		e.printStackTrace();
		//throw new Exception("Failed to Fetch all Inline Services ",e);
	}finally{
		session.close();
	}
	return serverMonitor;
	}

	@Override
	public List<IlsStats> getInlineStatusByServer(Integer serverId) {
		Session session=null;
		List<IlsStats> ilsList=new ArrayList<IlsStats>();
		System.out.println("Inside servers");
	try{
		session=sessionFactory.openSession();
		Query query=session.createQuery("From IlsStats where serverId=:serverId");
		query.setParameter("serverId", serverId);
		ilsList=(List<IlsStats>)query.list();
	}
	catch(Exception e){
		e.printStackTrace();
		//throw new Exception("Failed to Fetch all Inline Services ",e);
	}finally{
		session.close();
	}
	return ilsList;
	}

	@Override
	public List<IlsStats> getAllInlineStats() {
		Session session=null;
		List<IlsStats> ilsList=new ArrayList<IlsStats>();
		System.out.println("Inside Service servers");
	try{
		session=sessionFactory.openSession();
		Query query=session.createQuery("From IlsStats");
		ilsList=(List<IlsStats>)query.list();
		if(ilsList==null && ilsList.size()<=0){
			throw new NullPointerException("No data present in inline Stats table" + new Date());
		}
	}
	catch(Exception e){
		e.printStackTrace();
		return null;
	}finally{
		session.close();
	}
	return ilsList;
	}

	@Override
	public int insertInIlsStatsHistory(List<IlsStatsHistory> ilsStatsHisList) {
		Session session=null;
		Transaction transaction=null;
		System.out.println("Inside servers");
	try{
		session=sessionFactory.openSession();
		transaction=session.beginTransaction();
		int count=0;
		for(IlsStatsHistory ilsStats:ilsStatsHisList){
			session.save(ilsStats);
			count++;
			if(count%10==0){
				session.flush();
				session.clear();
			}
		}
		transaction.commit();
	}
	catch(Exception e){
		System.out.println("Data Insertion Failed in ILS history table - Time : " +new Date());
		e.printStackTrace();
		return 0;
		//throw new Exception("Failed to Fetch all Inline Services ",e);
	}finally{
		session.close();
		
	}
	return 1;
	}

	@Override
	public int deleteAllInlineStats() {
		Session session=null;
		Transaction transaction=null;
	try{
		session=sessionFactory.openSession();
		transaction=session.beginTransaction();
		Query query=session.createQuery("Delete from IlsStats");
		query.executeUpdate();
		transaction.commit();
	}
	catch(Exception e){
		e.printStackTrace();
		return 0;
		//throw new Exception("Failed to Fetch all Inline Services ",e);
	}finally{
		session.close();
	}
		return 1;
	}

	@Override
	public List<ServerStats> getAllServerStats() {
		Session session=null;
		List<ServerStats> serverStatsList=new ArrayList<ServerStats>();
	try{
		session=sessionFactory.openSession();
		Query query=session.createQuery("From ServerStats");
		serverStatsList=(List<ServerStats>)query.list();
		if(serverStatsList==null && serverStatsList.size()<=0){
			throw new NullPointerException("No data present in inline Stats table" + new Date());
		}
		
	}
	catch(Exception e){
		e.printStackTrace();
		//throw new Exception("Failed to Fetch all Inline Services ",e);
		return null;
	}finally{
		session.close();
	}
	return serverStatsList;
	}

	@Override
	public int insertInServerStatsHistory(List<ServerStatsHistory> serverStats) {
		Session session=null;
		Transaction transaction=null;
	try{
		session=sessionFactory.openSession();
		transaction=session.beginTransaction();
		int count=0;
		for(ServerStatsHistory serverSt:serverStats){
			session.save(serverSt);
			count++;
			if(count%10==0){
				session.flush();
				session.clear();
			}
		}
		transaction.commit();
	}
	catch(Exception e){
		e.printStackTrace();
		return 0;
		//throw new Exception("Failed to Fetch all Inline Services ",e);
	}finally{
		session.close();
	}
	return 1;
	}

	
	@Override
	public int deleteAllServerStats() {
		Session session=null;
		Transaction transaction=null;
	try{
		session=sessionFactory.openSession();
		transaction=session.beginTransaction();
		Query query=session.createQuery("Delete from ServerStats");
		query.executeUpdate();
		transaction.commit();
	}
	catch(Exception e){
		e.printStackTrace();
		return 0;
		//throw new Exception("Failed to Fetch all Inline Services ",e);
	}finally{
		session.close();
	}
		return 1;
	}

	@Override
	public int insertInIlsStats(List<IlsStats> ilsStatsList) {
		Session session=null;
		Transaction transaction=null;
		System.out.println("Inside Insert in ILS STAT Tables");
	try{
		session=sessionFactory.openSession();
		transaction=session.beginTransaction();
		int count=0;
		for(IlsStats ilsStats:ilsStatsList){
			session.save(ilsStats);
			count++;
			System.out.println("Count " + count);
			if(count%10==0){
				session.flush();
				session.clear();
			}
		}
		System.out.println(count + " Rows Inserted");
		transaction.commit();
	}
	catch(Exception e){
		e.printStackTrace();
		return 0;
		//throw new Exception("Failed to Fetch all Inline Services ",e);
	}finally{
		session.close();
	}
	return 1;
	}
	
	@Override
	public int insertInServerStats(List<ServerStats> serverStats) {
		Session session=null;
		Transaction transaction=null;
	try{
		session=sessionFactory.openSession();
		transaction=session.beginTransaction();
		int count=0;
		for(ServerStats serverSt:serverStats){
			session.save(serverSt);
			count++;
			if(count%10==0){
				session.flush();
				session.clear();
			}
		}
		System.out.println(count + " Rows Inserted");
		transaction.commit();
	}
	catch(Exception e){
		e.printStackTrace();
		return 0;
		//throw new Exception("Failed to Fetch all Inline Services ",e);
	}finally{
		session.close();
	}
	return 1;
	}

	@Override
	public ServerStatsHistory getServerStatusByDate(Integer serverId, String startDate) {
		// TODO Auto-generated method stub
		Session session=null;
		ServerStatsHistory serverStats=new ServerStatsHistory();
	try{
		session=sessionFactory.openSession();
		String endDate=DateConversionUtility.incrementDateBy1(startDate,FORMAT1);
		startDate=DateConversionUtility.convertDateToAnotherFormat(startDate, FORMAT1, FORMAT2);
		endDate=DateConversionUtility.convertDateToAnotherFormat(endDate, FORMAT1, FORMAT2);
		System.out.println("Start DAte" + startDate);
		System.out.println("End DAte" + endDate);
		Query query=session.createQuery("Select sum(reqCountAfterRefresh),sum(sessnCountAfterRefresh),sum(timedOutReqCountAfterRefresh) From ServerStatsHistory where serverId=:serverId and refreshTime >=:startDate and refreshTime<=:endDate   ");
		query.setParameter("serverId", serverId);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		List<Object[]> groupList = query.list();
		for(Object[] arr : groupList){
			
			for(int i=0;i<arr.length;i++){
				System.out.println(arr[i]);
				if(i==0) serverStats.setReqCountAfterRefresh(Long.valueOf(arr[i].toString()));
				else if(i==1) serverStats.setSessnCountAfterRefresh(Long.valueOf(arr[i].toString()));
				else if(i==2) serverStats.setTimedOutReqCountAfterRefresh(Long.valueOf(arr[i].toString()));
			}
		}
		//serverStatList=(List<ServerStatsHistory>)query.list();
		/*if(serverStatList.size()>0){
			serverStats=serverStatList.get(0);
		}else throw new NullPointerException("No Data Available for entered Date");*/
	}
	catch(Exception e){
		e.printStackTrace();
		return serverStats;
		//throw new Exception("Failed to Fetch all Inline Services ",e);
	}finally{
		session.close();
	}
	return serverStats;
	}

	@Override
	public List<ServerStatsHistory> getAllServerStatusByDate(String date) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Session session=null;
		
		List<ServerStatsHistory> serverStatList=new ArrayList<ServerStatsHistory>();
	try{
		session=sessionFactory.openSession();
		String endDate=DateConversionUtility.incrementDateBy1(date,FORMAT1);
		String startDate=DateConversionUtility.convertDateToAnotherFormat(date, FORMAT1, FORMAT2);
		endDate=DateConversionUtility.convertDateToAnotherFormat(endDate, FORMAT1, FORMAT2);
		Query query=session.createQuery("Select serverId,sum(reqCountAfterRefresh),sum(sessnCountAfterRefresh),sum(timedOutReqCountAfterRefresh) From ServerStatsHistory where refreshTime >=:startDate and refreshTime<=:endDate group by serverId   ");
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		List<Object[]> groupList = query.list();
		for(Object[] arr : groupList){
			ServerStatsHistory serverStats=new ServerStatsHistory();
			for(int i=0;i<arr.length;i++){
				System.out.println(arr[i]);
				if(i==0) serverStats.setServerId(Integer.valueOf(arr[i].toString()));
				if(i==1) serverStats.setReqCountAfterRefresh(Long.valueOf(arr[i].toString()));
				else if(i==2) serverStats.setSessnCountAfterRefresh(Long.valueOf(arr[i].toString()));
				else if(i==3) serverStats.setTimedOutReqCountAfterRefresh(Long.valueOf(arr[i].toString()));
			}
			serverStatList.add(serverStats);
		}
		//serverStatList=(List<ServerStatsHistory>)query.list();
		/*if(serverStatList.size()>0){
			serverStats=serverStatList.get(0);
		}else throw new NullPointerException("No Data Available for entered Date");*/
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

	@SuppressWarnings("unchecked")
	@Override
	public List<ServerMonitor> getAllServersByState(String serverType) {
			Session session=null;
			List<ServerMonitor> serverList=new ArrayList<ServerMonitor>();
			System.out.println("server Type " + serverType);
		try{
			serverType=serverType.trim();
			session=sessionFactory.openSession();
			Query query=session.createQuery("From ServerMonitor where serverState=:serverType");
			query.setParameter("serverType", serverType);
			serverList=(List<ServerMonitor>)query.list();
			System.out.println("Server List Size " +serverList.size());
		}
		catch(Exception e){
			e.printStackTrace();
			//throw new Exception("Failed to Fetch all Inline Services ",e);
		}finally{
			session.close();
		}
		return serverList;
		}

	@Override
	public List<ServerStatsHistory> getAllServerStatsByDate(String date) {
		// TODO Auto-generated method stub
	Session session=null;
		
		List<ServerStatsHistory> serverStatList=new ArrayList<ServerStatsHistory>();
	try{
		session=sessionFactory.openSession();
		String endDate=DateConversionUtility.incrementDateBy1(date,FORMAT1);
		String startDate=DateConversionUtility.convertDateToAnotherFormat(date, FORMAT1, FORMAT2);
		endDate=DateConversionUtility.convertDateToAnotherFormat(endDate, FORMAT1, FORMAT2);
		Query query=session.createQuery("From ServerStatsHistory where refreshTime >=:startDate and refreshTime<=:endDate");
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		serverStatList = (List<ServerStatsHistory>)query.list();
		//serverStatList=(List<ServerStatsHistory>)query.list();
		if(serverStatList==null || serverStatList.size()<=0)
		throw new NullPointerException("No Data Available for entered Date");
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

	@Override
	public List<Alert> getAlertsByDate(String date) {
		// TODO Auto-generated method stub
		Session session=null;
		String startDate=null;
		List<Alert> alertList=new ArrayList<Alert>();
	try{
		if(date!=null){
			session=sessionFactory.openSession();
			String endDate=DateConversionUtility.incrementDateBy1(date,FORMAT1);
			startDate=DateConversionUtility.convertDateToAnotherFormat(date, FORMAT1, FORMAT2);
			endDate=DateConversionUtility.convertDateToAnotherFormat(endDate, FORMAT1, FORMAT2);
			System.out.println("Start DAte" + startDate);
			System.out.println("End DAte" + endDate);
			Query query=session.createQuery("From Alert where alertDate >=:startDate and alertDate<=:endDate   ");
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
			alertList=(List<Alert>)query.list();
			if(alertList==null || alertList.size()<0){
				throw new NullPointerException("No Data Available for entered Date");
			}
		}
		else{
			throw new Exception("Date cannot be null");
		}
	}
	catch(Exception e){
		e.printStackTrace();
		return alertList;
		//throw new Exception("Failed to Fetch all Inline Services ",e);
	}finally{
		session.close();
	}
	return alertList;
	}

	@Override
	public int getAlertsByDate(List<Alert> alertList) {
		// TODO Auto-generated method stub
		Session session=null;
		Transaction transaction=null;
		System.out.println("Inside Alert Inserter");
	try{
		session=sessionFactory.openSession();
		transaction=session.beginTransaction();
		int count=0;
		for(Alert alert:alertList){
			System.out.println(alert.getAlertType());
			session.save(alert);
			count++;
			if(count%10==0){
				session.flush();
				session.clear();
			}
		}
		transaction.commit();
	}
	catch(Exception e){
		System.out.println("Data Insertion Failed in Alert table - Time : " +new Date());
		e.printStackTrace();
		return 0;
		//throw new Exception("Failed to Fetch all Inline Services ",e);
	}finally{
		session.close();
	}
	return 1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RecommendationStats> getRecomStats(String date) {
		// TODO Auto-generated method stub
		Session session=null;
		String startDate=null;
		List<RecommendationStats> recomList=new ArrayList<RecommendationStats>();
	try{
		if(date!=null){
			session=sessionFactory.openSession();
			date=DateConversionUtility.convertDateToAnotherFormat(date, FORMAT1, FORMAT2);
			System.out.println("Start DAte" + date);
			Query query=session.createQuery("From RecommendationStats where sessionDate=:startDate");
			query.setParameter("startDate", date);
			recomList=(List<RecommendationStats>)query.list();
			if(recomList==null || recomList.size()<0){
				throw new NullPointerException("No Data Available for entered Date");
			}
		}
		else{
			throw new Exception("Date cannot be null");
		}
	}
	catch(Exception e){
		e.printStackTrace();
		return recomList;
		//throw new Exception("Failed to Fetch all Inline Services ",e);
	}finally{
		session.close();
	}
	return recomList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MaxRecomStats> getMaxRecomStats() {
		// TODO Auto-generated method stub
		Session session=null;
		List<MaxRecomStats> recomList=new ArrayList<MaxRecomStats>();
	try{
			session=sessionFactory.openSession();
			Query query=session.createQuery("From MaxRecomStats");
			recomList=(List<MaxRecomStats>)query.list();
			System.out.println("Recom List " + recomList.size());
	}
	catch(Exception e){
		e.printStackTrace();
		return recomList;
		//throw new Exception("Failed to Fetch all Inline Services ",e);
	}finally{
		session.close();
	}
	return recomList;
	}
}
