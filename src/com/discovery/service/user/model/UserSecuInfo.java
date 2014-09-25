package com.discovery.service.user.model;

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
@Table(name = "user_secu_infos")
public class UserSecuInfo
{
	private int id;
	private String email;
	private String password;
	private Date registrationTime;
	private Date lastLoginTime;

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

	@Column(length = 32)
	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	@Column(length = 32)
	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	@Column(name = "registration_time", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getRegistrationTime()
	{
		return registrationTime;
	}

	public void setRegistrationTime(Date registrationTime)
	{
		this.registrationTime = registrationTime;
	}

	@Column(name = "last_login_time")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getLastLoginTime()
	{
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime)
	{
		this.lastLoginTime = lastLoginTime;
	}

}
