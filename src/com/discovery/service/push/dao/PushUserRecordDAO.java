package com.discovery.service.push.dao;

import java.util.List;

import com.discovery.service.push.model.PushUserRecord;

/*
 * 图片推送用户列表的DAO接口
 * @author chong
 */
public interface PushUserRecordDAO {

	/*
	 * 新增一条图片推送用户的记录
	 */
	public void save(PushUserRecord pushUserRecord);
	
	/*
	 * 更新一条图片推送用户的记录
	 */
	public void update(PushUserRecord pushUserRecord);
	
	/*
	 * 通过图片id删除图片推送用户的记录
	 * @param pictureId 图片id
	 */
	public void deleteByPictureId(int pictureId);
	
	/*
	 * 通过用户id删除图片推送用户的记录
	 * @param userId 用户id
	 */
	public void deleteByUserId(int userId);
	
	/*
	 * 获取一条图片推送用户的记录
	 * @param pictureId 图片id
	 * @param userId 用户id
	 */
	public PushUserRecord getById(int pictureId,int userId);
	
	/*
	 * 获取一张图片所有的推送用户记录
	 * @param pictureId 图片id
	 */
	public List<PushUserRecord> getListByPictureId(int pictureId);
	
	/*
	 * 获取一个用户所有的推送图片记录
	 * @param userId 用户id
	 */
	public List<PushUserRecord> getListByUserId(int userId);
}
