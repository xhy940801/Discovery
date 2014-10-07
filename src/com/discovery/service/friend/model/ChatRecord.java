package com.discovery.service.friend.model;

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
@Table(name = "chat_records")
public class ChatRecord {

	private long id;
	private int senderId;
	private int receiverId;
	private String content;
	private Date sendTime;
	
	public ChatRecord(){}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "sender_id")
	public int getSenderId() {
		return senderId;
	}
	
	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}
	
	@Column(name = "receiver_id")
	public int getReceiverId() {
		return receiverId;
	}
	
	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}
	
	@Column(name = "content",length = 200)
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name = "send_time")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getSendTime() {
		return sendTime;
	}
	
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	
	
}
