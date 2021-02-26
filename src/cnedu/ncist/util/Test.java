package cnedu.ncist.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;



public class Test {
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		Map<String, String> map1 = new HashMap<String, String>();
		Map<String, String> map2 = new HashMap<String, String>();
		map.put("YEAR_2018_sore", "2018");
		map.put("YEAR_2019_sore", "2019");
		map.put("YEAR_2016_sore", "2016");
		System.out.println(map);
		Map<String, String> sortMapByKey = sortMapByKey(map);
		for (Map.Entry<String, String> entry :sortMapByKey.entrySet()) {
			System.out.println(entry);
		}

	}
	
	public static Map<String, String> sortMapByKey(Map<String, String> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}
		Map<String, String> sortMap = new TreeMap<String, String>(new MapKeyComparator());
		sortMap.putAll(map);
		return sortMap;
	}

}

