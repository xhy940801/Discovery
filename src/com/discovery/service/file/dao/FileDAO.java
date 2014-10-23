package com.discovery.service.file.dao;

import com.discovery.service.file.model.File;

public interface FileDAO
{
	/**
	 * 新增文件
	 * @param file 文件
	 */
	public void save(File file);
	
	/**
	 * 通过id获取文件
	 * @param id 文件id
	 * @return 文件对象
	 */
	public File getById(int id);
	
	/**
	 * 通过id删除文件
	 * @param id 文件id
	 */
	public void delete(int id);
}
