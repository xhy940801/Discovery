package com.discovery.service.user.dao.impl;

import org.hibernate.SessionFactory;

import com.discovery.service.user.dao.UserSecuInfoDAO;
import com.discovery.service.user.model.UserSecuInfo;

/**
 * 用户安全信息的DAO的hibernate实现
 * @author xiao.hy
 * @see com.discovery.service.user.dao.UserSecuInfoDAO
 */
public class UserSecuInfoDAOHibernateImpl implements UserSecuInfoDAO
{
	private SessionFactory sessionFactory;

	@Override
	public void save(UserSecuInfo userSecuInfo)
	{
		sessionFactory.getCurrentSession().save(userSecuInfo);
	}

	@Override
	public void update(UserSecuInfo userSecuInfo)
	{
		sessionFactory.getCurrentSession().update(userSecuInfo);
	}

	@Override
	public UserSecuInfo getById(int id)
	{
		return (UserSecuInfo) sessionFactory.getCurrentSession().
				get(UserSecuInfo.class, id);
	}

	@Override
	public UserSecuInfo getByUsername(String username)
	{
		return (UserSecuInfo) sessionFactory.getCurrentSession()
				.createQuery("from UserSecuInfo as u where u.email=?")
				.setString(0, username).uniqueResult();
	}
	
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

}
