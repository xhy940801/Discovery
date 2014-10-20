package com.discovery.service.push.dao;

import java.util.List;

import com.discovery.service.picture.model.PictureInfo;


/*
 * 推送的DAO接口
 * @author chong
 */
public interface PushDAO {

	/*
	 * 刷新图片的推送信息
	 * @param pictureId 图片id
	 * @return 返回未完成推送的数量
	 */
	public int refresh(int pictureId);
	
	/*
	 * 获取图片所要推送的用户id数组
	 * @param pictureInfo 图片信息
	 * @param count 推送人数
	 * @return int列表  用户id
	 */
	public List<Integer> getPushList(PictureInfo pictureInfo,int count);
	
	/*
	 * 推送操作
	 * @param pictureId 图片id
	 * @param userArray 用户id列表
	 */
	public void push(int pictureId,List<Integer> userIdList);
	
}
