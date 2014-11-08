package com.discovery.service.push.manager.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;

import com.discovery.service.message.Message;
import com.discovery.service.message.impl.ErrorMessage;
import com.discovery.service.message.impl.GeneralMessage;
import com.discovery.service.message.impl.PushListMessage;
import com.discovery.service.picture.model.PictureInfo;
import com.discovery.service.push.dao.PushDAO;
import com.discovery.service.push.dao.PushRecordDAO;
import com.discovery.service.push.dao.PushUserRecordDAO;
import com.discovery.service.push.manager.PushManager;
import com.discovery.service.push.model.PushRecord;
import com.discovery.service.push.model.PushUserRecord;

public class PushManagerDefaultImpl implements PushManager {

	private PushDAO pushDAO;
	private PushRecordDAO pushRecordDAO;
	private PushUserRecordDAO pushUserRecordDAO;
	
	public PushManagerDefaultImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public void setPushDAO(PushDAO pushDAO) {
		this.pushDAO = pushDAO;
	}

	public void setPushRecordDAO(PushRecordDAO pushRecordDAO) {
		this.pushRecordDAO = pushRecordDAO;
	}

	public void setPushUserRecordDAO(PushUserRecordDAO pushUserRecordDAO) {
		this.pushUserRecordDAO = pushUserRecordDAO;
	}

	@Override
	public Message newPicturePush(PictureInfo pictureInfo) {
		// TODO Auto-generated method stub
		try {
			
			newPush(pictureInfo);
			
		}catch(HibernateException e){
			return new ErrorMessage(0, null);
		}catch(Exception e){
			return new ErrorMessage(0, null);
		}catch(Throwable e){
			return new ErrorMessage(0, null);
		}
		return new GeneralMessage(0,null);
	}

	@Override
	public Message refreshPicturePush(PictureInfo pictureInfo) {
		// TODO Auto-generated method stub
		try {
			
			oldPush(pictureInfo);
			
		}catch(HibernateException e){
			return new ErrorMessage(0, null);
		}catch(Exception e){
			return new ErrorMessage(0, null);
		}catch(Throwable e){
			return new ErrorMessage(0, null);
		}
		return new GeneralMessage(0,null);
	}

	@Override
	public Message getPushPictures(int userId,int offset,int count) {
		// TODO Auto-generated method stub
		List<PushUserRecord> list;
		try {
			
			list = pushUserRecordDAO.getListByUserId(userId,offset,count);
			
		}catch(HibernateException e){
			return new ErrorMessage(0, null);
		}catch(Exception e){
			return new ErrorMessage(0, null);
		}catch(Throwable e){
			return new ErrorMessage(0, null);
		}
		return new PushListMessage(0, list);
	}

	@Override
	public Message deletePicturePush(int pictureId) {
		// TODO Auto-generated method stub
		try {
			
			deletePush(pictureId);
			
		}catch(HibernateException e){
			return new ErrorMessage(0, null);
		}catch(Exception e){
			return new ErrorMessage(0, null);
		}catch(Throwable e){
			return new ErrorMessage(0, null);
		}
		return new GeneralMessage(0,null);
	}

	@Transactional
	private void newPush(PictureInfo pictureInfo){
		PushRecord pushRecord = new PushRecord();
		pushRecord.setPictureId(pictureInfo.getId());
		pushRecord.setAllCount(5);
		pushRecord.setRemainCount(5);
		pushRecord.setUpdateTime(new Date());
		pushRecord.setStatus(true);
		
		pushRecordDAO.save(pushRecord);
		
		push(pictureInfo,pushRecord);
	}
	
	@Transactional
	private void oldPush(PictureInfo pictureInfo){
		PushRecord pushRecord = pushRecordDAO.getByPictureId(pictureInfo.getId());
		pushRecord.setRemainCount(pushRecord.getRemainCount() + pushDAO.refresh(pictureInfo.getId()));
		
		pushRecordDAO.update(pushRecord);
		
		push(pictureInfo, pushRecord);
	}
	
	private void push(PictureInfo pictureInfo,PushRecord pushRecord){
		pushDAO.push(pushRecord.getPictureId(), pushDAO.getPushList(pictureInfo, pushRecord.getRemainCount()));
	}
	
	@Transactional
	private void deletePush(int pictureId){
		pushRecordDAO.delete(pictureId);
		pushUserRecordDAO.deleteByPictureId(pictureId);
	}
	
}
