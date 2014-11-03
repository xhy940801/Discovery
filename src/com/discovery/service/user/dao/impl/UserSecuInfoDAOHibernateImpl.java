package com.discovery.service.user.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
	public void save(UserSecuInfo userSecuInfo)
	{
		sessionFactory.getCurrentSession().save(userSecuInfo);
	}

	@Override
	@Transactional
	public void update(UserSecuInfo userSecuInfo)
	{
		sessionFactory.getCurrentSession().update(userSecuInfo);
	}

	@Override
	@Transactional
	public UserSecuInfo getById(int id)
	{
		return (UserSecuInfo) sessionFactory.getCurrentSession().
				get(UserSecuInfo.class, id);
	}

	@Override
	@Transactional
	public UserSecuInfo getByUsername(String username)
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from UserSecuInfo as u where u.email=?");
		query.setString(0, username);
		UserSecuInfo reInfo = (UserSecuInfo) query.uniqueResult();
				
		return reInfo;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

}
