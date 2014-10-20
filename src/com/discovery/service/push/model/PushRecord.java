package com.discovery.service.push.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "push_records")
public class PushRecord {

	private int id;
	private int pictureId;
	private int allCount;
	private int remainCount;
	private Date updateTime;
	private boolean status;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="picture_id")
	public int getPictureId() {
		return pictureId;
	}
	
	public void setPictureId(int pictureId) {
		this.pictureId = pictureId;
	}
	
	@Column(name="all_count")
	public int getAllCount() {
		return allCount;
	}
	
	public void setAllCount(int allCount) {
		this.allCount = allCount;
	}
	
	@Column(name="remain_count")
	public int getRemainCount() {
		return remainCount;
	}
	
	public void setRemainCount(int remainCount) {
		this.remainCount = remainCount;
	}
	
	@Column(name="update_time")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdateTime() {
		return updateTime;
	}
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@Column(name="status")
	public boolean getStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
