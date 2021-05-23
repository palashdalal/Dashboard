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
public class IlsStatsHistory {
	
	@Id
	@SequenceGenerator(name="ilsHistorySeq", sequenceName="SEQ_ILSSTATSHISTORY",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="ilsHistorySeq")
	private int id;
	private int ilsid;
	private int serverId;
	private String status;
	private Date refreshTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIlsid() {
		return ilsid;
	}
	public void setIlsid(int ilsid) {
		this.ilsid = ilsid;
	}
	public int getServerId() {
		return serverId;
	}
	public void setServerId(int serverId) {
		this.serverId = serverId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getRefreshTime() {
		return refreshTime;
	}
	public void setRefreshTime(Date refreshTime) {
		this.refreshTime = refreshTime;
	}
	
	
}
