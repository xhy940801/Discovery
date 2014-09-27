package com.discovery.service.user.manager.impl;

import java.util.Date;

import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;

import com.discovery.service.user.dao.UserEsseInfoDAO;
import com.discovery.service.user.dao.UserSecuInfoDAO;
import com.discovery.service.user.manager.UserManager;
import com.discovery.service.user.message.Message;
import com.discovery.service.user.message.impl.ErrorMessage;
import com.discovery.service.user.message.impl.GeneralMessage;
import com.discovery.service.user.message.impl.UserSecuInfoMessage;
import com.discovery.service.user.model.UserEsseInfo;
import com.discovery.service.user.model.UserSecuInfo;
import com.discovery.service.util.AESUtil;
import com.discovery.service.util.MD5Util;
import com.discovery.service.util.StringVerify;

public class UserManagerDefaultImpl implements UserManager
{
	private UserEsseInfoDAO userEsseInfoDAO;
	private UserSecuInfoDAO userSecuInfoDAO;
	
	private String verificationString;
	private long timeLimit;
	
	public UserManagerDefaultImpl()
	{
		verificationString = "我写的。。。";
		timeLimit = 1000 * 60 * 10;
	}

	@Override
	public Message register(String email, String password)
	{
		if(!StringVerify.isEmail(email, 32))
			return new ErrorMessage(201011, null);
		if(!StringVerify.isStrongPassword(password, 6, 18))
			return new ErrorMessage(201012, null);
		if(userSecuInfoDAO.getByUsername(email) != null)
			return new ErrorMessage(601011, null);
		password = MD5Util.md5(password);
		if(password == null)
			return new ErrorMessage(501010, null);
		UserSecuInfo userSecuInfo;
		try
		{
			userSecuInfo = addUser(email, password);
		}
		catch (HibernateException e)
		{
			return new ErrorMessage(401010, null);
		}
		catch (Exception e)
		{
			return new ErrorMessage(501010, null);
		}
		catch (Throwable e)
		{
			return new ErrorMessage(701010, null);
		}
		return new UserSecuInfoMessage(0, userSecuInfo);
	}
	
	@Transactional
	public UserSecuInfo addUser(String email, String password)
	{
		UserSecuInfo userSecuInfo = new UserSecuInfo();
		userSecuInfo.setEmail(email);
		userSecuInfo.setPassword(password);
		userSecuInfo.setLastLoginTime(new Date());
		userSecuInfo.setRegistrationTime(new Date());
		userSecuInfoDAO.save(userSecuInfo);
		UserEsseInfo userEsseInfo = new UserEsseInfo();
		userEsseInfo.setUserSecuInfoId(userSecuInfo.getId());
		userEsseInfoDAO.save(userEsseInfo);
		return userSecuInfo;
	}

	@Override
	public Message login(String email, String password)
	{
		UserSecuInfo userSecuInfo;
		try
		{
			userSecuInfo = userSecuInfoDAO.getByUsername(email);
			if(userSecuInfo == null)
				return new ErrorMessage(301022, null);
			userSecuInfo.setLastLoginTime(new Date());
			userSecuInfoDAO.update(userSecuInfo);
		}
		catch (HibernateException e)
		{
			return new ErrorMessage(401020, null);
		}
		catch (Exception e)
		{
			return new ErrorMessage(501020, null);
		}
		catch (Throwable e)
		{
			return new ErrorMessage(701020, null);
		}
		if(!userSecuInfo.getPassword().equals(MD5Util.md5(password)))
			return new ErrorMessage(301021, null);
		return new UserSecuInfoMessage(0, userSecuInfo);
	}

	@Override
	public Message getResetPasswordKey(String email)
	{
		String key;
		try
		{
			key = AESUtil.encrypt(email + ";" + new Date().getTime() + ";" + verificationString);
		}
		catch (Exception e)
		{
			return new ErrorMessage(501030, null);
		}
		catch (Throwable e)
		{
			return new ErrorMessage(701030, null);
		}
		if(key == null)
			return new ErrorMessage(501030, null);
		return new GeneralMessage(0, key);
	}

	@Override
	public Message resetPassword(String key, String password)
	{
		String context;
		UserSecuInfo userSecuInfo;
		try
		{
			context = AESUtil.decrypt(key);
			if(!StringVerify.isStrongPassword(password, 6, 18))
				return new ErrorMessage(201042, null);
			if(context == null)
				return new ErrorMessage(501040, "context");
			String[] datas = context.split(";", 3);
			if(datas.length < 3 || !datas[2].equals(verificationString))
				return new ErrorMessage(301041, null);
			if(new Date().getTime() - Long.parseLong(datas[1]) > timeLimit)
				return new ErrorMessage(301042, null);
			password = MD5Util.md5(password);
			if(password == null)
				return new ErrorMessage(501040, "psd");
			userSecuInfo = updateUserPassword(datas[0], password);
		}
		catch (HibernateException e)
		{
			return new ErrorMessage(401040, null);
		}
		catch (Exception e)
		{
			return new ErrorMessage(501040, null);
		}
		catch (Throwable e)
		{
			return new ErrorMessage(701040, null);
		}
		return new UserSecuInfoMessage(0, userSecuInfo);
	}
	
	@Transactional
	private UserSecuInfo updateUserPassword(String email, String password)
	{
		UserSecuInfo userSecuInfo = userSecuInfoDAO.getByUsername(email);
		userSecuInfo.setPassword(password);
		userSecuInfoDAO.update(userSecuInfo);
		return userSecuInfo;
	}

	@Override
	public Message changePassword(int userId, String oldPassword,
			String newPassword)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message changeEsseInfo(int userId, String[] infos)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setUserEsseInfoDAO(UserEsseInfoDAO userEsseInfoDAO)
	{
		this.userEsseInfoDAO = userEsseInfoDAO;
	}

	public void setUserSecuInfoDAO(UserSecuInfoDAO userSecuInfoDAO)
	{
		this.userSecuInfoDAO = userSecuInfoDAO;
	}

}
