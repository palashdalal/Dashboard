 /**
 * 
 */
package com.bt.rtddashboard.service;

import java.util.Date;
import java.util.List;

import com.bt.rtddashboard.model.Contacts;
import com.bt.rtddashboard.model.TransactionMonitoringVo;

/**
 * @author 609669080
 *
 */
public interface TransactionMonitoringService {

	public List<TransactionMonitoringVo> getTransactionList(String startDate,String enddate,String customerId,String accountId);

	public List<TransactionMonitoringVo> getTransactionListByDate(String startDate, String endDate);

	public List<TransactionMonitoringVo> getTransactionMonitoringSessionsByDateAndID(String startDate, String endDate,
			String paramId, String paramValue);
}
