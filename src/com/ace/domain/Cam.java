package com.ace.domain;

public class Cam {
	private int id;
	
	private int cam_id;
	
	private String time;
	
	private int car_num;
	
	private int man_num;
	
	private String screenshot;


	public int getCam_id() {
		return cam_id;
	}

	public void setCam_id(int cam_id) {
		this.cam_id = cam_id;
	}

	public String getScreenshot() {
		return screenshot;
	}

	public void setScreenshot(String screenshot) {
		this.screenshot = screenshot;
	}

	public int getCar_num() {
		return car_num;
	}

	public void setCar_num(int car_num) {
		this.car_num = car_num;
	}

	public int getMan_num() {
		return man_num;
	}

	public void setMan_num(int man_num) {
		this.man_num = man_num;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	
}
