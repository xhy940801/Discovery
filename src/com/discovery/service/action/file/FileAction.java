package com.discovery.service.action.file;

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

	public void setFileManager(FileManager fileManager)
	{
		this.fileManager = fileManager;
	}
}
