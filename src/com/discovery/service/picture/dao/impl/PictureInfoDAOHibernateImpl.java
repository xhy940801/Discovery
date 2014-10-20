package com.discovery.service.picture.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.discovery.service.picture.dao.PictureInfoDAO;
import com.discovery.service.picture.model.PictureInfo;

/*
 * 图片信息DAO接口的Hibernate实现
 * @author chong
 * @see com.discovery.service.picture.dao.PictureInfoDAO
 */
public class PictureInfoDAOHibernateImpl implements PictureInfoDAO {
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void save(PictureInfo pictureInfo) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(pictureInfo);
	}

	@Override
	public void update(PictureInfo pictureInfo) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(pictureInfo);
	}

	@Override
	public PictureInfo getById(int id) {
		// TODO Auto-generated method stub
		return (PictureInfo)sessionFactory.getCurrentSession().get(PictureInfo.class, id);
	}

	@Override
	public List<PictureInfo> getByUserId(int userId) {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession()
						.createQuery("from PictureInfo as p where p.userSecuInfoId=?")
						.setInteger(0, userId);
		List<PictureInfo> re = query.list();
		return re;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession()
			.createQuery("delete PictureInfo as p where p.id=?")
			.setInteger(0, id).executeUpdate();
	}


}
