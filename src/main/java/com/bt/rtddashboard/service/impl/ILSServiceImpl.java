/**
 * 
 */
package com.bt.rtddashboard.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import com.bt.rtddashboard.service.InlineService;

/**
 * @author 609669080
 *
 */
@Service
@Transactional(readOnly = false)
public class ILSServiceImpl implements InlineService{

	@Autowired
	private InlineDao dataAccess;

	
	@Override
	public List<ILSModel> getAllInlineService() {
		// TODO Auto-generated method stub
		return dataAccess.getAllInlineService();
	}


	@Override
	public ServerMonitor getAllInlineByServer(Integer serverId) {
		// TODO Auto-generated method stub
		return dataAccess.getAllInlineByServer(serverId);
	}


	@Override
	public List<ServerMonitor> getAllServers() {
		// TODO Auto-generated method stub
		return dataAccess.getAllServers();
	}


	@Override
	public ServerStats getServerStats(Integer serverId) {
		// TODO Auto-generated method stub
		return dataAccess.getServerStats(serverId);
	}


	@Override
	public List<IlsStats> getInlineStatusByServer(Integer serverId) {
		// TODO Auto-generated method stub
		return dataAccess.getInlineStatusByServer(serverId);
	}


	@Override
	public List<IlsStats> getAllInlineStats() {
		// TODO Auto-generated method stub
		System.out.println("Inside Service");
		return dataAccess.getAllInlineStats();
	}


	@Override
	public int insertInIlsStatsHistory(List<IlsStatsHistory> ilsStatsHisList) {
		// TODO Auto-generated method stub
		return dataAccess.insertInIlsStatsHistory(ilsStatsHisList);
	}


	@Override
	public int deleteAllInlineStats() {
		// TODO Auto-generated method stub
		return dataAccess.deleteAllInlineStats();
	}


	@Override
	public List<ServerStats> getAllServerStats() {
		// TODO Auto-generated method stub
		return dataAccess.getAllServerStats();
	}


	@Override
	public int insertInServerStatsHistory(List<ServerStatsHistory> serverStats) {
		// TODO Auto-generated method stub
		return dataAccess.insertInServerStatsHistory(serverStats);
	}


	@Override
	public int deleteAllServerStats() {
		// TODO Auto-generated method stub
		return dataAccess.deleteAllServerStats();
	}


	@Override
	public int insertInIlsStats(List<IlsStats> ilsStatsList) {
		// TODO Auto-generated method stub
		return dataAccess.insertInIlsStats(ilsStatsList);
	}


	@Override
	public int insertInServerStats(List<ServerStats> serverStats) {
		// TODO Auto-generated method stub
		return dataAccess.insertInServerStats(serverStats);
	}


	@Override
	public ServerStatsHistory getServerStatusByDate(Integer serverId, String startDate) {
		// TODO Auto-generated method stub
		return dataAccess.getServerStatusByDate(serverId,startDate);
	}


	@Override
	public List<ServerStatsHistory> getAllServerStatusByDate(String date) {
		// TODO Auto-generated method stub
		return dataAccess.getAllServerStatusByDate(date);
	}


	@Override
	public List<ServerMonitor> getAllServersByState(String serverType) {
		// TODO Auto-generated method stub
		return dataAccess.getAllServersByState(serverType);
	}


	@Override
	public List<ServerStatsHistory> getAllServerStatsByDate(String startDate) {
		// TODO Auto-generated method stub
		return dataAccess.getAllServerStatsByDate(startDate);
	}


	@Override
	public List<Alert> getAlertsByDate(String date) {
		// TODO Auto-generated method stub
		return dataAccess.getAlertsByDate(date);
	}


	@Override
	public int insertAlertsData(List<Alert> alertList) {
		// TODO Auto-generated method stub
		return dataAccess.getAlertsByDate(alertList);
	}


	@Override
	public List<RecommendationStats> getRecomStats(String date) {
		// TODO Auto-generated method stub
		return dataAccess.getRecomStats(date);
	}


	@Override
	public List<MaxRecomStats> getMaxRecomStats() {
		// TODO Auto-generated method stub
		return dataAccess.getMaxRecomStats();
	}


	

}
