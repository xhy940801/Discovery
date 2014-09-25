package com.discovery.service.user.dao;

import com.discovery.service.user.model.UserSecuInfo;

public interface UserSecuInfoDAO
{
	public void save(UserSecuInfo userSecuInfo);
	public void update(UserSecuInfo userSecuInfo);
	public UserSecuInfo getById(int id);
	public UserSecuInfo getByUsername(String username);
}
