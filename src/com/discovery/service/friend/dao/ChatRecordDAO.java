package com.discovery.service.friend.dao;

import java.util.List;

import com.discovery.service.friend.model.ChatRecord;

/*
 * 聊天记录的DAO接口
 * @author chong
 */

public interface ChatRecordDAO {

	/*
	 * 新增一条聊天记录
	 * @param chatRecord 聊天记录
	 */
	public void save(ChatRecord chatRecord);
	
	/*
	 * 查询两名用户的聊天记录
	 * @param senderId 发起查询用户的id
	 * @param receiverId 与该用户聊天的好友用户id
	 * @param count 返回数量
	 */
	public List<ChatRecord> getChatRecord(int senderId,int receiverId,int count);
}
