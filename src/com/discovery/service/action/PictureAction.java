package com.discovery.service.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.discovery.service.message.Message;
import com.discovery.service.push.manager.PushManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class PictureAction extends ActionSupport {

	PushManager pushManager;
	
	HttpServletRequest request = ServletActionContext.getRequest();
	
	public String pushPictList(){
		Map<?, ?> map = ActionContext.getContext().getParameters();
		int userId = Integer.parseInt(((String[]) map.get("userId"))[0]);
		Message msg = pushManager.getPushPictures(userId);
		ActionContext.getContext().put("msg", msg.toJSONMessage());
		return "success";
	}
	
	public PushManager getPushManager() {
		return pushManager;
	}

	public void setPushManager(PushManager pushManager) {
		this.pushManager = pushManager;
	}
		
}
