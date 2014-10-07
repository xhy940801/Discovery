package com.discovery.service.message.impl;

import java.util.List;

import com.discovery.service.friend.model.RelationOfFriend;
import com.discovery.service.message.Message;

public class RelationListMessage implements Message {

	private int msgCode;
	private List<RelationOfFriend> list;
	
	public RelationListMessage(int msgCode,List<RelationOfFriend> list) {
		// TODO Auto-generated constructor stub
		this.msgCode = msgCode;
		this.list = list;
	}
	
	@Override
	public String toJSONMessage() {
		// TODO Auto-generated method stub
		String str = "[";
		for(RelationOfFriend r : list){
			str += "{";
			str += "\"id\":\"" + r.getId() + "\",";
			str += "\"sponsorId\":\"" + r.getSponsorId() + "\",";
			str += "\"receiverId\":\"" + r.getReceiverId() + "\"";
			str += "}";
			str += ",";
		}
		str = str.substring(0,str.length()-1);
		str += "]";
		return "{\"code\":\"" + msgCode + "\",\"msg\":" + str + "}";
	}

	@Override
	public int getMsgCode() {
		// TODO Auto-generated method stub
		return this.msgCode;
	}

}
