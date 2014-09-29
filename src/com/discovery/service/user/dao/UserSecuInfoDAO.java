package com.discovery.service.user.dao;

import com.discovery.service.user.model.UserSecuInfo;

/**
 * 用户安全信息的DAO接口
 * @author xiao.hy
 */
public interface UserSecuInfoDAO
{
	/**
	 * 新增用户安全信息
	 * @param userSecuInfo 用户安全信息
	 */
	public void save(UserSecuInfo userSecuInfo);
	
	/**
	 * 更新用户安全信息
	 * @param userSecuInfo 用户安全信息
	 */
	public void update(UserSecuInfo userSecuInfo);
	
	/**
	 * 通过id获取用户安全信息
	 * @param id 用户安全信息的id
	 * @return UserSecuInfo 用户安全信息
	 */
	public UserSecuInfo getById(int id);
	
	/**
	 * 通过用户名获取用户安全信息
	 * @param username 用户名
	 * @return UserSecuInfo 用户安全信息
	 */
	public UserSecuInfo getByUsername(String username);
}
