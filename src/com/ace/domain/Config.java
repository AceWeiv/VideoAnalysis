package com.ace.domain;

public class Config {

	private int id;
	
	private String name;
	
	private String adress;
	
	private boolean isActive;
	
	private int threshold_car;
	
	private int threshold_man;

	public int getThreshold_car() {
		return threshold_car;
	}

	public void setThreshold_car(int threshold_car) {
		this.threshold_car = threshold_car;
	}

	public int getThreshold_man() {
		return threshold_man;
	}

	public void setThreshold_man(int threshold_man) {
		this.threshold_man = threshold_man;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
}
