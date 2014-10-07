package com.discovery.service.message.impl;

import java.util.List;

import com.discovery.service.friend.model.RequestForFriend;
import com.discovery.service.message.Message;

public class RequestListMessage implements Message {

	private int msgCode;
	private List<RequestForFriend> list;
	
	public RequestListMessage(int msgCode,List<RequestForFriend> list) {
		// TODO Auto-generated constructor stub
		this.msgCode = msgCode;
		this.list = list;
	}
	
	@Override
	public String toJSONMessage() {
		// TODO Auto-generated method stub
		String str = "[";
		for(RequestForFriend r : list){
			str += "{";
			str += "\"id\":\"" + r.getId() + "\",";
			str += "\"sponsorId\":\"" + r.getSponsorId() + "\",";
			str += "\"receiverId\":\"" + r.getReceiverId() + "\",";
			str += "\"requestTime\":\"" + r.getRequestTime() + "\",";
			str += "\"handleTime\":\"" + r.getHandleTime() + "\",";
			str += "\"status\":\"" + r.getStatus() + "\"";
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
