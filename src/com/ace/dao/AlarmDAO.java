package com.ace.dao;

import java.util.List;

import com.ace.domain.Alarm;

public interface AlarmDAO {
	public List<Alarm> getAll();
	
	public List<Alarm> getNew(int id);
}
