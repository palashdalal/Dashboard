/**
 * 
 */
package com.bt.rtddashboard.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bt.rtddashboard.dao.InlineDao;
import com.bt.rtddashboard.dao.ReportDao;
import com.bt.rtddashboard.model.Contacts;
import com.bt.rtddashboard.model.ServerStatsHistory;
import com.bt.rtddashboard.service.ReportService;

/**
 * @author 609669080
 *
 */
@Service
@Transactional(readOnly = false)
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportDao dataAccess;
	
	@Override
	public List<ServerStatsHistory> getAllStatsHistory() {
		// TODO Auto-generated method stub
		return dataAccess.getAllStatsHistory();
	}

	@Override
	public List<ServerStatsHistory> getServerStatsByServer(Integer serverId) {
		// TODO Auto-generated method stub
		return dataAccess.getServerStatsByServer(serverId);
	}

	@Override
	public List<ServerStatsHistory> getServerStatsByDate(Integer serverId, String startDate, String endDate) {
		// TODO Auto-generated method stub
		return dataAccess.getServerStatsByDate( serverId,  startDate,  endDate);
	}


}
