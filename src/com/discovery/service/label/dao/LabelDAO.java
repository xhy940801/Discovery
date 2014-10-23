package com.discovery.service.label.dao;

import java.util.List;

import com.discovery.service.label.model.Label;

public interface LabelDAO
{
	public void save(Label label);
	public void update(Label label);
	public Label getById(int id);
	public List<Label> getByLink(int link);
	public Label getByName(String name);
	public List<Integer> getLinks(int offset, int length);
}
