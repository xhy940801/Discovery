package com.discovery.service.file.manager;

import com.discovery.service.message.Message;

public interface FileManager
{
	public Message addFile(String name, String type, byte[] data, String remark);
	public Message getFile(int id);
	public Message deleteFile(int id);
}
