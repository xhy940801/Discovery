package com.discovery.service.friend.dao;

import java.util.List;

import com.discovery.service.friend.model.RequestForFriend;


/*
 * 好友申请记录的DAO接口
 * @author chong
 */

public interface RequestForFriendDAO {
	
	/*
	 * 新增一条好友申请记录
	 * @param requestForFriend 好友申请
	 */
	public void save(RequestForFriend requestForFriend);
	
	/*
	 * 更新一条好友申请记录
	 * @param sponsorId 发出申请的用户id
	 * @param receiverId 被申请好友的用户id
	 */
	public void update(RequestForFriend requestForFriend);
	
	/*
	 * 删除一条好友申请记录
	 * @param sponsorId 发出申请的用户id
	 * @param receiverId 被申请好友的用户id
	 */
	public void delete(int sponsorId,int receiverId);
	
	/*
	 * 通过两名用户id获取好友申请记录
	 * @param sponsorId,receiverId 两名用户的id
	 */
	public RequestForFriend getById(int sponsorId,int receiverId);
	
	/*
	 * 通过用户id获取该用户发出的所有好友申请的对象用户的id
	 * @param id 用户安全信息id
	 */
	public List<RequestForFriend> getToRequset(int id);
	
	/*
	 * 通过用户id获取所有向该用户发出好友申请的用户的id
	 * @param id 用户安全信息id
	 */
	public List<RequestForFriend> getFromRequest(int id);

}
