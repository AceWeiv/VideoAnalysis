package com.ace.domain;

public class Alarm {
	private int id;
	
	private int cam_id;
	
	private String time;
	
	private int alarm_type;
	
	private String content;
	
	private String screenshot;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCam_id() {
		return cam_id;
	}

	public void setCam_id(int cam_id) {
		this.cam_id = cam_id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getScreenshot() {
		return screenshot;
	}

	public void setScreenshot(String screenshot) {
		this.screenshot = screenshot;
	}

	public int getAlarm_type() {
		return alarm_type;
	}

	public void setAlarm_type(int alarm_type) {
		this.alarm_type = alarm_type;
	}
	
}
