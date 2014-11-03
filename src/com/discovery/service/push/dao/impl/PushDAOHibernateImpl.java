package com.discovery.service.push.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.discovery.service.picture.model.PictureInfo;
import com.discovery.service.push.dao.PushDAO;
import com.discovery.service.push.model.PushRecord;
import com.discovery.service.push.model.PushUserRecord;

/*
 * 推送DAO接口的Hibernate实现
 * @author chong
 * @see com.discovery.service.push.dao.PushDAO
 */
public class PushDAOHibernateImpl implements PushDAO {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public int refresh(int pictureId) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession()
				.createQuery("update PushUserRecord as p set p.status=? where p.pictureId=? and p.status=?")
				.setInteger(0, 2).setInteger(1, pictureId).setInteger(2, 0).executeUpdate();
	}

	@Override
	@Transactional
	public List<Integer> getPushList(PictureInfo pictureInfo,int count) {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession()
						.createQuery("select userSecuInfoId from UserEsseInfo as u where u.userSecuInfoId not in (select userId from PushUserRecord as p where p.pictureId=?1) order by ((u.latitude-?2)*(u.latitude-?2) + (u.longitude-?3)*(u.longitude-?3)) ASC ");
		query.setInteger("1", pictureInfo.getId());
		query.setFloat("2", pictureInfo.getLatitude());
		query.setFloat("3", pictureInfo.getLongitude());
		query.setFirstResult(0);
		query.setMaxResults(count);
		List<Integer> re = query.list();
		return re;
	}

	@Override
	@Transactional
	public void push(int pictureId, List<Integer> userIdList) {
		// TODO Auto-generated method stub
		PushRecord pushRecord = (PushRecord)sessionFactory.getCurrentSession()
			.createQuery("from PushRecord as p where p.pictureId=?").setInteger(0, pictureId).uniqueResult();
		PushUserRecord pushUserRecord;
		for(Integer i : userIdList){
			pushUserRecord = new PushUserRecord();
			pushUserRecord.setPictureId(pictureId);
			pushUserRecord.setUserId(i);
			pushUserRecord.setPushTime(new Date());
			pushUserRecord.setStatus((byte)0);
			sessionFactory.getCurrentSession().save(pushUserRecord);
			pushRecord.setRemainCount(pushRecord.getRemainCount()-1);
		}
		sessionFactory.getCurrentSession().save(pushRecord);
	}

}
