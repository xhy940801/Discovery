package com.discovery.service.friend.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.discovery.service.friend.dao.ChatRecordDAO;
import com.discovery.service.friend.model.ChatRecord;

public class ChatRecordDAOHibernateImpl implements ChatRecordDAO{

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public void save(ChatRecord chatRecord) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(chatRecord);	
	}

	@Override
	@Transactional
	public List<ChatRecord> getChatRecord(int senderId, int receiverId,int count) {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession()
						.createQuery("from ChatRecord as c where (c.senderId=?1 and c.receiverId=?2) or (c.senderId=?2 and c.receiverId=?1)");
		query.setInteger("1", senderId);
		query.setInteger("2", receiverId);
		query.setFirstResult(0);
		query.setMaxResults(count);
		List<ChatRecord> re = query.list();
		return re;
	}

}
