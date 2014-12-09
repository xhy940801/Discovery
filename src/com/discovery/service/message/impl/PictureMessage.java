package com.discovery.service.message.impl;

import java.util.List;

import com.discovery.service.message.Message;
import com.discovery.service.picture.model.PictureInfo;
import com.xiao.util.json.xjson.JArrayObj;
import com.xiao.util.json.xjson.JMapObj;

public class PictureMessage implements Message {

	private int msgCode;
	private List<PictureInfo> pictureInfos;
	
	public PictureMessage(int msgCode,List<PictureInfo> pictureInfos) {
		// TODO Auto-generated constructor stub
		this.msgCode = msgCode;
		this.pictureInfos = pictureInfos;
	}
	
	@Override
	public String toJSONMessage() {
		// TODO Auto-generated method stub
		JArrayObj jArrayObj = new JArrayObj();
		JMapObj jMapObj;
		for(PictureInfo pictureInfo:pictureInfos){
			jMapObj = new JMapObj();
			jMapObj.put("id", pictureInfo.getId());
			jMapObj.put("fileId", pictureInfo.getFileId());
			jMapObj.put("longitude", pictureInfo.getLongitude());
			jMapObj.put("latitude", pictureInfo.getLatitude());
			jMapObj.put("userId", pictureInfo.getUserSecuInfoId());
			jMapObj.put("totalLikeNum", pictureInfo.getTotalLikeNum());
			jMapObj.put("totalTemperature", pictureInfo.getTotalTemperature());
			jMapObj.put("createdTime", pictureInfo.getCreatedTime());
			jMapObj.put("remark", pictureInfo.getRemark());
			
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
