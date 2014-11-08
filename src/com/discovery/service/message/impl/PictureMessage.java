package com.discovery.service.message.impl;

import com.discovery.service.message.Message;
import com.discovery.service.picture.model.PictureInfo;
import com.xiao.util.json.xjson.JMapObj;

public class PictureMessage implements Message {

	private int msgCode;
	private PictureInfo pictureInfo;
	
	public PictureMessage(int msgCode,PictureInfo pictureInfo) {
		// TODO Auto-generated constructor stub
		this.msgCode = msgCode;
		this.pictureInfo = pictureInfo;
	}
	
	@Override
	public String toJSONMessage() {
		// TODO Auto-generated method stub
		JMapObj jMapObj = new JMapObj();
		jMapObj.put("id", pictureInfo.getId());
		jMapObj.put("fileId", pictureInfo.getFileId());
		jMapObj.put("longitude", pictureInfo.getLongitude());
		jMapObj.put("latitude", pictureInfo.getLatitude());
		jMapObj.put("userId", pictureInfo.getUserSecuInfoId());
		jMapObj.put("totalLikeNum", pictureInfo.getTotalLikeNum());
		jMapObj.put("totalTemperature", pictureInfo.getTotalTemperature());
		jMapObj.put("createdTime", pictureInfo.getCreatedTime());
		jMapObj.put("remark", pictureInfo.getRemark());
		JMapObj jsonObj = new JMapObj();
		jsonObj.put("code", msgCode);
		jsonObj.put("msg", jMapObj);
		return jsonObj.toJSONString();
	}

	@Override
	public int getMsgCode() {
		// TODO Auto-generated method stub
		return this.msgCode;
	}

}
