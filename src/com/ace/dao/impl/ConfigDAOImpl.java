package com.ace.dao.impl;

import java.util.List;

import com.ace.dao.ConfigDAO;
import com.ace.dao.DAO;
import com.ace.domain.Config;

public class ConfigDAOImpl extends DAO<Config>implements ConfigDAO{
	//查询所有摄像头配置信息
	@Override
	public List<Config> getAll() {
		String sql = "SELECT * FROM camsource";
		return getForList(sql);
	}
	//新建摄像头
	@Override
	public void save(Config config) {
		String sql = "INSERT INTO camsource(name,adress,isActive,threshold_car,threshold_man) VALUES(?,?,?,?,?)";
		update(sql, config.getName(), config.getAdress(), config.isActive(), config.getThreshold_car(), config.getThreshold_man());
	}
	//删除特定摄像头
	@Override
	public void delete(Integer id) {
		String sql = "DELETE FROM camsource WHERE id = ?";
		update(sql, id);
	}
	//更新摄像头
	@Override
	public void update(Config config) {
		String sql = "UPDATE camsource SET name = ?,adress=?,isActive=?,threshold_car=?,threshold_man=? WHERE id = ?";
		update(sql, config.getName(), config.getAdress(), config.isActive(), config.getThreshold_car(), config.getThreshold_man(), config.getId());
	}
	//查询特定摄像头
	@Override
	public Config get(int id) {
		String sql = "SELECT * FROM camsource WHERE id = ?";
		return get(sql, id);
	}

}
