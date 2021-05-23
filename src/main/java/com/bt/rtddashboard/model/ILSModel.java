/**
 * 
 */
package com.bt.rtddashboard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;



/**
 * @author 609669080
 *
 */
@Entity
@Table(name="ILSMASTER")
public class ILSModel {
	
	@Id
	private Integer ilsId;
	
	private String ilsName;
	
	@Column(name="CHANNEL")
	private String channelName;
	
	private int serverPort;
	
	private String description;
	
	private String ilsDesc;
	/*@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ILSID")
	private IlsStats ilsStats;*/
	
	public String getIlsName() {
		return ilsName;
	}
	public void setIlsName(String ilsName) {
		this.ilsName = ilsName;
	}
	public Integer getIlsId() {
		return ilsId;
	}
	public void setIlsId(Integer ilsId) {
		this.ilsId = ilsId;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	/*public IlsStats getIlsStats() {
		return ilsStats;
	}
	public void setIlsStats(IlsStats ilsStats) {
		this.ilsStats = ilsStats;
	}*/
	public int getServerPort() {
		return serverPort;
	}
	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIlsDesc() {
		return ilsDesc;
	}
	public void setIlsDesc(String ilsDesc) {
		this.ilsDesc = ilsDesc;
	}
	
}
