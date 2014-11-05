package com.discovery.service.file.manager.impl;

import org.hibernate.HibernateException;

import com.discovery.service.file.dao.FileDAO;
import com.discovery.service.file.manager.FileManager;
import com.discovery.service.file.model.File;
import com.discovery.service.message.Message;
import com.discovery.service.message.impl.ErrorMessage;
import com.discovery.service.message.impl.FileMessage;
import com.discovery.service.message.impl.GeneralMessage;

public class FileManagerDefaultImpl implements FileManager
{
	private FileDAO fileDAO;

	@Override
	public Message addFile(String name, String type, byte[] data, String remark)
	{
		File file = new File();
		try
		{
			file.setName(name);
			file.setType(type);
			file.setContent(data);
			file.setRemark(remark);
			fileDAO.save(file);
		}
		catch (HibernateException e)
		{
			return new ErrorMessage(405010, null);
		}
		catch (Exception e)
		{
			return new ErrorMessage(505010, null);
		}
		catch (Throwable e)
		{
			return new ErrorMessage(705010, null);
		}
		return new GeneralMessage(0, file.getId());
	}

	@Override
	public Message getFile(int id)
	{
		File file;
		try
		{
			file = fileDAO.getById(id);
			if(file == null)
				return new ErrorMessage(605011, null);
		}
		catch (HibernateException e)
		{
			e.printStackTrace();
			return new ErrorMessage(405020, null);
		}
		catch (Exception e)
		{
			return new ErrorMessage(505020, null);
		}
		catch (Throwable e)
		{
			return new ErrorMessage(705020, null);
		}
		return new FileMessage(0, file);
	}

	@Override
	public Message deleteFile(int id)
	{
		try
		{
			fileDAO.delete(id);
		}
		catch (HibernateException e)
		{
			return new ErrorMessage(405020, null);
		}
		catch (Exception e)
		{
			return new ErrorMessage(505020, null);
		}
		catch (Throwable e)
		{
			return new ErrorMessage(705020, null);
		}
		return new GeneralMessage(0, null);
	}
	
	public void setFileDAO(FileDAO fileDAO)
	{
		this.fileDAO = fileDAO;
	}

}
