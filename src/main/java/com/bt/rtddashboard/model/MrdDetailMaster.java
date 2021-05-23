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
@Table(name="MRD_MONITOR_DETAIL")
public class MrdDetailMaster {

	@Id
	@Column(name="id")
	private Integer id;       
	@Column(name="MRD_ID")
	private Integer mrdId;
	@Column(name="LAST_REFRESH_TIME")
	private String lastRefreshTime;
	@Column(name="MRD_TABLE_NAME")
	private String mrdTableName;
	@Column(name="MRD_ATTRIBUTE_NAME")
	private String mrdAttributeName;
	@Column(name="PREVIOUS_BLANK_COUNT")
	private Double previousBlankCount;
	@Column(name="PREVIOUS_NON_BLANK_COUNT")
	private Double previousNonBlankCount;
	@Column(name="CURRENT_BLANK_COUNT")
	private Double currentBlankCount;
	@Column(name="CURRENT_NONBLANK_COUNT")
	private Double currentNonBlankCount;
	@Column(name="BLANK_DELTA_CHANGE")
	private Double blankDeltaChange;
	@Column(name="NON_BLANK_DELTA_CHANGE")
	private Double nonBlankDeltaChange;
	@Column(name="STATUS")
	private String status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMrdId() {
		return mrdId;
	}
	public void setMrdId(Integer mrdId) {
		this.mrdId = mrdId;
	}
	public String getLastRefreshTime() {
		return lastRefreshTime;
	}
	public void setLastRefreshTime(String lastRefreshTime) {
		this.lastRefreshTime = lastRefreshTime;
	}
	public String getMrdTableName() {
		return mrdTableName;
	}
	public void setMrdTableName(String mrdTableName) {
		this.mrdTableName = mrdTableName;
	}
	public String getMrdAttributeName() {
		return mrdAttributeName;
	}
	public void setMrdAttributeName(String mrdAttributeName) {
		this.mrdAttributeName = mrdAttributeName;
	}
	public Double getPreviousBlankCount() {
		return previousBlankCount;
	}
	public void setPreviousBlankCount(Double previousBlankCount) {
		this.previousBlankCount = previousBlankCount;
	}
	public Double getPreviousNonBlankCount() {
		return previousNonBlankCount;
	}
	public void setPreviousNonBlankCount(Double previousNonBlankCount) {
		this.previousNonBlankCount = previousNonBlankCount;
	}
	public Double getCurrentBlankCount() {
		return currentBlankCount;
	}
	public void setCurrentBlankCount(Double currentBlankCount) {
		this.currentBlankCount = currentBlankCount;
	}
	public Double getCurrentNonBlankCount() {
		return currentNonBlankCount;
	}
	public void setCurrentNonBlankCount(Double currentNonBlankCount) {
		this.currentNonBlankCount = currentNonBlankCount;
	}
	public Double getBlankDeltaChange() {
		return blankDeltaChange;
	}
	public void setBlankDeltaChange(Double blankDeltaChange) {
		this.blankDeltaChange = blankDeltaChange;
	}
	public Double getNonBlankDeltaChange() {
		return nonBlankDeltaChange;
	}
	public void setNonBlankDeltaChange(Double nonBlankDeltaChange) {
		this.nonBlankDeltaChange = nonBlankDeltaChange;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
