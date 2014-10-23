package com.discovery.service.user.manager;

import com.discovery.service.message.Message;

/**
 * 用户管理的类
 * @author xiaohy
 */
public interface UserManager
{
	/**
	 * 注册
	 * @param email email（邮箱）
	 * @param password 密码
	 * @return 消息类
	 */
	public Message register(String email, String password);
	
	/**
	 * 登录
	 * @param email email（邮箱）
	 * @param password 密码
	 * @return 消息类
	 */
	public Message login(String email, String password);
	
	/**
	 * 获取重置密码的key
	 * @param email 要重置密码的邮箱
	 * @return 消息类
	 */
	public Message getResetPasswordKey(String email);
	
	/**
	 * 重置密码
	 * @param key key
	 * @param password 新密码
	 * @return 消息类
	 */
	public Message resetPassword(String key, String password);
	
	/**
	 * 更改密码
	 * @param userId 用户id
	 * @param oldPassword 旧密码
	 * @param newPassword 新密码
	 * @return 消息类
	 */
	public Message changePassword(int userId, String oldPassword, String newPassword);
	
	/**
	 * 改变用户信息
	 * @param userId 用户id
	 * @param infos 电话／地址等等。。。
	 * @return 消息类
	 */
	public Message changeEsseInfo(int userId, String[] infos);
	
	/**
	 * 获取用户基本信息
	 * @param userId 用户的id
	 * @return 消息类
	 */
	public Message getEsseInfo(int userId);
}
