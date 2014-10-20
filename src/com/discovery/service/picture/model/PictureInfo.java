package com.discovery.service.picture.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "picture_infos")
public class PictureInfo {

	private int id;
	private int fileId;
	private float longitude;
	private float latitude;
	private int userSecuInfoId;
	private int totalLikeNum;
	private int totalTemperature;
	private Date createdTime;
	private Date modifyLikeTime;
	private Date modifyTempTime;
	private String remark;
	private int likeNum0, likeNum1, likeNum2, likeNum3, likeNum4, likeNum5, likeNum6, likeNum7, likeNum8, likeNum9;
	private int temperature0, temperature1, temperature2, temperature3, temperature4, temperature5, temperature6, temperature7, temperature8, temperature9;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}

	@Column(name="file_id")
	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public float getLongitude()
	{
		return longitude;
	}

	public void setLongitude(float longitude)
	{
		this.longitude = longitude;
	}

	public float getLatitude()
	{
		return latitude;
	}

	public void setLatitude(float latitude)
	{
		this.latitude = latitude;
	}

	@Column(name="user_secu_info_id")
	public int getUserSecuInfoId()
	{
		return userSecuInfoId;
	}

	public void setUserSecuInfoId(int userSecuInfoId)
	{
		this.userSecuInfoId = userSecuInfoId;
	}

	@Column(name="total_like_num")
	public int getTotalLikeNum()
	{
		return totalLikeNum;
	}

	public void setTotalLikeNum(int totalLikeNum)
	{
		this.totalLikeNum = totalLikeNum;
	}

	@Column(name="total_temperature")
	public int getTotalTemperature()
	{
		return totalTemperature;
	}

	public void setTotalTemperature(int totalTemperature)
	{
		this.totalTemperature = totalTemperature;
	}

	@Column(name="created_time")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatedTime()
	{
		return createdTime;
	}

	public void setCreatedTime(Date createdTime)
	{
		this.createdTime = createdTime;
	}

	@Column(name="modify_like_time")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getModifyLikeTime() {
		return modifyLikeTime;
	}

	public void setModifyLikeTime(Date modifyLikeTime) {
		this.modifyLikeTime = modifyLikeTime;
	}

	@Column(name="modify_temp_time")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getModifyTempTime() {
		return modifyTempTime;
	}

	public void setModifyTempTime(Date modifyTempTime) {
		this.modifyTempTime = modifyTempTime;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	@Column(name="like_num0")
	public int getLikeNum0()
	{
		return likeNum0;
	}

	public void setLikeNum0(int likeNum0)
	{
		this.likeNum0 = likeNum0;
	}

	@Column(name="like_num1")
	public int getLikeNum1()
	{
		return likeNum1;
	}

	public void setLikeNum1(int likeNum1)
	{
		this.likeNum1 = likeNum1;
	}

	@Column(name="like_num2")
	public int getLikeNum2()
	{
		return likeNum2;
	}

	public void setLikeNum2(int likeNum2)
	{
		this.likeNum2 = likeNum2;
	}

	@Column(name="like_num3")
	public int getLikeNum3()
	{
		return likeNum3;
	}

	public void setLikeNum3(int likeNum3)
	{
		this.likeNum3 = likeNum3;
	}

	@Column(name="like_num4")
	public int getLikeNum4()
	{
		return likeNum4;
	}

	public void setLikeNum4(int likeNum4)
	{
		this.likeNum4 = likeNum4;
	}

	@Column(name="like_num5")
	public int getLikeNum5()
	{
		return likeNum5;
	}

	public void setLikeNum5(int likeNum5)
	{
		this.likeNum5 = likeNum5;
	}

	@Column(name="like_num6")
	public int getLikeNum6()
	{
		return likeNum6;
	}

	public void setLikeNum6(int likeNum6)
	{
		this.likeNum6 = likeNum6;
	}

	@Column(name="like_num7")
	public int getLikeNum7()
	{
		return likeNum7;
	}

	public void setLikeNum7(int likeNum7)
	{
		this.likeNum7 = likeNum7;
	}

	@Column(name="like_num8")
	public int getLikeNum8()
	{
		return likeNum8;
	}

	public void setLikeNum8(int likeNum8)
	{
		this.likeNum8 = likeNum8;
	}

	@Column(name="like_num9")
	public int getLikeNum9()
	{
		return likeNum9;
	}

	public void setLikeNum9(int likeNum9)
	{
		this.likeNum9 = likeNum9;
	}

	public int getTemperature0()
	{
		return temperature0;
	}

	public void setTemperature0(int temperature0)
	{
		this.temperature0 = temperature0;
	}

	public int getTemperature1()
	{
		return temperature1;
	}

	public void setTemperature1(int temperature1)
	{
		this.temperature1 = temperature1;
	}

	public int getTemperature2()
	{
		return temperature2;
	}

	public void setTemperature2(int temperature2)
	{
		this.temperature2 = temperature2;
	}

	public int getTemperature3()
	{
		return temperature3;
	}

	public void setTemperature3(int temperature3)
	{
		this.temperature3 = temperature3;
	}

	public int getTemperature4()
	{
		return temperature4;
	}

	public void setTemperature4(int temperature4)
	{
		this.temperature4 = temperature4;
	}

	public int getTemperature5()
	{
		return temperature5;
	}

	public void setTemperature5(int temperature5)
	{
		this.temperature5 = temperature5;
	}

	public int getTemperature6()
	{
		return temperature6;
	}

	public void setTemperature6(int temperature6)
	{
		this.temperature6 = temperature6;
	}

	public int getTemperature7()
	{
		return temperature7;
	}

	public void setTemperature7(int temperature7)
	{
		this.temperature7 = temperature7;
	}

	public int getTemperature8()
	{
		return temperature8;
	}

	public void setTemperature8(int temperature8)
	{
		this.temperature8 = temperature8;
	}

	public int getTemperature9()
	{
		return temperature9;
	}

	public void setTemperature9(int temperature9)
	{
		this.temperature9 = temperature9;
	}
	
}
