package com.test.captain;

import java.util.List;

import org.junit.Test;

import com.main.captain.Factory;

public class FactoryTest {
	
	@Test
	public void getOrderTest(){
		Factory factory = new Factory();
		List<String> dataList = factory.getOrder("A", 2);
		if(dataList!=null){
			System.out.println("发货单号\t商品名称\t数量\t出货仓库");
			for(String str : dataList)
				System.out.println(str);
		}
		dataList = factory.getOrder("B", 4);
		if(dataList!=null){
			System.out.println("发货单号\t商品名称\t数量\t出货仓库");
			for(String str : dataList)
				System.out.println(str);
		}
	}

}
