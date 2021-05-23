/**
 * 
 */
package com.bt.rtddashboard.container;

import java.util.List;

/**
 * @author 609669080
 *
 */
public class ServerContainer {

	private Integer serverId;
	private String serverName;
	private String serverIp;
	private String serverState;
	private Integer serverPort;
	private String userId;
	private String password;
	private Boolean authenticationReq;
	private List<IlsContainer> ilsList;
	public Integer getServerId() {
		return serverId;
	}
	public void setServerId(Integer serverId) {
		this.serverId = serverId;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getServerIp() {
		return serverIp;
	}
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}
	public String getServerState() {
		return serverState;
	}
	public void setServerState(String serverState) {
		this.serverState = serverState;
	}
	public Integer getServerPort() {
		return serverPort;
	}
	public void setServerPort(Integer serverPort) {
		this.serverPort = serverPort;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getAuthenticationReq() {
		return authenticationReq;
	}
	public void setAuthenticationReq(Boolean authenticationReq) {
		this.authenticationReq = authenticationReq;
	}
	public List<IlsContainer> getIlsList() {
		return ilsList;
	}
	public void setIlsList(List<IlsContainer> ilsList) {
		this.ilsList = ilsList;
	}
	
	
}
