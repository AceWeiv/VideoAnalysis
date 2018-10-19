package com.ace.dao.impl;

import java.util.List;

import com.ace.dao.AlarmDAO;
import com.ace.dao.DAO;
import com.ace.domain.Alarm;

public class AlarmDAOImpl extends DAO<Alarm> implements AlarmDAO{

	@Override
	public List<Alarm> getAll() {
		String sql = "SELECT * FROM alarmtable";
		return getForList(sql);
	}

	@Override
	public List<Alarm> getNew(int id) {
		String sql = "SELECT * FROM alarmtable WHERE id > ?";
		return getForList(sql, id);
	}

}
