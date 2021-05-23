/**
 * 
 */
package com.bt.rtddashboard.dao;

import java.util.List;

import com.bt.rtddashboard.model.ServerStatsHistory;

/**
 * @author 609669080
 *
 */
public interface ReportDao {

	public List<ServerStatsHistory> getAllStatsHistory();

	public List<ServerStatsHistory> getServerStatsByServer(Integer serverId);

	public List<ServerStatsHistory> getServerStatsByDate(Integer serverId, String startDate, String endDate);

}
