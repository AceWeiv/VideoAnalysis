package com.ace.dao;

import java.util.List;

import com.ace.domain.Config;

public interface ConfigDAO {
	//查询所有摄像头配置信息
	public List<Config> getAll();
	//新建摄像头
	public void save(Config config);
	//删除特定摄像头
	public void delete(Integer id);
	//更新摄像头
	public void update(Config config);
	//查询特定摄像头
	public Config get(int id);

}
