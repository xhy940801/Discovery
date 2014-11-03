package com.discovery.service.message.impl;

import com.discovery.service.message.Message;
import com.discovery.service.user.model.UserEsseInfo;

public class UserEsseInfoMessage implements Message
{
	private int msgCode;
	private UserEsseInfo userEsseInfo;
	
	public UserEsseInfoMessage(int msgCode, UserEsseInfo userEsseInfo)
	{
		this.msgCode = msgCode;
		this.userEsseInfo = userEsseInfo;
	}

	@Override
	public String toJSONMessage()
	{
		String msg = "{\"userSecuInfoId\":" + userEsseInfo.getUserSecuInfoId() + ",\"nickname\":\""
				+ userEsseInfo.getNickname() + "\",\"tel\":\""
				+ userEsseInfo.getTel() + "\",\"phone\":\""
				+ userEsseInfo.getPhone() + "\",\"address\":\""
				+ userEsseInfo.getAddress() + "\"}";
		return "{\"code\":" + msgCode + ",\"msg\":" + msg + "}";
	}

	@Override
	public int getMsgCode()
	{
		return msgCode;
	}

}
