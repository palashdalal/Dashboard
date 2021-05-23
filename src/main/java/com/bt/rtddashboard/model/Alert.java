/**
 * 
 */
package com.bt.rtddashboard.model;

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
public class Alert {
	
	@Id
	@SequenceGenerator(name="alertSeq", sequenceName="SEQ_ALERT",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="alertSeq")
	private int id;
	private String alertDesc;
	private String alertType;
	private String alertDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAlertDesc() {
		return alertDesc;
	}
	public void setAlertDesc(String alertDesc) {
		this.alertDesc = alertDesc;
	}
	 
	public String getAlertType() {
		return alertType;
	}
	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}
	public String getAlertDate() {
		return alertDate;
	}
	public void setAlertDate(String alertDate) {
		this.alertDate = alertDate;
	}
	
	
}
