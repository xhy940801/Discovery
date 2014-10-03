package com.discovery.service.friend.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.discovery.service.friend.dao.RelationOfFriendDAO;
import com.discovery.service.friend.model.RelationOfFriend;

/*
 * 好友关系记录的Hibernate实现
 * @author chong
 */
public class RelationOfFriendDAOHibernateImpl implements RelationOfFriendDAO {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public void save(RelationOfFriend relationOfFriend) {
		sessionFactory.getCurrentSession().save(relationOfFriend);
	}

	@Override
	@Transactional
	public void delete(int sponsorId, int receiverId) {
		sessionFactory.getCurrentSession()
			.createQuery("delete RelationOfFriend as r where r.sponsorId=? and r.receiverId=?")
			.setInteger(0,sponsorId).setInteger(1,receiverId).executeUpdate();

	}

	@Override
	public boolean checkout(int sponsorId, int receiverId) {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession()
			.createQuery("from RelationOfFriend as r where r.sponsorId=? and r.receiverId=?");
		query.setInteger(0,sponsorId).setInteger(1,receiverId);
		if(query.list().size() > 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List<RelationOfFriend> getFriendsList(int id) {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession()
			.createQuery("from RelationOfFriend as r where r.sponsorId=?");
		query.setInteger(0,id);
		List<RelationOfFriend> re = query.list();
		return re;
	}

}