package com.discovery.service.label.dao;

import com.discovery.service.label.model.Label;

public interface LabelDAO
{
	public void save(Label label);
	public void update(Label label);
	public Label getById(int id);
	public Label getByLink(int link);
}
