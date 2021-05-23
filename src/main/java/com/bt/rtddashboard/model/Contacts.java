/**
 * 
 */
package com.bt.rtddashboard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author 609669080
 *
 */
@Entity
public class Contacts {
	@Id
	private int id;
	private String name;
	private String emailId;
	private String extnsn;
	private String timings;
	private String area;
	@Column(name="ESCAL_LEAD")
	private String escalationLead;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getExtnsn() {
		return extnsn;
	}
	public void setExtnsn(String extnsn) {
		this.extnsn = extnsn;
	}
	public String getTimings() {
		return timings;
	}
	public void setTimings(String timings) {
		this.timings = timings;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getEscalationLead() {
		return escalationLead;
	}
	public void setEscalationLead(String escalationLead) {
		this.escalationLead = escalationLead;
	}
	
	
}
