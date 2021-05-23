package com.bt.rtddashboard.dao;

import java.util.Date;
import java.util.List;

import com.bt.rtddashboard.model.TransactionMonitoringVo;

public interface TransactionMonitoringDao {
	
	public List<TransactionMonitoringVo> getTransactionSessions(String startDate, String enddate, String customerId, String billingId);

	public List<TransactionMonitoringVo> getTransactionListByDate(String startDate, String endDate);

	public List<TransactionMonitoringVo> getTransactionMonitoringSessionsByDateAndID(String startDate, String endDate,
			String paramId, String paramValue);

}
