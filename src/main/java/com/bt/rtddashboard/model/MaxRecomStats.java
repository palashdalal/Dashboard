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
@Table(name="MAX_RECOM_STATS")
public class MaxRecomStats {
	
	@Id
	private Integer ilsId;
	
	@Column(name="MAX_RECOMS_PER_SEC_1")
	private Integer rank1;
	
	@Column(name="MAX_RECOMS_PER_SEC_2")
	private Integer rank2;
	
	@Column(name="MAX_RECOMS_PER_SEC_3")
	private Integer rank3;
	
	@Column(name="MAX_RECOMS_PER_SEC_4")
	private Integer rank4;
	
	@Column(name="MAX_RECOMS_PER_SEC_5")
	private Integer rank5;

	public Integer getIlsId() {
		return ilsId;
	}

	public void setIlsId(Integer ilsId) {
		this.ilsId = ilsId;
	}

	public Integer getRank1() {
		return rank1;
	}

	public void setRank1(Integer rank1) {
		this.rank1 = rank1;
	}

	public Integer getRank2() {
		return rank2;
	}

	public void setRank2(Integer rank2) {
		this.rank2 = rank2;
	}

	public Integer getRank3() {
		return rank3;
	}

	public void setRank3(Integer rank3) {
		this.rank3 = rank3;
	}

	public Integer getRank4() {
		return rank4;
	}

	public void setRank4(Integer rank4) {
		this.rank4 = rank4;
	}

	public Integer getRank5() {
		return rank5;
	}

	public void setRank5(Integer rank5) {
		this.rank5 = rank5;
	}

	
}
