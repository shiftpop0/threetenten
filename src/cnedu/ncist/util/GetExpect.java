package cnedu.ncist.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * 指数平滑法的基本公式：
 * St=ayt+(1-a)St-1 
 * 式中，
 *  St--时间t的平滑值； 
 *  yt--时间t的实际值； St-1--时间t-1的平滑值；
 *  a--平滑常数，其取值范围为[0,1]
 *
 * 二次指数平滑法求预测值
 * 
 * @param list
 *            基础数据集合
 * @param year
 *            未来第几期
 * @param modulus
 *            平滑系数
 * @return 预测值
 */
public class GetExpect {
	public Map<String, String> getExpect(Map<String, String> map, List<Map<String, String>> yearList, int year,
			Double modulus) {
		if (modulus <= 0 || modulus >= 1) {
			return null;
		}
		
		//剔除了分数为0的年份
		Map<String, String> map2 = new HashMap<String, String>(map);
		Iterator<String> iterator = map2.keySet().iterator();
	    List<String> valueList = new ArrayList<String>();
	    //用来存放最早年份的分数
	    String firstscorce = null;
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			if ("0".equals(map2.get(key)) || "ZYDH".equals(key) || "DQMC".equals(key) || "ZYMC".equals(key)) {
				iterator.remove();
				map2.remove(key);
			}
		}
		//找出最早年份
		Map<String, String> sortMapByKey2 = sortMapByKey(map2);
		for (Map.Entry<String, String> entry : sortMapByKey2.entrySet()) {
			firstscorce = entry.getValue();
			break;
		}

		// 所要预测的年份
		Integer lastYear = Integer.valueOf(yearList.get(yearList.size() - 1).get("YEAR1")) + 1;
		
		Double modulusLeft = 1 - modulus;
		Map<String, String> sortMapByKey = sortMapByKey(map);
		//给定初始值
		Integer lastIndex = Integer.valueOf(firstscorce);
		Integer lastSecIndex = Integer.valueOf(firstscorce);
		//遍历Map剔除Map中不需要的Key
		for (Map.Entry<String, String> entry : sortMapByKey.entrySet()) {
			if (!"ZYMC".equals(entry.getKey()) && !"0".equals(entry.getValue()) && !"ZYDH".equals(entry.getKey())
					&& !"DQMC".equals(entry.getKey())) {
				lastIndex = (int) (modulus * Integer.valueOf(entry.getValue()) + modulusLeft * lastIndex);
				lastSecIndex = (int) (modulus * lastIndex + modulusLeft * lastSecIndex);
			}
		}
		Integer a = 2 * lastIndex - lastSecIndex;
		Integer b = (int) ((modulus / modulusLeft) * (lastIndex - lastSecIndex));
		Integer c = a + (b * year);
		map.put("YEAR_" + lastYear + "_score", c.toString());
		return map;
	}

	// 改造SQL语句根据传来的年份数组构造新的sql
	public String appendString(List<Map<String, String>> yearList) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT a.ZYMC,a.ZYDH,a.KLMC, ");
		for (int i = 0; i < yearList.size(); i++) {
			sql.append("IFNULL(year_").append(yearList.get(i).get("YEAR1")).append(".avg_cj, 0) AS YEAR_")
					.append(yearList.get(i).get("YEAR1")).append("_score ").append(",");
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append("FROM(SELECT ZYDH,ZYMC,KLDM,KLMC FROM sheet2 GROUP BY ZYDH) AS a ");
		for (int i = 0; i < yearList.size(); i++) {
			sql.append("LEFT JOIN (SELECT enter_student.LQZY,ROUND(AVG(enter_student.CJ)) AS avg_cj, "
					+ "major.ZYMC,YEAR1 FROM enter_student,major WHERE major.ZYDH = enter_student.LQZY "
					+ "AND YEAR1 =").append(yearList.get(i).get("YEAR1")).append(" GROUP BY ZYDH,YEAR1) AS year_")
					.append(yearList.get(i).get("YEAR1")).append(" ON year_").append(yearList.get(i).get("YEAR1"))
					.append(".LQZY = a.ZYDH ");
		}
		return sql.toString();
	}

	// 改造SQL语句根据传来的年份数组构造新的sql---带上了专业
	public String appendStringWithMajor(List<Map<String, String>> yearList, String major, String majorType) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT a.ZYMC,a.ZYDH,a.KLMC, ");
		for (int i = 0; i < yearList.size(); i++) {
			sql.append("IFNULL(year_").append(yearList.get(i).get("YEAR1")).append(".avg_cj, 0) AS YEAR_")
					.append(yearList.get(i).get("YEAR1")).append("_score ").append(",");
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append("FROM(SELECT ZYDH,ZYMC,KLDM,KLMC FROM sheet2 WHERE KLDM = '" + majorType + "' " + "AND ZYMC = '"
				+ major + "' GROUP BY ZYDH) AS a ");
		for (int i = 0; i < yearList.size(); i++) {
			sql.append("LEFT JOIN (SELECT enter_student.LQZY,ROUND(AVG(enter_student.CJ)) AS avg_cj, "
					+ "major.ZYMC,YEAR1 FROM enter_student,major WHERE major.ZYDH = enter_student.LQZY "
					+ "AND YEAR1 =").append(yearList.get(i).get("YEAR1")).append(" GROUP BY ZYDH,YEAR1) AS year_")
					.append(yearList.get(i).get("YEAR1")).append(" ON year_").append(yearList.get(i).get("YEAR1"))
					.append(".LQZY = a.ZYDH ");
		}
		return sql.toString();
	}
	
//	对map利用key排序
	public static Map<String, String> sortMapByKey(Map<String, String> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}
		Map<String, String> sortMap = new TreeMap<String, String>(new MapKeyComparator());
		sortMap.putAll(map);
		return sortMap;
	}
	
}

//实现一个比较器类
	class MapKeyComparator implements Comparator<String> {
		 
		public int compare(String s1, String s2) {
			return s1.compareTo(s2);  //从小到大排序
		}
	}



