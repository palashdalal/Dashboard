/**
 * 
 */
package com.bt.rtddashboard.container;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @author 609669080
 *
 */
public class IlsContainer {

	
	private Integer ilsId;
	private String ilsName;
	private String channelName;
	private String status;
	private Date refreshTime;
	private String ilsDesc;
	
	public Integer getIlsId() {
		return ilsId;
	}
	public void setIlsId(Integer ilsId) {
		this.ilsId = ilsId;
	}
	public String getIlsName() {
		return ilsName;
	}
	public void setIlsName(String ilsName) {
		this.ilsName = ilsName;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
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
	public void setRefreshTime(Date date) {
		this.refreshTime = date;
	}
	public String getIlsDesc() {
		return ilsDesc;
	}
	public void setIlsDesc(String ilsDesc) {
		this.ilsDesc = ilsDesc;
	}
	
	
}
