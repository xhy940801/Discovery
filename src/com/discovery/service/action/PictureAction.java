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

public class PictureAction {

	PushManager pushManager;
	PictureManager pictureManager;
	
	HttpServletRequest request = ServletActionContext.getRequest();

	public String addPicture()
	{
		int fileId = this.getIntegerParam("fileId");
		int userId = this.getIntegerParam("userId");
		float longitude = (float) this.getDoubleParam("longitude");
		float latitude = (float) this.getDoubleParam("latitude");
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
	
	private String getParam(String key)
	{
		Map<?, ?> map = ActionContext.getContext().getParameters();
		String[] data = (String[]) map.get(key);
		return data[0];
	}
	
	private int getIntegerParam(String key)
	{
		return Integer.parseInt(getParam(key));
	}
	
	private double getDoubleParam(String key)
	{
		return Double.parseDouble(getParam(key));
	}
	
	private String getStringParam(String key)
	{
		return getParam(key);
	}
}
