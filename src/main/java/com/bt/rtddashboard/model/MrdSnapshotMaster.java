/**
 * 
 */
package com.bt.rtddashboard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 609669080
 *
 */
@Entity
@Table(name="MRD_MONITOR_SUMMARY")
public class MrdSnapshotMaster {

	@Id
	@Column(name="MRD_ID")
	private Integer mrdId;
	@Column(name="MRD_TABLE_NAME")
	private String mrdTableName;
	@Column(name="MRD_OWNER_NAME")
	private String mrdOwnerName;
	@Column(name="LAST_REFRESH_TIME")
	private String lastRefreshTime;
	@Column(name="RTD_LOAD_TIME")
	private String rtdLoadTime;
	@Column(name="CURRENT_VOLUME")
	private Double currentVolume;
	@Column(name="PREVIOUS_VOLUME")
	private Double previousVolume;
	@Column(name="DELTA_CHANGE")
	private Double deltaChange;
	@Column(name="STATUS")
	private String status;
	
	public Integer getMrdId() {
		return mrdId;
	}
	public void setMrdId(Integer mrdId) {
		this.mrdId = mrdId;
	}
	public String getMrdTableName() {
		return mrdTableName;
	}
	public void setMrdTableName(String mrdTableName) {
		this.mrdTableName = mrdTableName;
	}
	public String getMrdOwnerName() {
		return mrdOwnerName;
	}
	public void setMrdOwnerName(String mrdOwnerName) {
		this.mrdOwnerName = mrdOwnerName;
	}
	public String getLastRefreshTime() {
		return lastRefreshTime;
	}
	public void setLastRefreshTime(String lastRefreshTime) {
		this.lastRefreshTime = lastRefreshTime;
	}
	public String getRtdLoadTime() {
		return rtdLoadTime;
	}
	public void setRtdLoadTime(String rtdLoadTime) {
		this.rtdLoadTime = rtdLoadTime;
	}
	public Double getCurrentVolume() {
		return currentVolume;
	}
	public void setCurrentVolume(Double currentVolume) {
		this.currentVolume = currentVolume;
	}
	public Double getPreviousVolume() {
		return previousVolume;
	}
	public void setPreviousVolume(Double previousVolume) {
		this.previousVolume = previousVolume;
	}
	public Double getDeltaChange() {
		return deltaChange;
	}
	public void setDeltaChange(Double deltaChange) {
		this.deltaChange = deltaChange;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
