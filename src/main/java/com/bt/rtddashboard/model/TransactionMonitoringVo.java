package com.bt.rtddashboard.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 609761425
 *
 */
@Entity
@Table(name="SESSION_LOG_DATA")
public class TransactionMonitoringVo {


	@Id
	@Column(name="SESSIONID")	
	String sessionId;
		@Column(name="REQUEST")
		String request;
		@Column(name="RESPONSE")
		String response;
		 
		@Column(name="ADVISOR_NAME")
		String advisorName;
		
		@Column(name="REQUEST_START_TIME")
		String requestStartTime;
		
		@Column(name="REQUEST_END_TIME")
		String requestEndTime;
		
		@Column(name="CUSTOMERID")
		String customerId;
		
		@Column(name="ACCOUNTID")
		String accountId;
		
		@Column(name="CUSTOMERDETAILS_DB_START_TIME")
		String customerDetailsDBStartTime;
		
		@Column(name="CUSTOMERDETAILS_DB_END_TIME")
		String customerDetailsDBEndTime;
		
		@Column(name="SESSIONCLEANUPTIME")
		String sessionCleanUpTime;
		
		@Column(name="REGRADEMATRIX_STARTTIME")
		String regradeMatrixStartTime;
		
		@Column(name="REGRADEMATRIX_ENDTIME")
		String regradeMatrixEndTime;
		
		@Column(name="DATE_LOG")
		String date;
		
		@Column(name="DATE_INSERTION")
		Date dateInsert;
		
		 public String getSessionId() {
			return sessionId;
		}
	
		public void setSessionId(String sessionId) {
			this.sessionId = sessionId;
		}
	
		public String getCustomerDetailsDBStartTime() {
			return customerDetailsDBStartTime;
		}

		public void setCustomerDetailsDBStartTime(String customerDetailsDBStartTime) {
			this.customerDetailsDBStartTime = customerDetailsDBStartTime;
		}


		public String getCustomerDetailsDBEndTime() {
			return customerDetailsDBEndTime;
		}

		public void setCustomerDetailsDBEndTime(String customerDetailsDBEndTime) {
			this.customerDetailsDBEndTime = customerDetailsDBEndTime;
		}

		public String getSessionCleanUpTime() {
			return sessionCleanUpTime;
		}

		public void setSessionCleanUpTime(String sessionCleanUpTime) {
			this.sessionCleanUpTime = sessionCleanUpTime;
		}

		public String getRegradeMatrixStartTime() {
			return regradeMatrixStartTime;
		}

		public void setRegradeMatrixStartTime(String regradeMatrixStartTime) {
			this.regradeMatrixStartTime = regradeMatrixStartTime;
		}

		public String getRegradeMatrixEndTime() {
			return regradeMatrixEndTime;
		}

		public void setRegradeMatrixEndTime(String regradeMatrixEndTime) {
			this.regradeMatrixEndTime = regradeMatrixEndTime;
		}

		public String getRequest() {
			return request;
		}
	
		public void setRequest(String request) {
			this.request = request;
		}
	
		public String getResponse() {
			return response;
		}
	
		public void setResponse(String response) {
			this.response = response;
		}
	
		public String getRequestStartTime() {
			return requestStartTime;
		}

		public void setRequestStartTime(String requestStartTime) {
			this.requestStartTime = requestStartTime;
		}

		public String getRequestEndTime() {
			return requestEndTime;
		}

		public void setRequestEndTime(String requestEndTime) {
			this.requestEndTime = requestEndTime;
		}

		public String getCustomerId() {
			return customerId;
		}

		public void setCustomerId(String customerId) {
			this.customerId = customerId;
		}

		public String getAccountId() {
			return accountId;
		}

		public void setAccountId(String accountId) {
			this.accountId = accountId;
		}

		public String getAdvisorName() {
			return advisorName;
		}
	
		public void setAdvisorName(String advisorName) {
			this.advisorName = advisorName;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public Date getDateInsert() {
			return dateInsert;
		}

		public void setDateInsert(Date dateInsert) {
			this.dateInsert = dateInsert;
		}
}
