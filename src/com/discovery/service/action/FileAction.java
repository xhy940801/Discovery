package com.discovery.service.action;

import java.util.Map;

import com.discovery.service.file.manager.FileManager;
import com.discovery.service.message.Message;
import com.opensymphony.xwork2.ActionContext;

public class FileAction
{
	private FileManager fileManager;
	
	public String getBase64()
	{
		Map<?, ?> map = ActionContext.getContext().getParameters();
		String id = ((String[])map.get("id"))[0];
		Message msg = fileManager.getFile((int) Integer.parseInt(id));
		ActionContext.getContext().put("msg", msg.toJSONMessage());
		return "success";
	}
	
	public String saveFile()
	{
		Map<?, ?> map = ActionContext.getContext().getParameters();
		String content = ((String[])map.get("content"))[0];
		String type = ((String[])map.get("type"))[0];
		String name = ((String[])map.get("name"))[0];
		String remark = ((String[])map.get("remark"))[0];
		Message msg = fileManager.addFile(name, type, content, remark);
		ActionContext.getContext().put("msg", msg.toJSONMessage());
		return "success";
	}
	
	public String appendFile()
	{
		Map<?, ?> map = ActionContext.getContext().getParameters();
		String content = ((String[])map.get("content"))[0];
		int id = Integer.valueOf(((String[])map.get("type"))[0]);
		Message msg = fileManager.appendFile(id, content);
		ActionContext.getContext().put("msg", msg.toJSONMessage());
		return "success";
	}

	public void setFileManager(FileManager fileManager)
	{
		this.fileManager = fileManager;
	}
}
