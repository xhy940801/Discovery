package com.discovery.service.user.manager;

import com.discovery.service.user.message.Message;

public interface UserManager
{
	public Message register(String email, String password);
	public Message login(String email, String password);
	public Message getResetPasswordKey(String email);
	public Message resetPassword(String key, String password);
	public Message changePassword(int userId, String oldPassword, String newPassword);
	public Message changeEsseInfo(int userId, String[] infos);
}
