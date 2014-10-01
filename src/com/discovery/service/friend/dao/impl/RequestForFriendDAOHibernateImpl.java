package com.discovery.service.friend.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.discovery.service.friend.dao.RequestForFriendDAO;
import com.discovery.service.friend.model.RelationOfFriend;
import com.discovery.service.friend.model.RequestForFriend;

public class RequestForFriendDAOHibernateImpl implements RequestForFriendDAO{

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void save(RequestForFriend requestForFriend) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(requestForFriend);
		
	}

	@Override
	public void delete(int sponsorId, int receiverId) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession()
			.createQuery("delete RequestForFriend as r where r.sponsorId=? and r.receiverId=?")
			.setInteger(0,sponsorId).setInteger(1,receiverId).executeUpdate();
	}

	@Override
	public boolean checkout(int sponsorId, int receiverId) {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession()
				.createQuery("from RequestForFriend as r where r.sponsorId=? and r.receiverId=?");
		query.setInteger(0,sponsorId).setInteger(1,receiverId);
		if(query.list().size() > 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List<RequestForFriend> getToRequset(int id) {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession()
				.createQuery("from RequestForFriend as r where r.sponsorId=?");
		query.setInteger(0,id);
		List<RequestForFriend> re = query.list();
		return re;
	}

	@Override
	public List<RequestForFriend> getFromRequest(int id) {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession()
				.createQuery("from RequestForFriend as r where r.receiverId=?");
			query.setInteger(0,id);
			List<RequestForFriend> re = query.list();
			return re;
	}

}
