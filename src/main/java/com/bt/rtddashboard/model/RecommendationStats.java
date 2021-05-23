package com.bt.rtddashboard.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="RECOM_STATS")
public class RecommendationStats {

	@Id
	private Integer id;

	private Integer ilsId;
	
	@Column(name="SESSION_DATE")
	private String sessionDate;
	
	@Column(name="TOT_REQUEST_COUNT")
	private Integer totReqCount;
	
	@Column(name="TOT_RECOM_COUNT")
	private Integer totRecomCount;
	
	@Column(name="AVG_REQ_SECS")
	private Double avgReqPerSec;
	
	@Column(name="AVG_REQ_MIN")
	private Double avgReqPerMin;
	
	@Column(name="AVG_REQ_HOUR")
	private Double avgReqPerHour;
	
	@Column(name="MAX_REQ_SEC")
	private Integer maxReqperSec;
	
	@Column(name="EDW_CREATE_DATE")
	private Date edwCreatedDate;
	
	@Column(name="EDW_UPDATE_DATE")
	private Date edwUpdatedDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIlsId() {
		return ilsId;
	}

	public void setIlsId(Integer ilsId) {
		this.ilsId = ilsId;
	}

	public String getSessionDate() {
		return sessionDate;
	}

	public void setSessionDate(String sessionDate) {
		this.sessionDate = sessionDate;
	}

	public Integer getTotReqCount() {
		return totReqCount;
	}

	public void setTotReqCount(Integer totReqCount) {
		this.totReqCount = totReqCount;
	}

	public Integer getTotRecomCount() {
		return totRecomCount;
	}

	public void setTotRecomCount(Integer totRecomCount) {
		this.totRecomCount = totRecomCount;
	}

	public Double getAvgReqPerSec() {
		return avgReqPerSec;
	}

	public void setAvgReqPerSec(Double avgReqPerSec) {
		this.avgReqPerSec = avgReqPerSec;
	}

	public Double getAvgReqPerMin() {
		return avgReqPerMin;
	}

	public void setAvgReqPerMin(Double avgReqPerMin) {
		this.avgReqPerMin = avgReqPerMin;
	}

	public Double getAvgReqPerHour() {
		return avgReqPerHour;
	}

	public void setAvgReqPerHour(Double avgReqPerHour) {
		this.avgReqPerHour = avgReqPerHour;
	}

	public Integer getMaxReqperSec() {
		return maxReqperSec;
	}

	public void setMaxReqperSec(Integer maxReqperSec) {
		this.maxReqperSec = maxReqperSec;
	}

	public Date getEdwCreatedDate() {
		return edwCreatedDate;
	}

	public void setEdwCreatedDate(Date edwCreatedDate) {
		this.edwCreatedDate = edwCreatedDate;
	}

	public Date getEdwUpdatedDate() {
		return edwUpdatedDate;
	}

	public void setEdwUpdatedDate(Date edwUpdatedDate) {
		this.edwUpdatedDate = edwUpdatedDate;
	}
	
	
}
