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
@Table(name = "push_user_records")
public class PushUserRecord {

	private int id;
	private int pictureId;
	private int userId;
	private Date pushTime;
	private byte status;
	
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
	
	@Column(name="user_id")
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	@Column(name="push_time")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getPushTime() {
		return pushTime;
	}
	
	public void setPushTime(Date pushTime) {
		this.pushTime = pushTime;
	}
	
	@Column(name="status")
	public byte getStatus() {
		return status;
	}
	
	public void setStatus(byte status) {
		this.status = status;
	}

}
