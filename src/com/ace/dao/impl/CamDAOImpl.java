package com.ace.dao.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.ace.dao.CamDAO;
import com.ace.dao.DAO;
import com.ace.domain.Cam;
import com.ace.domain.CamK;
import com.ace.utils.TimeUtils;

public class CamDAOImpl extends DAO<Cam> implements CamDAO {
	//查询特定摄像头的全部数据
	@Override
	public List<Cam> getAll(int cam_id) {
		String sql = "SELECT time,car_num,man_num FROM camdatas WHERE cam_id = ?";
		return getForList(sql,cam_id);
	}
	//查询特定摄像头的最新24条数据
	@Override
	public List<Cam> getLast24(int cam_id) {
		String sql = "SELECT time,car_num,man_num,screenshot FROM camdatas WHERE cam_id = ? ORDER BY time desc LIMIT 24";
		return getForList(sql,cam_id);
	}
	//查询特定摄像头、特定时间段的K线图数据
	public List<CamK> getKList1(int cam_id, int method, String startTime, String endTime) throws ParseException {
		// 对时间间隔做处理
		int overRide = 0;
		switch (method) {
		case 1:
			overRide = 1;
			break;
		case 2:
			overRide = 2;
			break;
		case 3:
			overRide = 5;
			break;
		}
		List<CamK> camKs = new ArrayList<CamK>();
		TimeUtils timeUtils = new TimeUtils();
		// 确保数据都是标准格式
		startTime = timeUtils.formatTime(startTime);
		endTime = timeUtils.formatTime(endTime);
		int open_car_num = 0;
		int end_car_num = 0;
		int highest_car_num = 0;
		int lowest_car_num = 0;
		int open_man_num = 0;
		int end_man_num = 0;
		int highest_man_num = 0;
		int lowest_man_num = 0;
		// 取出所有数据
		String sql = "SELECT * FROM camdatas WHERE time>=\"" + startTime + "\" and time<\"" + endTime + "\" and cam_id = ?";
		List<Cam> cams = getForList(sql,cam_id);
		// 计算第一个时间间隔
		String endTime1 = timeUtils.editMinute(startTime, overRide);
		// 若无查询结果，则返回值全是0
		if (cams.isEmpty()) {
			while (endTime1.compareTo(endTime) < 0) {
				CamK camK1 = new CamK();
				camK1.setTime(startTime);
				camK1.setHighest_car_num(0);
				camK1.setLowest_car_num(0);
				camK1.setOpen_car_num(0);
				camK1.setEnd_car_num(0);
				camK1.setHighest_man_num(0);
				camK1.setLowest_man_num(0);
				camK1.setOpen_man_num(0);
				camK1.setEnd_man_num(0);
				camK1.setScreenshot("//");
				camKs.add(camK1);
				startTime = endTime1;
				endTime1 = timeUtils.editMinute(startTime, overRide);
			}
			CamK camK1 = new CamK();
			camK1.setTime(startTime);
			camK1.setHighest_car_num(0);
			camK1.setLowest_car_num(0);
			camK1.setOpen_car_num(0);
			camK1.setEnd_car_num(0);
			camK1.setHighest_man_num(0);
			camK1.setLowest_man_num(0);
			camK1.setOpen_man_num(0);
			camK1.setEnd_man_num(0);
			camK1.setScreenshot("//");
			camKs.add(camK1);
			return camKs;
		}
		// 对第一组数据做初始化
		highest_car_num = lowest_car_num = open_car_num = cams.get(0).getCar_num();
		highest_man_num = lowest_man_num = open_man_num = cams.get(0).getMan_num();
		for (int i = 0; i < cams.size(); i++) {
			String time_list = cams.get(i).getTime();
			// 若已计算完一个时间间隔，则将计算结果插入至列表并初始化下一组数据
			if (time_list.compareTo(endTime1) >= 0) {
				String endTime2 = timeUtils.editMinute(endTime1, overRide);
				// 将时间切换至有数据的间隔，并将中间全插入0
				while (time_list.compareTo(endTime2) >= 0) {
					CamK camK1 = new CamK();
					camK1.setTime(startTime);
					camK1.setHighest_car_num(0);
					camK1.setLowest_car_num(0);
					camK1.setOpen_car_num(0);
					camK1.setEnd_car_num(0);
					camK1.setHighest_man_num(0);
					camK1.setLowest_man_num(0);
					camK1.setOpen_man_num(0);
					camK1.setEnd_man_num(0);
					camK1.setScreenshot(cams.get(i).getScreenshot());
					camKs.add(camK1);
					startTime = endTime1;
					endTime2 = timeUtils.editMinute(endTime1, overRide);
					endTime1 = endTime2;
				}
				CamK camK = new CamK();
				camK.setTime(startTime);
				camK.setHighest_car_num(highest_car_num);
				camK.setLowest_car_num(lowest_car_num);
				camK.setOpen_car_num(open_car_num);
				camK.setEnd_car_num(end_car_num);
				camK.setHighest_man_num(highest_man_num);
				camK.setLowest_man_num(lowest_man_num);
				camK.setOpen_man_num(open_man_num);
				camK.setEnd_man_num(end_man_num);
				camK.setScreenshot(cams.get(i).getScreenshot());
				camKs.add(camK);
				// 将时间切换至下一个间隔
				startTime = endTime1;
				endTime1 = endTime2;
				// 初始化下一组数据
				highest_car_num = lowest_car_num = open_car_num = cams.get(i).getCar_num();
				highest_man_num = lowest_man_num = open_man_num = cams.get(i).getMan_num();
			}
			// 计算最大最小值
			end_car_num = cams.get(i).getCar_num();
			end_man_num = cams.get(i).getMan_num();
			if (highest_car_num < end_car_num) {
				highest_car_num = end_car_num;
			}
			if (lowest_car_num > end_car_num) {
				lowest_car_num = end_car_num;
			}
			if (highest_man_num < end_man_num) {
				highest_man_num = end_man_num;
			}
			if (lowest_man_num > end_man_num) {
				lowest_man_num = end_man_num;
			}
		}
		// 插入最后一组数据
		CamK camK = new CamK();
		camK.setTime(startTime);
		camK.setHighest_car_num(highest_car_num);
		camK.setLowest_car_num(lowest_car_num);
		camK.setOpen_car_num(open_car_num);
		camK.setEnd_car_num(end_car_num);
		camK.setHighest_man_num(highest_man_num);
		camK.setLowest_man_num(lowest_man_num);
		camK.setOpen_man_num(open_man_num);
		camK.setEnd_man_num(end_man_num);
		camK.setScreenshot(cams.get(cams.size()-1).getScreenshot());
		camKs.add(camK);
		//若结果已经添加完但尾部没数据时填充0
		while (endTime1.compareTo(endTime) < 0) {
			startTime = endTime1;
			endTime1 = timeUtils.editMinute(startTime, overRide);
			CamK camK1 = new CamK();
			camK1.setTime(startTime);
			camK1.setHighest_car_num(0);
			camK1.setLowest_car_num(0);
			camK1.setOpen_car_num(0);
			camK1.setEnd_car_num(0);
			camK1.setHighest_man_num(0);
			camK1.setLowest_man_num(0);
			camK1.setOpen_man_num(0);
			camK1.setEnd_man_num(0);
			camK1.setScreenshot("//");
			camKs.add(camK1);
		}
		return camKs;
	}
	
	//K线图滑动条拖拽更新数据方法
	public List<CamK> KDataRefresh(int cam_id, int method, String startTime, String endTime, int start_end) throws ParseException{
		TimeUtils timeUtils = new TimeUtils();
		int overRide = 0;
		switch (method) {
		case 1:
			overRide = 1;
			break;
		case 2:
			overRide = 2;
			break;
		case 3:
			overRide = 5;
			break;
		}
		startTime = timeUtils.formatTime(startTime);
		endTime = timeUtils.formatTime(endTime);
		//向左和向右更新时计算不同的时间段，再调用K线图数据方法
		if(start_end == 1) {
			startTime = timeUtils.editMinute(endTime, overRide);
			endTime = timeUtils.editMinute(endTime, overRide*11);
			return getKList1(cam_id,method, startTime, endTime);
		}else {
			endTime = startTime;
			startTime = timeUtils.editMinute(startTime, -overRide*10);
			return getKList1(cam_id,method, startTime, endTime);
		}
	}
	//查询特定摄像头时间段内的图片地址
	@Override
	public List<Cam> getScreenShot(int cam_id, int method, String startTime) throws ParseException {
		TimeUtils timeUtils = new TimeUtils();
		int overRide = 0;
		switch (method) {
		case 1:
			overRide = 1;
			break;
		case 2:
			overRide = 2;
			break;
		case 3:
			overRide = 5;
			break;
		}
		startTime = timeUtils.formatTime(startTime);
		String start_time = timeUtils.editMinute(startTime, -overRide);
		String end_time = timeUtils.editMinute(startTime, overRide);
		String sql = "SELECT * FROM camdatas WHERE time>=\"" + start_time + "\" and time<\"" + end_time + "\" and cam_id = ?";;
		return getForList(sql, cam_id);
	}
}
