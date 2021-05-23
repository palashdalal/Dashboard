/**
 * 
 */
package com.bt.rtddashboard.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * @author 609669080
 *
 */
@Entity
public class ServerStatsHistory {
	@Id
	@SequenceGenerator(name="serverHistorySeq", sequenceName="SEQ_SERVERSTATSHISTORY",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="serverHistorySeq")
	private int id;
	private int serverId;
	private Long totReqCount;
	private Long totSessnCount;
	private Long totTimedOutReqCount;
	private Long reqCountAfterRefresh;
	private Long sessnCountAfterRefresh;
	private Long timedOutReqCountAfterRefresh;
	private String refreshTime;
	
	public int getServerId() {
		return serverId;
	}
	public void setServerId(int serverId) {
		this.serverId = serverId;
	}
	public Long getTotReqCount() {
		return totReqCount;
	}
	public void setTotReqCount(Long totReqCount) {
		this.totReqCount = totReqCount;
	}
	public Long getTotSessnCount() {
		return totSessnCount;
	}
	public void setTotSessnCount(Long totSessnCount) {
		this.totSessnCount = totSessnCount;
	}
	public Long getTotTimedOutReqCount() {
		return totTimedOutReqCount;
	}
	public void setTotTimedOutReqCount(Long totTimedOutReqCount) {
		this.totTimedOutReqCount = totTimedOutReqCount;
	}
	
	public Long getReqCountAfterRefresh() {
		return reqCountAfterRefresh;
	}
	public void setReqCountAfterRefresh(Long reqCountAfterRefresh) {
		this.reqCountAfterRefresh = reqCountAfterRefresh;
	}
	public Long getSessnCountAfterRefresh() {
		return sessnCountAfterRefresh;
	}
	public void setSessnCountAfterRefresh(Long sessnCountAfterRefresh) {
		this.sessnCountAfterRefresh = sessnCountAfterRefresh;
	}
	public Long getTimedOutReqCountAfterRefresh() {
		return timedOutReqCountAfterRefresh;
	}
	public void setTimedOutReqCountAfterRefresh(Long timedOutReqCountAfterRefresh) {
		this.timedOutReqCountAfterRefresh = timedOutReqCountAfterRefresh;
	}
	public String getRefreshTime() {
		return refreshTime;
	}
	public void setRefreshTime(String refreshTime) {
		this.refreshTime = refreshTime;
	}
}
