package com.discovery.service.friend.manager.impl;

import java.util.Date;

import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;

import com.discovery.service.friend.dao.ChatRecordDAO;
import com.discovery.service.friend.dao.RelationOfFriendDAO;
import com.discovery.service.friend.dao.RequestForFriendDAO;
import com.discovery.service.friend.manager.FriendManager;
import com.discovery.service.friend.model.ChatRecord;
import com.discovery.service.friend.model.RelationOfFriend;
import com.discovery.service.friend.model.RequestForFriend;
import com.discovery.service.message.Message;
import com.discovery.service.message.impl.ChatRecordListMessage;
import com.discovery.service.message.impl.ErrorMessage;
import com.discovery.service.message.impl.GeneralMessage;
import com.discovery.service.message.impl.RelationListMessage;
import com.discovery.service.message.impl.RequestListMessage;

/*
 * 好友管理的实现类
 * @author chong
 * @see com.discovery.service.friend.manager.FriendManager
 */
public class FriendManagerDefaultImpl implements FriendManager {
	
	private RelationOfFriendDAO relationOfFriendDAO;
	private RequestForFriendDAO requestForFriendDAO;
	private ChatRecordDAO chatRecordDAO;
	
	public FriendManagerDefaultImpl(){}
	
	@Override
	public Message request(int sponsorId, int receiverId) {
		// TODO Auto-generated method stub
		RelationOfFriend relationOfFriend;
		RequestForFriend requestForFriend;
		try{
			if(isFriend(sponsorId, receiverId)){
				return new ErrorMessage(302011, null);
			}
			
			requestForFriend = requestForFriendDAO.getById(sponsorId, receiverId);
			if(requestForFriend != null){
				requestForFriend.setRequestTime(new Date());
				requestForFriend.setHandleTime(null);
				requestForFriend.setStatus((byte)0);
				requestForFriendDAO.update(requestForFriend);
			}else{
				requestForFriend = new RequestForFriend();
				requestForFriend.setSponsorId(sponsorId);
				requestForFriend.setReceiverId(receiverId);
				requestForFriend.setRequestTime(new Date());
				requestForFriend.setStatus((byte)0);
				requestForFriendDAO.save(requestForFriend);
			}
			
		}catch(HibernateException e){
			return new ErrorMessage(402010, null);
		}catch(Exception e){
			return new ErrorMessage(502010, null);
		}catch(Throwable e){
			return new ErrorMessage(702010, null);
		}
		return new GeneralMessage(0,null);
	}

	@Override
	public Message agree(int sponsorId, int receiverId) {
		// TODO Auto-generated method stub
		RelationOfFriend relationOfFriend;
		RequestForFriend requestForFriend;
		try{
			if(isFriend(sponsorId, receiverId)){
				return new ErrorMessage(302021,null);
			}
			
			requestForFriend = requestForFriendDAO.getById(sponsorId, receiverId);
			if(requestForFriend == null){
				return new ErrorMessage(302022, null);
			}
			if(requestForFriend.getStatus() != 0){
				return new ErrorMessage(302023, null);
			}
			
			addFriend(sponsorId, receiverId,requestForFriend);		
			
		}catch(HibernateException e){
			return new ErrorMessage(402020, null);
		}catch(Exception e){
			return new ErrorMessage(502020, null);
		}catch(Throwable e){
			return new ErrorMessage(702020, null);
		}
		return new GeneralMessage(0,null);
	}
	
	@Override
	public Message refuse(int sponsorId, int receiverId) {
		// TODO Auto-generated method stub
		RequestForFriend requestForFriend;
		try{
			if(isFriend(sponsorId, receiverId)){
				return new ErrorMessage(302031, null);
			}
			
			requestForFriend = requestForFriendDAO.getById(sponsorId, receiverId);
			if(requestForFriend == null){
				return new ErrorMessage(302032, null);
			}
			if(requestForFriend.getStatus() != 0){
				return new ErrorMessage(302033, null);
			}
			
			requestForFriend.setHandleTime(new Date());
			requestForFriend.setStatus((byte)2);
			requestForFriendDAO.update(requestForFriend);
			
		}catch(HibernateException e){
			return new ErrorMessage(402030, null);
		}catch(Exception e){
			return new ErrorMessage(502030, null);
		}catch(Throwable e){
			return new ErrorMessage(702030, null);
		}
		return new GeneralMessage(0, null);
	}
	
	@Override
	public Message breakOff(int sponsorId, int receiverId) {
		// TODO Auto-generated method stub
		RelationOfFriend relationOfFriend;
		try{
			if(!isFriend(sponsorId, receiverId)){
				return new ErrorMessage(302041, null);
			}
			
			deleteFriend(sponsorId, receiverId);
			
		}catch(HibernateException e){
			return new ErrorMessage(402040, null);
		}catch(Exception e){
			return new ErrorMessage(502040, null);
		}catch(Throwable e){
			return new ErrorMessage(702040, null);
		}
		return new GeneralMessage(0, null);
	}

	@Override
	public Message getAllToRequest(int userId) {
		// TODO Auto-generated method stub
		try{
			
			return new RequestListMessage(0,requestForFriendDAO.getToRequset(userId));
			
		}catch(HibernateException e){
			return new ErrorMessage(402050, null);
		}catch(Exception e){
			return new ErrorMessage(502050, null);
		}catch(Throwable e){
			return new ErrorMessage(702050, null);
		}
	}
	
	@Override
	public Message getAllFromRequest(int userId) {
		// TODO Auto-generated method stub
		try{
			
			return new RequestListMessage(0,requestForFriendDAO.getFromRequest(userId));
			
		}catch(HibernateException e){
			return new ErrorMessage(402060, null);
		}catch(Exception e){
			return new ErrorMessage(502060, null);
		}catch(Throwable e){
			return new ErrorMessage(702060, null);
		}
	}

	@Override
	public Message getAllFriends(int userId) {
		// TODO Auto-generated method stub
		try{
			
			return new RelationListMessage(0,relationOfFriendDAO.getFriendsList(userId));
			
		}catch(HibernateException e){
			return new ErrorMessage(402070, null);
		}catch(Exception e){
			return new ErrorMessage(502070, null);
		}catch(Throwable e){
			return new ErrorMessage(702070, null);
		}
	}
	
	@Override
	public Message chat(int senderId, int receiverId, String content) {
		// TODO Auto-generated method stub
		ChatRecord chatRecord;
		try{
			if(!isFriend(senderId, receiverId)){
				return new ErrorMessage(302071, null);
			}
			
			chatRecord = new ChatRecord();
			chatRecord.setSenderId(senderId);
			chatRecord.setReceiverId(receiverId);
			chatRecord.setContent(content);
			chatRecord.setSendTime(new Date());
			chatRecordDAO.save(chatRecord);
			
		}catch(HibernateException e){
			return new ErrorMessage(402080, null);
		}catch(Exception e){
			return new ErrorMessage(502080, null);
		}catch(Throwable e){
			return new ErrorMessage(702080, null);
		}
		return new GeneralMessage(0, null);
	}
	
	@Override
	public Message getChatRecord(int senderId, int receiverId, int count) {
		// TODO Auto-generated method stub
		try{
			
			return new ChatRecordListMessage(0,chatRecordDAO.getChatRecord(senderId, receiverId, count));
			
		}catch(HibernateException e){
			return new ErrorMessage(402090, null);
		}catch(Exception e){
			return new ErrorMessage(502090, null);
		}catch(Throwable e){
			return new ErrorMessage(702090, null);
		}
	}
	
	@Transactional
	public void addFriend(int sponsorId,int receiverId,RequestForFriend requestForFriend){
		RelationOfFriend relationOfFriend;
		
		relationOfFriend = new RelationOfFriend();
		relationOfFriend.setSponsorId(sponsorId);
		relationOfFriend.setReceiverId(receiverId);
		relationOfFriendDAO.save(relationOfFriend);
		
		relationOfFriend = new RelationOfFriend();
		relationOfFriend.setSponsorId(receiverId);
		relationOfFriend.setReceiverId(sponsorId);
		relationOfFriendDAO.save(relationOfFriend);
		
		requestForFriend.setHandleTime(new Date());
		requestForFriend.setStatus((byte)1);
		requestForFriendDAO.update(requestForFriend);
	}
	
	@Transactional
	public void deleteFriend(int sponsorId,int receiverId){
		relationOfFriendDAO.delete(sponsorId, receiverId);
		relationOfFriendDAO.delete(receiverId, sponsorId);
	}
	
	public boolean isFriend(int sponsorId,int receiverId){
		RelationOfFriend relationOfFriend = relationOfFriendDAO.getById(sponsorId, receiverId);
		if(relationOfFriend != null){
			return true;
		}else{
			return false;
		}
	}

	public void setRelationOfFriendDAO(RelationOfFriendDAO relationOfFriendDAO) {
		this.relationOfFriendDAO = relationOfFriendDAO;
	}

	public void setRequestForFriendDAO(RequestForFriendDAO requestForFriendDAO) {
		this.requestForFriendDAO = requestForFriendDAO;
	}

	public void setChatRecordDAO(ChatRecordDAO chatRecordDAO) {
		this.chatRecordDAO = chatRecordDAO;
	}
	
}
