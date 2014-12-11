package com.discovery.service.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.discovery.service.message.Message;
import com.discovery.service.picture.manager.PictureManager;
import com.discovery.service.push.manager.PushManager;
import com.opensymphony.xwork2.ActionContext;

public class PictureAction {

	PushManager pushManager;
	PictureManager pictureManager;
	
	HttpServletRequest request = ServletActionContext.getRequest();

	public String addPicture()
	{
		int fileId = this.getIntegerParam("fileId");
		int userId = this.getIntegerParam("userId");
		float longitude = this.getDoubleParam("longitude");
		float latitude = this.getDoubleParam("latitude");
		String remark = this.getStringParam("remark");

		Message msg = pictureManager.addPicture(fileId, userId, longitude, latitude, remark);
		ActionContext.getContext().put("msg", msg.toJSONMessage());
		return "success";
	}

	public String pushPictList(){
		Map<?, ?> map = ActionContext.getContext().getParameters();
		int userId = Integer.parseInt(((String[]) map.get("userId"))[0]);
		int offset = Integer.parseInt(((String[]) map.get("offset"))[0]);
		int count = Integer.parseInt(((String[]) map.get("count"))[0]);
		Message msg = pushManager.getPushPictures(userId,offset,count);
		ActionContext.getContext().put("msg", msg.toJSONMessage());
		return "success";
	}
	
	public String getPictureInfo(){
		Map<?, ?> map = ActionContext.getContext().getParameters();
		int pictureId = Integer.parseInt(((String[]) map.get("pictureId"))[0]);
		Message msg = pictureManager.getPicture(pictureId);
		ActionContext.getContext().put("msg", msg.toJSONMessage());
		return "success";
	}
	
	public PushManager getPushManager() {
		return pushManager;
	}

	public void setPushManager(PushManager pushManager) {
		this.pushManager = pushManager;
	}

	public void setPictureManager(PictureManager pictureManager) {
		this.pictureManager = pictureManager;
	}
		
}
