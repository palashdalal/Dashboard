/**
 * 
 */
package com.bt.rtddashboard.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bt.rtddashboard.dao.TransactionMonitoringDao;
import com.bt.rtddashboard.model.TransactionMonitoringVo;
import com.bt.rtddashboard.service.TransactionMonitoringService;

/**
 * @author 609669080
 *
 */
@Service
@Transactional(readOnly = false)
public class TransactionMonitoringServiceImpl implements TransactionMonitoringService{
	
	@Autowired
	TransactionMonitoringDao dataAccess;
	@Override
	public List<TransactionMonitoringVo> getTransactionList(String startDate, String enddate, String customerId,
			String accountId) {
		// TODO Auto-generated method stub
		return dataAccess.getTransactionSessions(startDate, enddate, customerId, accountId);
	}
	@Override
	public List<TransactionMonitoringVo> getTransactionListByDate(String startDate, String endDate) {
		// TODO Auto-generated method stub
		return dataAccess.getTransactionListByDate(startDate, endDate);
	}
	@Override
	public List<TransactionMonitoringVo> getTransactionMonitoringSessionsByDateAndID(String startDate, String endDate,
			String paramId, String paramValue) {
		// TODO Auto-generated method stub
		return dataAccess.getTransactionMonitoringSessionsByDateAndID(startDate, endDate,paramId,paramValue);
	}

}
