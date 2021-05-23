/**
 * 
 */
package com.bt.rtddashboard.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author 609669080
 *
 */
@Entity
public class ServerStats {
	@Id
	private int serverId;
	private Long totReqCount;
	private Long totSessnCount;
	private Long totTimedOutReqCount;
	private Long reqCountAfterRefresh;
	private Long sessnCountAfterRefresh;
	private Long timedOutReqCountAfterRefresh;
	private Date refreshTime;
	
	public ServerStats(){
		totReqCount=0L;
		totSessnCount=0L;
		totTimedOutReqCount=0L;
		reqCountAfterRefresh=0L;
		sessnCountAfterRefresh=0L;
		timedOutReqCountAfterRefresh=0L;
	}
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
	public Date getRefreshTime() {
		return refreshTime;
	}
	public void setRefreshTime(Date refreshTime) {
		this.refreshTime = refreshTime;
	}
	
	
	
}
