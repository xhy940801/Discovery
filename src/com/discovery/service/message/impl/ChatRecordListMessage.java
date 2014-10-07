package com.discovery.service.message.impl;

import java.util.List;

import com.discovery.service.friend.model.ChatRecord;
import com.discovery.service.friend.model.RequestForFriend;
import com.discovery.service.message.Message;

public class ChatRecordListMessage implements Message {
	
	private int msgCode;
	private List<ChatRecord> list;
	
	public ChatRecordListMessage(int msgCode,List<ChatRecord> list) {
		// TODO Auto-generated constructor stub
		this.msgCode = msgCode;
		this.list = list;
	}

	@Override
	public String toJSONMessage() {
		// TODO Auto-generated method stub
		String str = "[";
		for(ChatRecord c : list){
			str += "{";
			str += "\"id\":\"" + c.getId() + "\",";
			str += "\"senderId\":\"" + c.getSenderId() + "\",";
			str += "\"receiverId\":\"" + c.getReceiverId() + "\",";
			str += "\"content\":\"" + c.getContent() + "\",";
			str += "\"sendTime\":\"" + c.getSendTime() + "\"";
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
