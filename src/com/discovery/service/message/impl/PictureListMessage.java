package com.discovery.service.message.impl;

import java.util.List;

import com.discovery.service.message.Message;
import com.discovery.service.push.model.PushUserRecord;
import com.xiao.util.json.xjson.JArrayObj;
import com.xiao.util.json.xjson.JMapObj;

public class PictureListMessage implements Message {

	private int msgCode;
	private List<PushUserRecord> list;
	
	public PictureListMessage(int msgCode,List<PushUserRecord> list) {
		this.msgCode = msgCode;
		this.list = list;
	}
	
	@Override
	public String toJSONMessage() {
		// TODO Auto-generated method stub
		JArrayObj jArrayObj = new JArrayObj();
		JMapObj jMapObj;
		for(PushUserRecord pur : list){
			jMapObj = new JMapObj();
			jMapObj.put("pictureId", pur.getPictureId());
			jMapObj.put("pushTime", pur.getPushTime());
			jArrayObj.add(jMapObj);
		}
		JMapObj jsonObj = new JMapObj();
		jsonObj.put("code", msgCode);
		jsonObj.put("msg", jArrayObj);
		return jsonObj.toJSONString();
	}

	@Override
	public int getMsgCode() {
		// TODO Auto-generated method stub
		return this.msgCode;
	}

}
