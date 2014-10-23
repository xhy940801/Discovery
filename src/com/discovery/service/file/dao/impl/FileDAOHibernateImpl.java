package com.discovery.service.file.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.discovery.service.file.dao.FileDAO;
import com.discovery.service.file.model.File;

public class FileDAOHibernateImpl implements FileDAO
{
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public void save(File file)
	{
		sessionFactory.getCurrentSession().save(file);
	}

	@Override
	public File getById(int id)
	{
		return (File) sessionFactory.getCurrentSession().get(File.class, id);
	}

	@Override
	@Transactional
	public void delete(int id)
	{
		sessionFactory.getCurrentSession()
				.createQuery("delete File as f where id=?").setInteger(0, id);
	}

	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

}
