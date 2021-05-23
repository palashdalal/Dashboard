/**
 * 
 */
package com.bt.rtddashboard.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.PathParam;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bt.rtddashboard.dao.AttributeDao;
import com.bt.rtddashboard.model.Contacts;
import com.bt.rtddashboard.model.MrdDetailMaster;
import com.bt.rtddashboard.model.MrdSnapshotMaster;
import com.bt.rtddashboard.model.ProspectAttributeMaster;
import com.bt.rtddashboard.model.ServerStatsHistory;
import com.bt.rtddashboard.model.StandardAttributeMaster;

/**
 * @author 609669080
 *
 */
@Repository
public class AttributeDaoImpl implements AttributeDao{
	
	@Autowired
	SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<StandardAttributeMaster> getStandardAttributesByJobType(String jobType) {
		List<StandardAttributeMaster> attList=new ArrayList<StandardAttributeMaster>();
		Session session=null;
	try{
		session=sessionFactory.openSession();
		Query query=session.createQuery("From StandardAttributeMaster where jobType=:jobType");
		query.setParameter("jobType", jobType);
		attList=(List<StandardAttributeMaster>)query.list();
	}
	catch(Exception e){
		e.printStackTrace();
		//throw new Exception("Failed to Fetch all Inline Services ",e);
	}finally{
		session.close();
	}
		return attList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProspectAttributeMaster> getProspectAttributesByJobType(String jobType) {
		// TODO Auto-generated method stub
		List<ProspectAttributeMaster> attList=new ArrayList<ProspectAttributeMaster>();
		Session session=null;
	try{
		session=sessionFactory.openSession();
		Query query=session.createQuery("From ProspectAttributeMaster where jobType=:jobType");
		query.setParameter("jobType", jobType);
		attList=(List<ProspectAttributeMaster>)query.list();
	}
	catch(Exception e){
		e.printStackTrace();
		//throw new Exception("Failed to Fetch all Inline Services ",e);
	}finally{
		session.close();
	}
		return attList;
	}

	@Override
	public List<StandardAttributeMaster> getStandardAttributeByName(String jobType, String attName) {
		List<StandardAttributeMaster> attList=new ArrayList<StandardAttributeMaster>();
		Session session=null;
	try{
		session=sessionFactory.openSession();
		Query query=session.createQuery("From StandardAttributeMaster where jobType=:jobType and attName=:attName");
		query.setParameter("jobType", jobType);
		query.setParameter("attName", attName);
		attList=(List<StandardAttributeMaster>)query.list();
	}
	catch(Exception e){
		e.printStackTrace();
		//throw new Exception("Failed to Fetch all Inline Services ",e);
	}finally{
		session.close();
	}
		return attList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProspectAttributeMaster> getProspectAttributeByName(String jobType, String attName) {
		List<ProspectAttributeMaster> attList=new ArrayList<ProspectAttributeMaster>();
		Session session=null;
	try{
		session=sessionFactory.openSession();
		Query query=session.createQuery("From ProspectAttributeMaster where jobType=:jobType and attName=:attName");
		query.setParameter("jobType", jobType);
		query.setParameter("attName", attName);
		attList=(List<ProspectAttributeMaster>)query.list();
	}
	catch(Exception e){
		e.printStackTrace();
		//throw new Exception("Failed to Fetch all Inline Services ",e);
	}finally{
		session.close();
	}
		return attList;
	}

	@Override
	public List<MrdSnapshotMaster> getAllMRDTablesStatus() {
		List<MrdSnapshotMaster> mrdList=new ArrayList<MrdSnapshotMaster>();
		Session session=null;
	try{
		session=sessionFactory.openSession();
		Query query=session.createQuery("From MrdSnapshotMaster");
		mrdList=(List<MrdSnapshotMaster>)query.list();
	}
	catch(Exception e){
		e.printStackTrace();
		//throw new Exception("Failed to Fetch all Inline Services ",e);
	}finally{
		session.close();
	}
		return mrdList;
	}

	@Override
	public List<MrdSnapshotMaster> getMRDTablesByStatus(String status) {
		List<MrdSnapshotMaster> mrdList=new ArrayList<MrdSnapshotMaster>();
		Session session=null;
	try{
		session=sessionFactory.openSession();
		Query query=session.createQuery("From MrdSnapshotMaster where status=:status");
		query.setParameter("status", status);
		mrdList=(List<MrdSnapshotMaster>)query.list();
	}
	catch(Exception e){
		e.printStackTrace();
		//throw new Exception("Failed to Fetch all Inline Services ",e);
	}finally{
		session.close();
	}
		return mrdList;
	}

	@Override
	public List<MrdDetailMaster> getMRDDetailByName(String tableName) {
		List<MrdDetailMaster> mrdList=new ArrayList<MrdDetailMaster>();
		Session session=null;
	try{
		session=sessionFactory.openSession();
		Query query=session.createQuery("From MrdDetailMaster where mrdTableName=:tableName");
		query.setParameter("tableName", tableName);
		mrdList=(List<MrdDetailMaster>)query.list();
	}
	catch(Exception e){
		e.printStackTrace();
		//throw new Exception("Failed to Fetch all Inline Services ",e);
	}finally{
		session.close();
	}
		return mrdList;
	}
}

	

