package com.main.captain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.main.captain.bean.WarehouseBean;

/**
 * 订单生成工厂类
 * @author Administrator
 *
 */
public class Factory {
	
	private List<WarehouseBean> warehouseList;
	
	public Factory() {
		warehouseList = new ArrayList<WarehouseBean>(3);
		Map<String, Integer> productMap1 = new HashMap<String, Integer>(3);
		productMap1.put("A", 1);
		productMap1.put("B", 2);
		productMap1.put("C", 2);
		WarehouseBean w1 = new WarehouseBean("w1",2,3,productMap1); //距离w2,3优先
		//WarehouseBean w1 = new WarehouseBean("w1",1,3,productMap1); //距离w1优先
		warehouseList.add(w1);
		
		Map<String, Integer> productMap2 = new HashMap<String, Integer>(3);
		productMap2.put("A", 2);
		productMap2.put("B", 1);
		productMap2.put("C", 2);
		WarehouseBean w2 = new WarehouseBean("w2",1,2,productMap2); //距离w2,3优先
		//WarehouseBean w2 = new WarehouseBean("w2",2,2,productMap2); //距离w1优先
		warehouseList.add(w2);
		
		Map<String, Integer> productMap3 = new HashMap<String, Integer>(3);
		productMap3.put("A", 1);
		productMap3.put("B", 3);
		productMap3.put("C", 1);
		WarehouseBean w3 = new WarehouseBean("w3",1,1,productMap3); //距离w2,3优先
		//WarehouseBean w3 = new WarehouseBean("w3",2,1,productMap3); //距离w1优先
		warehouseList.add(w3);
	}
	
	/**
	 * 获取订单明细
	 * @param productName 产品名称
	 * @param productNum 产品数量
	 * @return
	 */
	public List<String> getOrder(String productName, int productNum){
		Map<String,Integer> warehouseMap = new HashMap<String,Integer>();
		for(int cnt = 0;cnt<productNum;cnt++) {
			String wname = getOneOrder(productName);
			if(wname==null) {
				break;
			}else{
				if(warehouseMap.get(wname)==null) {
					warehouseMap.put(wname, new Integer(1));
				}else{
					int tempcnt  = warehouseMap.get(wname)+1;
					warehouseMap.put(wname, tempcnt);
				}
			}
		}
		
		if(warehouseMap.size()==0)
			return null;
		
		List<String> data = new ArrayList<String>(warehouseMap.size());
		int cnt = 1;
		for(String key : warehouseMap.keySet()) {
			data.add(cnt+"\t"+productName+"\t"+warehouseMap.get(key)+"\t"+key);
			cnt++;
		}
		return data;
	}
	
	/**
	 * 获取一个产品的仓库名称
	 * @param productName
	 * @return
	 */
	private String getOneOrder(String productName) {
		//获取距离最优仓库集合
		List<WarehouseBean> listData = new ArrayList<WarehouseBean>();
		int dlevel = 2;
		for(WarehouseBean warehouseBean : warehouseList) {
			if(warehouseBean.getProductMap().get(productName)>0) {
				if(warehouseBean.getdLevel()==dlevel) {
					listData.add(warehouseBean);
				}else if(warehouseBean.getdLevel()<dlevel){
					listData.clear();
					listData.add(warehouseBean);
					dlevel = warehouseBean.getdLevel();
				}
			}
		}
		
		if(listData.size()==0)
			return null;
		WarehouseBean tempBean = null;
		if(listData.size()==1){
			tempBean = listData.get(0);
		}else{
			tempBean = getWarehouseBeanForLevel(listData);
		}
		int cnt = tempBean.getProductMap().get(productName)-1;
		tempBean.getProductMap().put(productName,cnt);
		return tempBean.getWname();
	}
	
	/**
	 * 获取仓库优先级最高的仓库
	 * @param listData
	 * @return
	 */
	private WarehouseBean getWarehouseBeanForLevel(List<WarehouseBean> listData) {
		WarehouseBean warehouseBean = listData.get(0);
		for(WarehouseBean tempBean : listData){
			if(tempBean.getwLevel()<warehouseBean.getwLevel())
				warehouseBean = tempBean;
		}
		return warehouseBean;
	}

}
