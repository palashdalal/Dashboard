/**
 * 
 */
package com.bt.rtddashboard.model;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * @author 609669080
 *
 */
public class ServerIlsMap {

	@OneToOne
	@JoinColumn(name="SERVERID")
	private Integer serverId;
	
	@OneToOne
	@JoinColumn(name="ILSID")
	private Integer ilsId;

	public Integer getServerId() {
		return serverId;
	}

	public void setServerId(Integer serverId) {
		this.serverId = serverId;
	}

	public Integer getIlsId() {
		return ilsId;
	}

	public void setIlsId(Integer ilsId) {
		this.ilsId = ilsId;
	}
	
	
}
