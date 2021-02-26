package cnedu.ncist.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChangeList {
	public List<Map<String, String>> change(List<Map<String, String>> changeList,List<Map<String, String>> yearList){
//		System.out.println("chanage="+changeList);
//		System.out.println("chanageyer="+yearList);
		List<Map<String, String>> yList = new ArrayList<Map<String,String>>(yearList);
		
		//用来存放需要预测的数据
		Map<String, String> map = new HashMap<String, String>();
		List<Map<String, String>> mapList = new ArrayList<Map<String,String>>();
		map.put("ZYMC", changeList.get(0).get("ZYMC"));
		map.put("ZYDH", changeList.get(0).get("LQZY"));
		map.put("DQMC", changeList.get(0).get("DQMC"));
		for (int i = 0; i < changeList.size(); i++) {
			for (int j = 0; j < yList.size(); j++) {
//				System.out.println("--------------------------------");
//				System.out.println("changeList.get(i)="+changeList.get(i).get("YEAR1"));
//				System.out.println("yearList.get(j)="+yearList.get(j).get("YEAR1"));
				if (changeList.get(i).get("YEAR1").equals(yList.get(j).get("YEAR1"))) {
					map.put("YEAR_"+changeList.get(i).get("YEAR1")+"_sorce",changeList.get(i).get("avg_cj"));
					yList.remove(j);
				}
			}
		}
		for (int i = 0; i < yList.size(); i++) {
			map.put("YEAR_"+yList.get(i).get("YEAR1")+"_sorce","0" );
		}
//		System.out.println("yList=="+yList);
		
//		System.out.println("===================");
//		System.out.println(map);
		mapList.add(map);
		return mapList;
	}
}

