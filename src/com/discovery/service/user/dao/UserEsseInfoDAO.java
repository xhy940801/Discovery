package com.discovery.service.user.dao;

import com.discovery.service.user.model.UserEsseInfo;

public interface UserEsseInfoDAO
{
	public void save(UserEsseInfo userEsseInfo);
	public void update(UserEsseInfo userEsseInfo);
	public UserEsseInfo getById(int id);
	public UserEsseInfo getByUserSecuInfoId(int id);
}
