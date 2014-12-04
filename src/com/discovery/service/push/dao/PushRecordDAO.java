package com.discovery.service.push.dao;

import com.discovery.service.push.model.PushRecord;

/*
 * 图片的推送记录的DAO接口
 * @author chong
 */
public interface PushRecordDAO {

	/*
	 * 新增一条推送记录
	 */
	public void save(PushRecord pushRecord);
	
	/*
	 * 更新一条推送记录
	 */
	public void update(PushRecord pushRecord);
	
	/*
	 * 删除一条推送记录
	 * @param pictureId 图片id
	 */
	public void delete(int pictureId);
	
	/*
	 * 通过图片id获取一条推送记录
	 * @param pictureId 图片id
	 */
	public PushRecord getByPictureId(int pictureId);
	
	
}
