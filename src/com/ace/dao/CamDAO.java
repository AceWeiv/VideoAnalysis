package com.ace.dao;

import java.text.ParseException;
import java.util.List;

import com.ace.domain.Cam;
import com.ace.domain.CamK;

public interface CamDAO {
	//查询特定摄像头的全部数据
	public List<Cam> getAll(int cam_id);
	//查询特定摄像头的最新24条数据
	public List<Cam> getLast24(int cam_id);
	//查询特定摄像头、特定时间段的K线图数据
	public List<CamK> getKList1(int cam_id, int method, String startTime, String endTime) throws ParseException;
	//K线图滑动条拖拽更新数据方法
	public List<CamK> KDataRefresh(int cam_id, int method, String startTime, String endTime, int start_end) throws ParseException;
	//查询特定摄像头时间段内的图片地址
	public List<Cam> getScreenShot(int cam_id, int method, String startTime) throws ParseException;
}
