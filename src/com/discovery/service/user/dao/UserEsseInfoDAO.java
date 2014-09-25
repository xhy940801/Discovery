package com.discovery.service.user.dao;

import com.discovery.service.user.model.UserEsseInfo;

/**
 * 用户基本信息的DAO接口
 * @author xiao.hy
 */
public interface UserEsseInfoDAO
{
	/**
	 * 新增一条UserEsseInfo记录
	 * @param userEsseInfo 用户基本信息
	 */
	public void save(UserEsseInfo userEsseInfo);
	
	/**
	 * 更新用户基本信息
	 * @param userEsseInfo 用户基本信息
	 */
	public void update(UserEsseInfo userEsseInfo);
	
	/**
	 * 通过id获取用户基本信息
	 * @param id 用户基本信息记录的id
	 * @return 用户基本信息
	 */
	public UserEsseInfo getById(int id);
	
	/**
	 * 通过用户安全信息的id获取用户基本信息
	 * @param id 用户安全信息的id
	 * @return 用户基本信息
	 */
	public UserEsseInfo getByUserSecuInfoId(int id);
}
