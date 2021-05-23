/**
 * 
 */
package com.bt.rtddashboard.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bt.rtddashboard.model.MrdDetailMaster;
import com.bt.rtddashboard.model.MrdSnapshotMaster;
import com.bt.rtddashboard.model.ProspectAttributeMaster;
import com.bt.rtddashboard.model.StandardAttributeMaster;

/**
 * @author 609669080
 *
 */

public interface AttributeService {

	public List<StandardAttributeMaster> getStandardAttributesByJobType(String jobType);

	public List<ProspectAttributeMaster> getProspectAttributesByJobType(String jobType);

	public List<StandardAttributeMaster> getStandardAttributeByName(String jobType, String attName);

	public List<ProspectAttributeMaster> getProspectAttributeByName(String jobType, String attName);

	public List<MrdSnapshotMaster> getAllMRDTablesStatus();

	public List<MrdSnapshotMaster> getMRDTablesByStatus(String status);

	public List<MrdDetailMaster> getMRDDetailByName(String tableName);

}
