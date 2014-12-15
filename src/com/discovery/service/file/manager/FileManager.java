package com.discovery.service.file.manager;

import com.discovery.service.message.Message;

public interface FileManager
{
	public Message addFile(String name, String type, String content, String remark);
	public Message getFile(int id);
	public Message deleteFile(int id);
	public Message appendFile(int id, String content);
}
