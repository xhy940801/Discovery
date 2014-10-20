package com.discovery.service.picture.dao;

import java.util.List;

import com.discovery.service.picture.model.PictureInfo;

/*
 * 图片信息的DAO接口
 * @author chong
 */
public interface PictureInfoDAO {

	/*
	 * 新增一条图片信息
	 * @param pictureInfo 图片信息
	 */
	public void save(PictureInfo pictureInfo);
	
	/*
	 * 更新一条图片信息
	 * @param pictureInfo 图片信息
	 */
	public void update(PictureInfo pictureInfo);
	
	/*
	 * 获取一条图片信息
	 * @param id 图片信息id
	 * @return 图片信息
	 */
	public PictureInfo getById(int id);
	
	/*
	 * 获取用户所有的图片信息
	 * @param userId 用户id
	 * @return 图片信息列表
	 */
	public List<PictureInfo> getByUserId(int userId);
	
	/*
	 * 删除一条图片信息
	 * @param id 图片信息id
	 */
	public void delete(int id);
	
}
