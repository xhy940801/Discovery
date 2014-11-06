package com.discovery.service.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.discovery.service.message.Message;
import com.discovery.service.push.manager.PushManager;
import com.discovery.service.user.manager.UserManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {
	
	UserManager userManager;
	
	HttpServletRequest request = ServletActionContext.getRequest();

	public String login(){
		Map<?, ?> map = ActionContext.getContext().getParameters();
		String username = ((String[]) map.get("email"))[0];
		String password = ((String[]) map.get("password"))[0];
		Message msg = userManager.login(username, password);
		ActionContext.getContext().put("UserSecuInfo", msg.toJSONMessage());
		
		return "success";
	}
	
	public String getEsseInfo(){
		Map<?, ?> map = ActionContext.getContext().getParameters();
		int userId = Integer.parseInt(((String[]) map.get("userId"))[0]);
		Message msg = userManager.getEsseInfo(userId);
		ActionContext.getContext().put("UserEsseInfo", msg.toJSONMessage());
		return "success";
	}

	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	
}
