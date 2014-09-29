package com.discovery.service.message;

/**
 * 消息类
 * @author xiao.hy
 */
public interface Message
{
	/**
	 * 把消息转换为json格式
	 * @return
	 */
	public String toJSONMessage();
	
	/**
	 * 获取消息码
	 * 消息码具体含义见msgcode.txt
	 * @return 消息码
	 */
	public int getMsgCode();
}
