package com.ace.dao;

import java.text.ParseException;
import java.util.List;

import com.ace.domain.Cam;
import com.ace.domain.CamK;

public interface CamDAO2 {

	public List<Cam> getAll();
	
	public List<Cam> getLast24();
	
	public String getLastTime();
	
	public List<CamK> getKList(int method,String startTime,String endTime) throws ParseException;
	
	public List<CamK> getKList1(int method, String startTime, String endTime) throws ParseException;
	
	public List<CamK> KDataRefresh(int method, String startTime, String endTime, int start_end) throws ParseException;
}
