package com.discovery.service.push.manager;

import com.discovery.service.message.Message;
import com.discovery.service.picture.model.PictureInfo;

/*
 * 推送管理的接口类
 * @author chong
 */
public interface PushManager {

	/*
	 * 新增图片推送
	 * @param pictureId 图片id
	 */
	public Message newPicturePush(PictureInfo pictureInfo);
	
	/*
	 * 刷新图片推送
	 * @param pictureId 图片id
	 */
	public Message refreshPicturePush(PictureInfo pictureInfo);
	
	/*
	 * 获取所推送的图片id列表
	 * @param userId 用户id
	 */
	public Message getPushPictures(int userId,int offset,int count);
	
	/*
	 * 删除图片的推送信息
	 * @param pictureId 图片id
	 */
	public Message deletePicturePush(int pictureId);
}
