package com.discovery.service.user.manager.impl;

import java.util.Date;

import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;

import com.discovery.service.message.Message;
import com.discovery.service.message.impl.ErrorMessage;
import com.discovery.service.message.impl.GeneralMessage;
import com.discovery.service.message.impl.UserEsseInfoMessage;
import com.discovery.service.message.impl.UserSecuInfoMessage;
import com.discovery.service.user.dao.UserEsseInfoDAO;
import com.discovery.service.user.dao.UserSecuInfoDAO;
import com.discovery.service.user.manager.UserManager;
import com.discovery.service.user.model.UserEsseInfo;
import com.discovery.service.user.model.UserSecuInfo;
import com.discovery.service.util.AESUtil;
import com.discovery.service.util.MD5Util;
import com.discovery.service.util.StringVerify;

/**
 * 用户管理器的实现类
 * @author xiao.hy
 * @see com.discovery.service.user.manager.UserManager
 */
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
		//验证用户名或者密码是否符合规范
		if (!StringVerify.isEmail(email, 32))
			return new ErrorMessage(201011, null);
		if (!StringVerify.isStrongPassword(password, 6, 18))
			return new ErrorMessage(201012, null);
		if (userSecuInfoDAO.getByUsername(email) != null)
			return new ErrorMessage(601011, null);
		//对密码进行md5
		password = MD5Util.md5(password);
		if (password == null)
			return new ErrorMessage(501010, null);
		UserSecuInfo userSecuInfo;
		try
		{
			//调用成员函数addUser(这样设计的原因是,加了@Transactional注解的函数如果抛出运行时异常就会自动回滚
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
			//获取用户信息
			userSecuInfo = userSecuInfoDAO.getByUsername(email);
			if (userSecuInfo == null)
				return new ErrorMessage(301022, null);
			//更新时间(这里不单独用函数是因为这里的操作只有一次更新/添加,不一定需要事务)
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
		//密码错误就返回错误信息
		if (!userSecuInfo.getPassword().equals(MD5Util.md5(password)))
			return new ErrorMessage(301021, null);
		return new UserSecuInfoMessage(0, userSecuInfo);
	}

	@Override
	public Message getResetPasswordKey(String email)
	{
		String key;
		try
		{
			//对email,时间和一段特定的信息进行加密
			key = AESUtil.encrypt(email + ";" + new Date().getTime() + ";"
					+ verificationString);
		}
		catch (Exception e)
		{
			return new ErrorMessage(501030, null);
		}
		catch (Throwable e)
		{
			return new ErrorMessage(701030, null);
		}
		if (key == null)
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
			//解密
			context = AESUtil.decrypt(key);
			//对密码进行格式验证
			if (!StringVerify.isStrongPassword(password, 6, 18))
				return new ErrorMessage(201042, null);
			if (context == null)
				return new ErrorMessage(501040, "context");
			//以;为分割符,把解密的信息分成3个字符串
			String[] datas = context.split(";", 3);
			//看看是否是3个部分,并且查看特定的信息是否正确
			if (datas.length < 3 || !datas[2].equals(verificationString))
				return new ErrorMessage(301041, null);
			//查看是否超时
			if (new Date().getTime() - Long.parseLong(datas[1]) > timeLimit)
				return new ErrorMessage(301042, null);
			password = MD5Util.md5(password);
			if (password == null)
				return new ErrorMessage(501040, "psd");
			//更新信息
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
		//检查密码强度
		if (!StringVerify.isStrongPassword(newPassword, 6, 18))
			return new ErrorMessage(201052, null);
		UserSecuInfo userSecuInfo;
		try
		{
			userSecuInfo = userSecuInfoDAO.getById(userId);
			//查看旧密码是否相等
			if (!userSecuInfo.getPassword().equals(MD5Util.md5(oldPassword)))
				return new ErrorMessage(301051, null);
			userSecuInfo.setPassword(MD5Util.md5(newPassword));
			userSecuInfoDAO.update(userSecuInfo);
		}
		catch (HibernateException e)
		{
			return new ErrorMessage(401050, null);
		}
		catch (Exception e)
		{
			return new ErrorMessage(501050, null);
		}
		catch (Throwable e)
		{
			return new ErrorMessage(701050, null);
		}
		return new UserSecuInfoMessage(0, userSecuInfo);
	}

	@Override
	public Message changeEsseInfo(int userId, String[] infos)
	{
		UserEsseInfo userEsseInfo;
		int code;
		//检查用户信息是否合法,这个成员函数会返回错误码,如果没有错误,返回0
		if ((code = verifyUserEsseInfo(infos)) != 0)
			return new ErrorMessage(code, null);
		try
		{
			//设置信息,更新
			userEsseInfo = userEsseInfoDAO.getByUserSecuInfoId(userId);
			userEsseInfo.setNickname(infos[0]);
			userEsseInfo.setTel(infos[1]);
			userEsseInfo.setPhone(infos[2]);
			userEsseInfo.setAddress(infos[3]);
			userEsseInfoDAO.update(userEsseInfo);
		}
		catch (HibernateException e)
		{
			return new ErrorMessage(401060, null);
		}
		catch (Exception e)
		{
			return new ErrorMessage(501060, null);
		}
		catch (Throwable e)
		{
			return new ErrorMessage(701060, null);
		}
		return new UserEsseInfoMessage(0, userEsseInfo);
	}

	private int verifyUserEsseInfo(String[] infos)
	{
		if (!StringVerify.checkLength(infos[0], 1, 16))
			return 201061;
		if (!StringVerify.checkLength(infos[1], 0, 16))
			return 201062;
		if (!StringVerify.checkLength(infos[2], 0, 16))
			return 201063;
		if (!StringVerify.checkLength(infos[3], 0, 255))
			return 201064;
		return 0;
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
