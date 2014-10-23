package com.discovery.service.label.manager;

import com.discovery.service.message.Message;

public interface LabelManager
{
	public Message getLabelClan(String name);
	public Message getLabels(int offset, int limit);
	public Message linkAndChangeCircleLabel(int id1, int id2);
	public Message unlink(int id);
}
