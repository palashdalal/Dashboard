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
@Table(name="STANDARD_ATTRIBUTE_MONITOR")
public class StandardAttributeMaster {
	
	@Id
	@Column(name="ATT_ID")
	private Integer attrId;
	
	@Column(name="ATT_NAME")
	private String attName;
	
	@Column(name="ATT_INSIGHT")
	private String attInsight;
	
	@Column(name="JOB_TYPE")
	private String jobType;
	
	@Column(name="LAST_REFRESH_COUNT")
	private Double lastRefreshCount;
	
	@Column(name="CURRENT_REFRESH_COUNT")
	private Double currentRefreshCount;
	
	@Column(name="DELTA_PERCENTAGE")
	private Double deltaPercentage;
	
	@Column(name="DELTA_INDICATOR")
	private String deltaIndicator;
	
	@Column(name="VA_INSIGHT_STATUS")
	private String insightStatus;
	
	@Column(name="REC_REFRESH_TS")
	private String lastRefreshDate;
	
	public Integer getAttrId() {
		return attrId;
	}
	public void setAttrId(Integer attrId) {
		this.attrId = attrId;
	}
	public String getAttName() {
		return attName;
	}
	public void setAttName(String attName) {
		this.attName = attName;
	}
	public String getAttInsight() {
		return attInsight;
	}
	public void setAttInsight(String attInsight) {
		this.attInsight = attInsight;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public Double getLastRefreshCount() {
		return lastRefreshCount;
	}
	public void setLastRefreshCount(Double lastRefreshCount) {
		this.lastRefreshCount = lastRefreshCount;
	}
	public Double getCurrentRefreshCount() {
		return currentRefreshCount;
	}
	public void setCurrentRefreshCount(Double currentRefreshCount) {
		this.currentRefreshCount = currentRefreshCount;
	}
	public Double getDeltaPercentage() {
		return deltaPercentage;
	}
	public void setDeltaPercentage(Double deltaPercentage) {
		this.deltaPercentage = deltaPercentage;
	}
	public String getDeltaIndicator() {
		return deltaIndicator;
	}
	public void setDeltaIndicator(String deltaIndicator) {
		this.deltaIndicator = deltaIndicator;
	}
	public String getInsightStatus() {
		return insightStatus;
	}
	public void setInsightStatus(String insightStatus) {
		this.insightStatus = insightStatus;
	}
	public String getLastRefreshDate() {
		return lastRefreshDate;
	}
	public void setLastRefreshDate(String lastRefreshDate) {
		this.lastRefreshDate = lastRefreshDate;
	}
	
	
}
