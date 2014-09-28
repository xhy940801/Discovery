package com.discovery.service.user.message.impl;

import com.discovery.service.user.message.Message;
import com.discovery.service.user.model.UserSecuInfo;

public class UserSecuInfoMessage implements Message
{
	private int msgCode;
	private UserSecuInfo userSecuInfo;

	public UserSecuInfoMessage(int msgCode, UserSecuInfo userSecuInfo)
	{
		this.msgCode = msgCode;
		this.userSecuInfo = userSecuInfo;
	}

	@Override
	public String toJSONMessage()
	{
		String msg = "{id:" + userSecuInfo.getId() + ",email:\""
				+ userSecuInfo.getEmail() + "\",lastLoginTime:\""
				+ userSecuInfo.getLastLoginTime() + "\",registionTime:\""
				+ userSecuInfo.getRegistrationTime() + "\"}";
		return "{code:" + msgCode + ",msg:" + msg + "}";
	}

	@Override
	public int getMsgCode()
	{
		return msgCode;
	}

}
