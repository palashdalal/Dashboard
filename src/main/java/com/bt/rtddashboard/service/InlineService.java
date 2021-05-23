/**
 * 
 */
package com.bt.rtddashboard.service;

import java.util.Date;
import java.util.List;

import com.bt.rtddashboard.model.Alert;
import com.bt.rtddashboard.model.ILSModel;
import com.bt.rtddashboard.model.IlsStats;
import com.bt.rtddashboard.model.IlsStatsHistory;
import com.bt.rtddashboard.model.MaxRecomStats;
import com.bt.rtddashboard.model.RecommendationStats;
import com.bt.rtddashboard.model.ServerMonitor;
import com.bt.rtddashboard.model.ServerStats;
import com.bt.rtddashboard.model.ServerStatsHistory;

/**
 * @author 609669080
 *
 */
public interface InlineService {

	public List<ILSModel> getAllInlineService();

	public ServerMonitor getAllInlineByServer(Integer serverId);

	public List<ServerMonitor> getAllServers();

	public ServerStats getServerStats(Integer serverId);

	public List<IlsStats> getInlineStatusByServer(Integer serverId);

	public List<IlsStats> getAllInlineStats();

	public int insertInIlsStatsHistory(List<IlsStatsHistory> ilsStatsHisList);
	
	public int deleteAllInlineStats();

	public List<ServerStats> getAllServerStats();

	public int insertInServerStatsHistory(List<ServerStatsHistory> serverStatsHistory);

	public int deleteAllServerStats();

	public int insertInIlsStats(List<IlsStats> ilsStatsList);
	
	public int insertInServerStats(List<ServerStats> serverStats);

	public ServerStatsHistory getServerStatusByDate(Integer serverId, String startDate);

	public List<ServerStatsHistory> getAllServerStatusByDate(String date);

	public List<ServerMonitor> getAllServersByState(String serverType);

	public List<ServerStatsHistory> getAllServerStatsByDate(String startDate);

	public List<Alert> getAlertsByDate(String date);

	public int insertAlertsData(List<Alert> alertList);

	public List<RecommendationStats> getRecomStats(String date);

	public List<MaxRecomStats> getMaxRecomStats();
}
