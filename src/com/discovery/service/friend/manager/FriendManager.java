package com.discovery.service.friend.manager;

import com.discovery.service.message.Message;

/*
 * 好友管理的接口类
 * @author chong
 */
public interface FriendManager {
	
	/*
	 * 发出好友请求
	 * @param sponsorId 发出请求的用户的id
	 * @param receiverId 被请求的用户的id
	 */
	public Message request(int sponsorId,int receiverId);
	
	/*
	 * 同意好友请求
	 * @param sponsorId 发出好友请求的用户id
	 * @param receiverId 接受好友请求的用户的id
	 */
	public Message agree(int sponsorId,int receiverId);
	
	/*
	 * 拒绝好友请求
	 * @param sponsorId 发出好友请求的用户id
	 * @param receiverId 拒绝好友请求的用户的id
	 */
	public Message refuse(int sponsorId,int receiverId);
	
	/*
	 * 断绝好友关系
	 * @param sponsorId 发出断交操作的用户的id
	 * @param receiverId 被断交的用户的id
	 */
	public Message breakOff(int sponsorId,int receiverId);
	
	/*
	 * 获取所有发出的好友请求
	 * @param userId 用户id
	 */
	public Message getAllToRequest(int userId);
	
	/*
	 * 获取所有接收的好友请求
	 * @param userId 用户id
	 */
	public Message getAllFromRequest(int userId);
	
	/*
	 * 获取所有的好友id
	 * @param userId 用户id
	 */
	public Message getAllFriends(int userId);
	
	/*
	 * 发送对话信息
	 * @param senderId 发送用户的id
	 * @param receiverId 接收用户的id
	 * @param content 会话内容
	 */
	public Message chat(int senderId,int receiverId,String content);
	
	/*
	 * 获取聊天记录
	 * @param senderId 发送用户的id
	 * @param receiverId 接收用户的id
	 * @param count 获取记录数量（-1为全部）
	 */
	public Message getChatRecord(int senderId,int receiverId,int count);
}
