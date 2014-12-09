package com.discovery.service.picture.manager;

import java.util.List;

import com.discovery.service.message.Message;
import com.discovery.service.picture.model.PictureInfo;

/*
 * 图片管理类
 * @author chong
 */
public interface PictureManager {

	/*
	 * 新增一条图片信息
	 * @param fileId 图片信息对应的文件id
	 * @param userId 发布图片的用户id
	 * @param longitude 经度
	 * @param latitude 纬度
	 * @param remark 图片描述
	 */
	public Message addPicture(int fileId,int userId,float longitude,float latitude,String remark);
	
	/*
	 * 删除一条图片信息
	 * @param pictureId 图片信息id
	 */
	public Message deletePicture(int pictureId);
	
	/*
	 * 点赞操作
	 * @param pictureId 图片信息id
	 * @param count 增加量
	 */
	public Message like(int pictureId,int count);
	
	/*
	 * 增加热度操作
	 * @param pictureId 图片信息id
	 * @param count 增加量
	 */
	public Message addTemperature(int pictureId,int count);
	
	public Message getPicture(int pictureId);
	
	public Message getPictureList(List<Integer> list);
}
