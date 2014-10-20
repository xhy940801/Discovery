package com.discovery.service.push.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.discovery.service.push.dao.PushRecordDAO;
import com.discovery.service.push.model.PushRecord;

/*
 * 图片的推送记录DAO接口的Hibernate实现
 * @author chong
 * @see com.discovery.service.push.dao.PushRecordDAO
 */
public class PushRecordDAOHibernateImpl implements PushRecordDAO {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void save(PushRecord pushRecord) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(pushRecord);
	}

	@Override
	public void update(PushRecord pushRecord) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(pushRecord);
	}

	@Override
	public void delete(int pictureId) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession()
			.createQuery("delete PushRecord as p where p.pictureId=?")
			.setInteger(0, pictureId).executeUpdate();
	}

	@Override
	public PushRecord getByPictureId(int pictureId) {
		// TODO Auto-generated method stub
		return (PushRecord)sessionFactory.getCurrentSession()
					.createQuery("from PushRecord as p where p.pictureId=?")
					.setInteger(0, pictureId).uniqueResult();
	}

}
