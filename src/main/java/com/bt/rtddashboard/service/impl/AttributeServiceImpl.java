/**
 * 
 */
package com.bt.rtddashboard.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bt.rtddashboard.dao.AttributeDao;
import com.bt.rtddashboard.model.MrdDetailMaster;
import com.bt.rtddashboard.model.MrdSnapshotMaster;
import com.bt.rtddashboard.model.ProspectAttributeMaster;
import com.bt.rtddashboard.model.StandardAttributeMaster;
import com.bt.rtddashboard.service.AttributeService;

/**
 * @author 609669080
 *
 */
@Service
@Transactional(readOnly = false)
public class AttributeServiceImpl implements AttributeService{

	@Autowired
	private AttributeDao dataAccess;
	@Override
	public List<StandardAttributeMaster> getStandardAttributesByJobType(String jobType) {
		// TODO Auto-generated method stub
		return dataAccess.getStandardAttributesByJobType(jobType);
	}
	@Override
	public List<ProspectAttributeMaster> getProspectAttributesByJobType(String jobType) {
		// TODO Auto-generated method stub
		return dataAccess.getProspectAttributesByJobType(jobType);
	}
	@Override
	public List<StandardAttributeMaster> getStandardAttributeByName(String jobType, String attName) {
		// TODO Auto-generated method stub
		return  dataAccess.getStandardAttributeByName(jobType,attName);
	}
	@Override
	public List<ProspectAttributeMaster> getProspectAttributeByName(String jobType, String attName) {
		// TODO Auto-generated method stub
		return  dataAccess.getProspectAttributeByName(jobType,attName);
	}
	@Override
	public List<MrdSnapshotMaster> getAllMRDTablesStatus() {
		// TODO Auto-generated method stub
		return dataAccess.getAllMRDTablesStatus();
	}
	@Override
	public List<MrdSnapshotMaster> getMRDTablesByStatus(String status) {
		// TODO Auto-generated method stub
		return dataAccess.getMRDTablesByStatus(status);
	}
	@Override
	public List<MrdDetailMaster> getMRDDetailByName(String tableName) {
		// TODO Auto-generated method stub
		return dataAccess.getMRDDetailByName(tableName);
	}

}
