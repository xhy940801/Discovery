package com.discovery.service.friend.dao;

import java.util.List;

import com.discovery.service.friend.model.RelationOfFriend;


/*
 * 好友关系操作的DAO接口
 * @author chong
 */

public interface RelationOfFriendDAO {
	
	/*
	 * 新增一条好友关系记录
	 * @param relationOfFriend 好友关系
	 */
	public void save(RelationOfFriend relationOfFriend);
	
	/*
	 * 删除一条好友关系记录
	 * @param sponsorId 发起删除操作用户安全信息id
	 * @param receiverId 被删除用户安全信息id
	 */
	public void delete(int sponsorId,int receiverId);
	
	/*
	 * 获取一条好友关系记录
	 * @param sponsorId,receiverId 两名用户id
	 */
	public RelationOfFriend getById(int sponsorId,int receiverId);
	
	/*
	 * 通过用户id获取该用户所有好友关系记录
	 * @param id 用户安全信息id
	 */
	public List<RelationOfFriend> getFriendsList(int id);
}
