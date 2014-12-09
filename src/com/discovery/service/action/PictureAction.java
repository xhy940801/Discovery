package com.discovery.service.action;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.discovery.service.message.Message;
import com.discovery.service.picture.manager.PictureManager;
import com.discovery.service.push.manager.PushManager;
import com.opensymphony.xwork2.ActionContext;
import com.xiao.util.json.xjson.JArrayObj;
import com.xiao.util.json.xjson.JSONDecodeException;

public class PictureAction {

	PushManager pushManager;
	PictureManager pictureManager;
	
	HttpServletRequest request = ServletActionContext.getRequest();
	
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
	
	public String getPictureInfoList(){
		Map<?, ?> map = ActionContext.getContext().getParameters();
		Message msg = pictureManager.getPictureList(
				idListJsonToList(((String[])map.get("pictureIdList"))[0]));
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
		
	private List<Integer> idListJsonToList(String json){
		boolean isContinute = false;
		String boxString = null;
		List<Integer> list = new LinkedList<Integer>();
		for(int i=0; i < json.length(); ++i){
			char c = json.charAt(i);
			if(c != ',' && c != '[' && c != ']'){
				boxString += c;
				isContinute = true;
			}else{
				if(isContinute){
					list.add(Integer.parseInt(boxString));
				}
				isContinute = false;
				boxString = "";
			}
		}
		return list;
	}
}
