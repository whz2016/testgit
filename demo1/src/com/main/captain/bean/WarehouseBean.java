package com.main.captain.bean;

import java.util.Map;

/**
 * 仓库bean
 * @author Administrator
 *
 */
public class WarehouseBean {
	
	private String wname; //仓库名称
	
	private int dLevel; //距离优先级,数字小优先
	
	private int wLevel; //仓库优先级,数字小优先
	
	private Map<String, Integer> productMap; //库存集合
	
	public WarehouseBean(String wname, int dLevel, int wLevel, Map<String, Integer> productMap) {
		this.wname = wname;
		this.dLevel = dLevel;
		this.wLevel = wLevel;
		this.productMap = productMap;
	}

	public String getWname() {
		return wname;
	}

	public void setWname(String wname) {
		this.wname = wname;
	}

	public int getdLevel() {
		return dLevel;
	}

	public void setdLevel(int dLevel) {
		this.dLevel = dLevel;
	}

	public int getwLevel() {
		return wLevel;
	}

	public void setwLevel(int wLevel) {
		this.wLevel = wLevel;
	}

	public Map<String, Integer> getProductMap() {
		return productMap;
	}

	public void setProductMap(Map<String, Integer> productMap) {
		this.productMap = productMap;
	}

}
