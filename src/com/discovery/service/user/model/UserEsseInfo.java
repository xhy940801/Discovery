package com.discovery.service.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_esse_infos")
public class UserEsseInfo
{
	private int id;
	private int userSecuInfoId;
	private float latitude;
	private float longitude;
	private String nickname;
	private String tel;
	private String phone;
	private String address;
	private int revision;
	
	public UserEsseInfo()
	{
		nickname = "";
		tel = "";
		phone = "";
		address = "";
	}

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

	@Column(name = "user_secu_info_id", updatable = false)
	public int getUserSecuInfoId()
	{
		return userSecuInfoId;
	}

	public void setUserSecuInfoId(int userSecuInfoId)
	{
		this.userSecuInfoId = userSecuInfoId;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	@Column(length = 16)
	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	@Column(length = 16)
	public String getTel()
	{
		return tel;
	}

	public void setTel(String tel)
	{
		this.tel = tel;
	}

	@Column(length = 16)
	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	@Column(length = 255)
	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	@Column
	public int getRevision()
	{
		return revision;
	}

	public void setRevision(int revision)
	{
		this.revision = revision;
	}

}
