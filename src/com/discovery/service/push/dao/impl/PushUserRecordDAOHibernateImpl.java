package com.discovery.service.push.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.discovery.service.push.dao.PushUserRecordDAO;
import com.discovery.service.push.model.PushUserRecord;

public class PushUserRecordDAOHibernateImpl implements PushUserRecordDAO {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public void save(PushUserRecord pushUserRecord) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(pushUserRecord);
	}

	@Override
	@Transactional
	public void update(PushUserRecord pushUserRecord) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(pushUserRecord);
	}

	@Override
	@Transactional
	public void deleteByPictureId(int pictureId) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession()
			.createQuery("delete PushUserRecord as p where p.pictureId=?")
			.setInteger(0, pictureId).executeUpdate();
	}

	@Override
	@Transactional
	public void deleteByUserId(int userId) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession()
			.createQuery("delete PushUserRecord as p where p.userId=?")
			.setInteger(0, userId).executeUpdate();
	}

	@Override
	@Transactional
	public PushUserRecord getById(int pictureId, int userId) {
		// TODO Auto-generated method stub
		return (PushUserRecord)sessionFactory.getCurrentSession()
				.createQuery("from PushUserRecord as p where p.pictureId=? and p.userId=?")
				.setInteger(0, pictureId).setInteger(1, userId).uniqueResult();
	}

	@Override
	@Transactional
	public List<PushUserRecord> getListByPictureId(int pictureId) {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession()
						.createQuery("from PushUserRecord as p where p.pictureId=?");
		query.setInteger(0, pictureId);
		@SuppressWarnings("unchecked")
		List<PushUserRecord> re = query.list();
		return re;
	}

	@Override
	@Transactional
	public List<PushUserRecord> getListByUserId(int userId,int offset,int count) {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession()
				.createQuery("from PushUserRecord as p where p.userId=? and p.status!=? order by p.pushTime DESC");
		query.setInteger(0, userId);
		query.setInteger(1, 2);
		//query.setFirstResult(offset);
		//query.setMaxResults(count);
		@SuppressWarnings("unchecked")
		List<PushUserRecord> re = query.list();
		if(offset >= re.size()){
			return null;
		}
		if(offset + count > re.size()){
			re = re.subList(offset, re.size());
		}else{
			re = re.subList(offset, offset+count);
		}
		return re;
	}

}
