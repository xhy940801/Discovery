package com.discovery.service.message.impl;

import sun.misc.BASE64Encoder;

import com.discovery.service.file.model.File;
import com.discovery.service.message.Message;
import com.xiao.util.json.xjson.JMapObj;

public class FileMessage implements Message
{
	private int msgCode;
	private File file;
	
	public FileMessage(int msgCode, File file)
	{
		this.msgCode = msgCode;
		this.file = file;
	}

	@Override
	public String toJSONMessage()
	{
		BASE64Encoder base64Encoder = new BASE64Encoder();
		JMapObj jMapObj = new JMapObj();
		jMapObj.put("id", file.getId());
		jMapObj.put("name", file.getName());
		jMapObj.put("type", file.getType());
		jMapObj.put("remark", file.getRemark());
		jMapObj.put("content", base64Encoder.encode(file.getContent()));
		JMapObj jsonObj = new JMapObj();
		jsonObj.put("code", msgCode);
		jsonObj.put("msg", jMapObj);
		return jsonObj.toJSONString();
	}

	@Override
	public int getMsgCode()
	{
		return msgCode;
	}

}
