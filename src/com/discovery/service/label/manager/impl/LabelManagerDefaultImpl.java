package com.discovery.service.label.manager.impl;

import com.discovery.service.label.dao.LabelDAO;
import com.discovery.service.label.manager.LabelManager;
import com.discovery.service.message.Message;

public class LabelManagerDefaultImpl implements LabelManager
{
	private LabelDAO labelDAO;

	@Override
	public Message getLabelClan(String name)
	{
		return null;
	}

	@Override
	public Message linkAndChangeCircleLabel(int id1, int id2)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message unlink(int id)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Message getLabels(int offset, int limit)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setLabelDAO(LabelDAO labelDAO)
	{
		this.labelDAO = labelDAO;
	}

}
