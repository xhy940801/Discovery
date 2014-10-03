package com.discovery.service.label.dao.impl;

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

	@Override
	@Transactional(readOnly = true)
	public Label getByLink(int link)
	{
		return (Label) sessionFactory.getCurrentSession()
				.createQuery("from Label as l where l.link=?")
				.setInteger(0, link).uniqueResult();
	}

	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

}
