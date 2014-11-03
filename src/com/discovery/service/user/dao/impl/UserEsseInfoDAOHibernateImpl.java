package com.discovery.service.user.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.discovery.service.user.dao.UserEsseInfoDAO;
import com.discovery.service.user.model.UserEsseInfo;

/**
 * 用户基本信息的DAO的hibernate实现
 * @author xiao.hy
 * @see com.discovery.service.user.dao.UserEsseINfoDAO
 */
public class UserEsseInfoDAOHibernateImpl implements UserEsseInfoDAO
{
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public void save(UserEsseInfo userEsseInfo)
	{
		sessionFactory.getCurrentSession().save(userEsseInfo);
	}

	@Override
	@Transactional
	public void update(UserEsseInfo userEsseInfo)
	{
		sessionFactory.getCurrentSession().update(userEsseInfo);
	}

	@Override
	@Transactional
	public UserEsseInfo getById(int id)
	{
		return (UserEsseInfo) sessionFactory.getCurrentSession().
				get(UserEsseInfo.class, id);
	}

	@Override
	@Transactional
	public UserEsseInfo getByUserSecuInfoId(int id)
	{
		return (UserEsseInfo) sessionFactory.getCurrentSession()
				.createQuery("from UserEsseInfo as u where u.userSecuInfoId=?")
				.setInteger(0, id).uniqueResult();
	}
	
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

}
