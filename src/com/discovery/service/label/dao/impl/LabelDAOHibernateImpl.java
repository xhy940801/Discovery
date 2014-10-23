package com.discovery.service.label.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.discovery.service.label.dao.LabelDAO;
import com.discovery.service.label.model.Label;

public class LabelDAOHibernateImpl implements LabelDAO
{
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public void save(Label label)
	{
		sessionFactory.getCurrentSession().save(label);
	}

	@Override
	@Transactional
	public void update(Label label)
	{
		sessionFactory.getCurrentSession().update(label);
	}

	@Override
	@Transactional(readOnly = true)
	public Label getById(int id)
	{
		return (Label) sessionFactory.getCurrentSession().get(Label.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Label> getByLink(int link)
	{
		return sessionFactory.getCurrentSession()
				.createQuery("from Label as l where l.link=?")
				.setInteger(0, link).list();
	}

	@Override
	public Label getByName(String name)
	{
		return (Label) sessionFactory.getCurrentSession()
				.createQuery("from Label as l where l.name=?")
				.setString(0, name).uniqueResult();
	}
	
	@Override
	public List<Integer> getLinks(int offset, int length)
	{
		@SuppressWarnings("unchecked")
		List<Integer> l = sessionFactory.getCurrentSession()
				.createQuery("select l.link from Label as l").list();
		return l;
	}

	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
}
