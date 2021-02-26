package cnedu.ncist.enterpage.service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import edu.ncist.wang.hkdf.bussiness.blo.AbstractService;
import edu.ncist.wang.hkdf.dao.database.DBConnectionException;
import edu.ncist.wang.hkdf.dao.database.DataBaseAccess;
import edu.ncist.wang.hkdf.dao.database.DataManipulationException;

public class EnterpageService extends AbstractService {
	public Map doServiceProcess(Map paraMap) {

		String actionType = (String) paraMap.get("ACTIONTYPE");

		if ("enterpage_Select1".equals(actionType)) {
			return enterpage_select1(paraMap);
		} else if ("enterpage_Select2".equals(actionType)) {
			return enterpage_select2(paraMap);
		} else if ("enterpage_Select3".equals(actionType)) {
			return enterpage_select3(paraMap);
		} else if ("enterpage_Select4".equals(actionType)) {
			return enterpage_select4(paraMap);
		} else if ("enterpage_Select1Family".equals(actionType)) {
			return enterpage_select1Family(paraMap);
		} else if ("enterpage_Select2Major".equals(actionType)) {
			return enterpage_select2Major(paraMap);
		}else if ("enterpage_Select3Family".equals(actionType)) {
			return enterpage_select3Family(paraMap);
		} else if ("enterpage_Select4Major".equals(actionType)) {
			return enterpage_select4Major(paraMap);
		}else if ("enterpage_Select5".equals(actionType)) {
			return enterpage_select5(paraMap);
		}else if ("enterpage_Select6".equals(actionType)) {
			return enterpage_select6(paraMap);
		}else if ("enterpage_Select7".equals(actionType)) {
			return enterpage_select7(paraMap);
		}else if ("enterpage_Select8".equals(actionType)) {
			return enterpage_select8(paraMap);
		}else if ("enterpage_SelectAll".equals(actionType)) {
			return enterpage_selectAll(paraMap);
		}else if ("enterpage_SelectAll1".equals(actionType)) {
			return enterpage_selectAll1(paraMap);
		}else if ("enterpage_SelectEnroll".equals(actionType)) {
			return enterpage_selectEnroll(paraMap);
		}
		else {
			ActionTypeError();
		}

		return null;

	}

	public Map enterpage_select1(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");

		Map<String, Object> resultMap = new HashMap<String, Object>();

		List<Map<String, String>> userList = null;
		List<Map<String, String>> familyList = null;
		List<Map<String, String>> typeList = null;

		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");

		String sql1 = "Select stuMajor1,majorType,majorName,stuyear,Count(stuMajor1) as sum,Avg(stuScoure) as savg"
						+" From enter_student_year,major"
						+" Where stuMajor1 = majorID"
						+" Group by majorName,stuyear"
						+" Order by stuyear,sum desc";
		String sql2 = "Select majorName,majorID from major";
		String sql3 = "Select typeName From majorType";
		
		try {
			userList = dba.getMultiRecord(sql1);
			familyList = dba.getMultiRecord(sql2);
			typeList = dba.getMultiRecord(sql3);
			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (null == userList) {
			resultMap.put("RESULT", "fail");
		} else {
			resultMap.put("RESULT", "success");
		}
		
		int x = 0;
		int z = 0;
		int n = 0;
		for(int i=0;i<userList.size();i++){
			int y = Integer.parseInt(userList.get(i).get("sum"));
			if(userList.get(i).get("stuyear").equals("2017")){
				x = x + y;
			}else if(userList.get(i).get("stuyear").equals("2018")){
				z = z + y;
			}else{
				n = n + y;
			}
		}

		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMaximumFractionDigits(2);
		String result = "1";
		for(int i=0;i<userList.size();i++){
			int y = Integer.parseInt(userList.get(i).get("sum"));
			if(userList.get(i).get("stuyear").equals("2017")){
				result = numberFormat.format((float)y/(float)x*100);
			}else if(userList.get(i).get("stuyear").equals("2018")){
				result = numberFormat.format((float)y/(float)z*100);
			}else{
				result = numberFormat.format((float)y/(float)n*100);
			}
			
			userList.get(i).put("rate", result+"%");
		}
		
		resultMap.put("USERLIST", userList);
		resultMap.put("FAMILYLIST", familyList);
		resultMap.put("TYPELIST", typeList);
		return resultMap;
	}

	public Map enterpage_select2(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");

		Map<String, Object> resultMap = new HashMap<String, Object>();

		List<Map<String, String>> userList = null;
		List<Map<String, String>> sumList = null;
		List<Map<String, String>> majorList = null;
		List<Map<String, String>> typeList = null;
		List<Map<String, String>> majorList2 = null;
		List<Map<String, String>> hotList = null;
		
		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");

		String sql1 = "Select stuMajor1,hot1,majorType,stuyear,enroll,Count(wish1) as wish1s "
						+"From enter_student_year,major "
						+"Where enroll = wish1 and stuMajor1 = majorID "
						+"Group by enroll,stuyear "
						+"Order by stuyear,enroll desc";
		String sql2 = "Select stuyear,Count(*) as sum From enter_student_year Group by stuyear Order by stuyear ";
		String sql4 = "Select majorName from major";
		String sql3 = "Select typeName From majorType";
		String sql5 = "Select stuMajor1,majorName,stuyear,Count(majorName) as sum " +
				"From enter_student_year,major Where stuMajor1 = majorID Group by majorName,stuyear Order by stuyear,sum desc";
		String sql8 = "Select hotName From hot";
		
		try {
			majorList2 = dba.getMultiRecord(sql5);
			sumList = dba.getMultiRecord(sql2);
			
			NumberFormat numberFormat = NumberFormat.getInstance();
			numberFormat.setMaximumFractionDigits(2);
			int x = 0;
			int a = 0;
			int b = 0;
			
			for(int i=0;i<sumList.size();i++){
				if(sumList.get(i).get("stuyear").equals("2017")){
					x = Integer.parseInt(sumList.get(i).get("sum"));
				}else if(sumList.get(i).get("stuyear").equals("2018")){
					a = Integer.parseInt(sumList.get(i).get("sum"));
				}else{
					b = Integer.parseInt(sumList.get(i).get("sum"));
				}
			}
			
			for(int i=0;i<majorList2.size();i++){
				float result1 = 0;
				String hot = "";
				int y = Integer.parseInt(majorList2.get(i).get("sum"));

				if(majorList2.get(i).get("stuyear").equals("2017")){
					result1 = Float.parseFloat(numberFormat.format((float)y/(float)x*100));
				}else if(majorList2.get(i).get("stuyear").equals("2018")){
					result1 = Float.parseFloat(numberFormat.format((float)y/(float)a*100));
				}else{
					result1 = Float.parseFloat(numberFormat.format((float)y/(float)b*100));
				}
				
				if(result1 >= 30){
					hot = "火爆";
				}else if(result1 > 20 && result1 < 30){
					hot = "热门";
				}else{
					hot = "冷门";
				}
				
				majorList2.get(i).put("hot", hot);
			}

			String sql6 = "Update enter_student_year Set hot =  Where stuMajor1 = ";
			for(int i=0;i<majorList2.size();i++){
				sql6 = "Update enter_student_year Set hot1 = '"+majorList2.get(i).get("hot")+"' " +
						"Where stuMajor1 = '"+majorList2.get(i).get("stuMajor1")+"' and stuyear = '"+majorList2.get(i).get("stuyear")+"'";
				//System.out.println(sql6);
				dba.executeUpdate(sql6);
			}
			
			userList = dba.getMultiRecord(sql1);
			majorList = dba.getMultiRecord(sql4);
			typeList = dba.getMultiRecord(sql3);
			hotList = dba.getMultiRecord(sql8);
			
			String result = "1";
			
			for(int i=0;i<userList.size();i++){
				int y = Integer.parseInt(userList.get(i).get("wish1s"));

				if(userList.get(i).get("stuyear").equals("2017")){
					result = numberFormat.format((float)y/(float)x*100);
				}else if(userList.get(i).get("stuyear").equals("2018")){
					result = numberFormat.format((float)y/(float)a*100);
				}else{
					result = numberFormat.format((float)y/(float)b*100);
				}
				
				userList.get(i).put("rate", result+"%");
			}
				
				
			System.out.println(userList);
			System.out.println(majorList2);
			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (null == userList) {
			resultMap.put("RESULT", "fail");
		} else {
			resultMap.put("RESULT", "success");
		}
		resultMap.put("USERLIST", userList);
		resultMap.put("SUMLIST", sumList);
		resultMap.put("MAJORLIST", majorList);
		resultMap.put("TYPELIST", typeList);
		resultMap.put("HOTLIST", hotList);
		return resultMap;
	}

	public Map enterpage_select3(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");

		Map<String, Object> resultMap = new HashMap<String, Object>();

		List<Map<String, String>> userList = null;
		List<Map<String, String>> sumList = null;
		List<Map<String, String>> majorList = null;
		List<Map<String, String>> typeList = null;
		List<Map<String, String>> majorList2 = null;
		List<Map<String, String>> hotList = null;
		
		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");

		String sql1 = "Select stuMajor1,hot1,majorType,stuyear,enroll,Count(wish1) as wish1s "
						+"From enter_student_year,major "
						+"Where enroll = wish1 and stuMajor1 = majorID "
						+"Group by enroll,stuyear "
						+"Order by stuyear,enroll desc";
		String sql2 = "Select stuyear,Count(*) as sum From enter_student_year Group by stuyear Order by stuyear ";
		String sql4 = "Select majorName from major";
		String sql3 = "Select typeName From majorType";
		String sql5 = "Select stuMajor1,majorName,stuyear,Count(majorName) as sum " +
				"From enter_student_year,major Where stuMajor1 = majorID Group by majorName,stuyear Order by stuyear,sum desc";
		String sql8 = "Select hotName From hot";
		
		try {
			majorList2 = dba.getMultiRecord(sql5);
			sumList = dba.getMultiRecord(sql2);
			
			NumberFormat numberFormat = NumberFormat.getInstance();
			numberFormat.setMaximumFractionDigits(2);
			int x = 0;
			int a = 0;
			int b = 0;
			
			for(int i=0;i<sumList.size();i++){
				if(sumList.get(i).get("stuyear").equals("2017")){
					x = Integer.parseInt(sumList.get(i).get("sum"));
				}else if(sumList.get(i).get("stuyear").equals("2018")){
					a = Integer.parseInt(sumList.get(i).get("sum"));
				}else{
					b = Integer.parseInt(sumList.get(i).get("sum"));
				}
			}
			
			for(int i=0;i<majorList2.size();i++){
				float result1 = 0;
				String hot = "";
				int y = Integer.parseInt(majorList2.get(i).get("sum"));

				if(majorList2.get(i).get("stuyear").equals("2017")){
					result1 = Float.parseFloat(numberFormat.format((float)y/(float)x*100));
				}else if(majorList2.get(i).get("stuyear").equals("2018")){
					result1 = Float.parseFloat(numberFormat.format((float)y/(float)a*100));
				}else{
					result1 = Float.parseFloat(numberFormat.format((float)y/(float)b*100));
				}
				
				if(result1 >= 30){
					hot = "火爆";
				}else if(result1 > 20 && result1 < 30){
					hot = "热门";
				}else{
					hot = "冷门";
				}
				
				majorList2.get(i).put("hot", hot);
			}

			String sql6 = "Update enter_student_year Set hot =  Where stuMajor1 = ";
			for(int i=0;i<majorList2.size();i++){
				sql6 = "Update enter_student_year Set hot1 = '"+majorList2.get(i).get("hot")+"' " +
						"Where stuMajor1 = '"+majorList2.get(i).get("stuMajor1")+"' and stuyear = '"+majorList2.get(i).get("stuyear")+"'";
				//System.out.println(sql6);
				dba.executeUpdate(sql6);
			}
			
			userList = dba.getMultiRecord(sql1);
			majorList = dba.getMultiRecord(sql4);
			typeList = dba.getMultiRecord(sql3);
			hotList = dba.getMultiRecord(sql8);
			
			String result = "1";
			
			for(int i=0;i<userList.size();i++){
				int y = Integer.parseInt(userList.get(i).get("wish1s"));

				if(userList.get(i).get("stuyear").equals("2017")){
					result = numberFormat.format((float)y/(float)x*100);
				}else if(userList.get(i).get("stuyear").equals("2018")){
					result = numberFormat.format((float)y/(float)a*100);
				}else{
					result = numberFormat.format((float)y/(float)b*100);
				}
				
				userList.get(i).put("rate", result+"%");
			}
				
				
			System.out.println(userList);
			System.out.println(majorList2);
			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (null == userList) {
			resultMap.put("RESULT", "fail");
		} else {
			resultMap.put("RESULT", "success");
		}
		resultMap.put("USERLIST", userList);
		resultMap.put("SUMLIST", sumList);
		resultMap.put("MAJORLIST", majorList);
		resultMap.put("TYPELIST", typeList);
		resultMap.put("HOTLIST", hotList);
		return resultMap;
	}

	public Map enterpage_select4(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");

		Map<String, Object> resultMap = new HashMap<String, Object>();

		List<Map<String, String>> userList = null;
		List<Map<String, String>> sumList = null;
		List<Map<String, String>> majorList = null;
		List<Map<String, String>> typeList = null;
		List<Map<String, String>> majorList2 = null;
		List<Map<String, String>> hotList = null;
		
		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");

		String sql1 = "Select stuMajor1,hot1,majorType,stuyear,enroll,Count(wish1) as wish1s "
						+"From enter_student_year,major "
						+"Where enroll = wish1 and stuMajor1 = majorID "
						+"Group by enroll,stuyear "
						+"Order by stuyear,enroll desc";
		String sql2 = "Select stuyear,Count(*) as sum From enter_student_year Group by stuyear Order by stuyear ";
		String sql4 = "Select majorName from major";
		String sql3 = "Select typeName From majorType";
		String sql5 = "Select stuMajor1,majorName,stuyear,Count(majorName) as sum " +
				"From enter_student_year,major Where stuMajor1 = majorID Group by majorName,stuyear Order by stuyear,sum desc";
		String sql8 = "Select hotName From hot";
		
		try {
			majorList2 = dba.getMultiRecord(sql5);
			sumList = dba.getMultiRecord(sql2);
			
			NumberFormat numberFormat = NumberFormat.getInstance();
			numberFormat.setMaximumFractionDigits(2);
			int x = 0;
			int a = 0;
			int b = 0;
			
			for(int i=0;i<sumList.size();i++){
				if(sumList.get(i).get("stuyear").equals("2017")){
					x = Integer.parseInt(sumList.get(i).get("sum"));
				}else if(sumList.get(i).get("stuyear").equals("2018")){
					a = Integer.parseInt(sumList.get(i).get("sum"));
				}else{
					b = Integer.parseInt(sumList.get(i).get("sum"));
				}
			}
			
			for(int i=0;i<majorList2.size();i++){
				float result1 = 0;
				String hot = "";
				int y = Integer.parseInt(majorList2.get(i).get("sum"));

				if(majorList2.get(i).get("stuyear").equals("2017")){
					result1 = Float.parseFloat(numberFormat.format((float)y/(float)x*100));
				}else if(majorList2.get(i).get("stuyear").equals("2018")){
					result1 = Float.parseFloat(numberFormat.format((float)y/(float)a*100));
				}else{
					result1 = Float.parseFloat(numberFormat.format((float)y/(float)b*100));
				}
				
				if(result1 >= 30){
					hot = "火爆";
				}else if(result1 > 20 && result1 < 30){
					hot = "热门";
				}else{
					hot = "冷门";
				}
				
				majorList2.get(i).put("hot", hot);
			}

			String sql6 = "Update enter_student_year Set hot =  Where stuMajor1 = ";
			for(int i=0;i<majorList2.size();i++){
				sql6 = "Update enter_student_year Set hot1 = '"+majorList2.get(i).get("hot")+"' " +
						"Where stuMajor1 = '"+majorList2.get(i).get("stuMajor1")+"' and stuyear = '"+majorList2.get(i).get("stuyear")+"'";
				//System.out.println(sql6);
				dba.executeUpdate(sql6);
			}
			
			userList = dba.getMultiRecord(sql1);
			majorList = dba.getMultiRecord(sql4);
			typeList = dba.getMultiRecord(sql3);
			hotList = dba.getMultiRecord(sql8);
			
			String result = "1";
			
			for(int i=0;i<userList.size();i++){
				int y = Integer.parseInt(userList.get(i).get("wish1s"));

				if(userList.get(i).get("stuyear").equals("2017")){
					result = numberFormat.format((float)y/(float)x*100);
				}else if(userList.get(i).get("stuyear").equals("2018")){
					result = numberFormat.format((float)y/(float)a*100);
				}else{
					result = numberFormat.format((float)y/(float)b*100);
				}
				
				userList.get(i).put("rate", result+"%");
			}
				
				
			System.out.println(userList);
			System.out.println(majorList2);
			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (null == userList) {
			resultMap.put("RESULT", "fail");
		} else {
			resultMap.put("RESULT", "success");
		}
		resultMap.put("USERLIST", userList);
		resultMap.put("SUMLIST", sumList);
		resultMap.put("MAJORLIST", majorList);
		resultMap.put("TYPELIST", typeList);
		resultMap.put("HOTLIST", hotList);
		return resultMap;
	}

	public Map enterpage_select1Family(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map map = (Map) paraMap.get("CONDITIONMAP");

		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");

		List<Map<String, String>> userList = null;
		List<Map<String, String>> familyList = null;
		List<Map<String, String>> typeList = null;

		String sql = null;
		String sql2 = "Select majorName,majorID from major";
		String sql3 = "Select typeName From majorType";

		String type = (String) map.get("TYPE");
		String major = (String) map.get("MAJOR");
		
		if(type.equals("all")){
			type = null;
		}
		if(major.equals("all")){
			major = null;
		}
		sql = "Select stuMajor1,rate,majorType,majorName,stuyear,Count(stuMajor1) as sum,Avg(stuScoure) as savg"
				+" From enter_student_year,major"
				+" Where stuMajor1 = majorID";
		if (type != null) {
			sql = sql + " and majorType = '"+type+"'";
		}
		if(major != null){
			sql = sql + " and stuMajor1 = '"+major+"'";
		}
		
		sql = sql +" Group by majorName,stuyear";
		sql = sql +" Order by stuyear,sum desc";

		try {
			familyList = dba.getMultiRecord(sql2);
			userList = dba.getMultiRecord(sql);
			typeList = dba.getMultiRecord(sql3);
			System.out.println(userList);
			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (null == userList) {
			resultMap.put("RESULT", "fail");
		} else {
			resultMap.put("RESULT", "success");
		}
		
		resultMap.put("FAMILYLIST", familyList);
		resultMap.put("USERLIST", userList);
		resultMap.put("TYPELIST", typeList);

		return resultMap;
	}
	public Map enterpage_select2Major(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map map = (Map) paraMap.get("CONDITIONMAP");

		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");

		List<Map<String, String>> userList = null;
		List<Map<String, String>> majorList = null;
		List<Map<String, String>> typeList = null;
		List<Map<String, String>> hotList = null;
		List<Map<String, String>> sumList = null;
		
		String major = (String) map.get("MAJOR");
		String type = (String) map.get("TYPE");
		String hot = (String)map.get("HOT");

		String sql1 = "Select stuMajor1,hot1,majorType,stuyear,enroll,Count(wish1) as wish1s "
			+"From enter_student_year,major "
			+"Where enroll = wish1 and stuMajor1 = majorID ";

		String sql2 = "Select majorName from major";
		String sql3 = "Select hotName From hot";
		String sql4 = "Select typeName from majorType";
		String sql5 = "Select stuyear,Count(*) as sum From enter_student_year Group by stuyear Order by stuyear ";
		
		
		if(major.equals("all")){
			major = null;
		}
		if(type.equals("all")){
			type = null;
		}
		if(hot.equals("all")){
			hot = null;
		}
		
		if (major != null) {
			sql1 = sql1 + " and enroll = '"+major+"'";
		}
		if (type != null) {
			sql1 = sql1 + " and majorType = '"+type+"'";	
		}
		if(hot != null){
			sql1 = sql1 + " and hot1 = '"+hot+"'";
		}
		
		sql1 = sql1 + " Group by enroll,stuyear Order by stuyear,enroll desc";
		
		System.out.println(major);
		System.out.println(type);
		System.out.println(hot);
		System.out.println(sql1);
		try {
			majorList = dba.getMultiRecord(sql2);
			userList = dba.getMultiRecord(sql1);
			typeList = dba.getMultiRecord(sql4);
			hotList = dba.getMultiRecord(sql3);
			sumList = dba.getMultiRecord(sql5);
			
			NumberFormat numberFormat = NumberFormat.getInstance();
			numberFormat.setMaximumFractionDigits(2);
			int x = 0;
			int a = 0;
			int b = 0;
			
			String result = "";
			for(int i=0;i<sumList.size();i++){
				if(sumList.get(i).get("stuyear").equals("2017")){
					x = Integer.parseInt(sumList.get(i).get("sum"));
				}else if(sumList.get(i).get("stuyear").equals("2018")){
					a = Integer.parseInt(sumList.get(i).get("sum"));
				}else{
					b = Integer.parseInt(sumList.get(i).get("sum"));
				}
			}
			
			for(int i=0;i<userList.size();i++){
				int y = Integer.parseInt(userList.get(i).get("wish1s"));

				if(userList.get(i).get("stuyear").equals("2017")){
					result = numberFormat.format((float)y/(float)x*100);
				}else if(userList.get(i).get("stuyear").equals("2018")){
					result = numberFormat.format((float)y/(float)a*100);
				}else{
					result = numberFormat.format((float)y/(float)b*100);
				}
				
				userList.get(i).put("rate", result+"%");
			}
			
			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (null == userList) {
			resultMap.put("RESULT", "fail");
		} else {
			resultMap.put("RESULT", "success");
		}
		
		resultMap.put("FMAJORLIST", majorList);
		resultMap.put("USERLIST", userList);
		resultMap.put("TYPELIST", typeList);
		resultMap.put("HOTLIST", hotList);
		
		System.out.println(userList+"-------------");
		return resultMap;
	}
	
	public Map enterpage_select3Family(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map map = (Map) paraMap.get("CONDITIONMAP");

		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");

		List<Map<String, String>> userList = null;
		List<Map<String, String>> majorList = null;
		List<Map<String, String>> typeList = null;
		List<Map<String, String>> hotList = null;
		List<Map<String, String>> sumList = null;
		
		String major = (String) map.get("MAJOR");
		String type = (String) map.get("TYPE");
		String hot = (String)map.get("HOT");

		String sql1 = "Select stuMajor1,hot1,majorType,stuyear,enroll,Count(wish1) as wish1s "
			+"From enter_student_year,major "
			+"Where enroll = wish1 and stuMajor1 = majorID ";

		String sql2 = "Select majorName from major";
		String sql3 = "Select hotName From hot";
		String sql4 = "Select typeName from majorType";
		String sql5 = "Select stuyear,Count(*) as sum From enter_student_year Group by stuyear Order by stuyear ";
		
		
		if(major.equals("all")){
			major = null;
		}
		if(type.equals("all")){
			type = null;
		}
		if(hot.equals("all")){
			hot = null;
		}
		
		if (major != null) {
			sql1 = sql1 + " and enroll = '"+major+"'";
		}
		if (type != null) {
			sql1 = sql1 + " and majorType = '"+type+"'";	
		}
		if(hot != null){
			sql1 = sql1 + " and hot1 = '"+hot+"'";
		}
		
		sql1 = sql1 + " Group by enroll,stuyear Order by stuyear,enroll desc";
		
		System.out.println(major);
		System.out.println(type);
		System.out.println(hot);
		System.out.println(sql1);
		try {
			majorList = dba.getMultiRecord(sql2);
			userList = dba.getMultiRecord(sql1);
			typeList = dba.getMultiRecord(sql4);
			hotList = dba.getMultiRecord(sql3);
			sumList = dba.getMultiRecord(sql5);
			
			NumberFormat numberFormat = NumberFormat.getInstance();
			numberFormat.setMaximumFractionDigits(2);
			int x = 0;
			int a = 0;
			int b = 0;
			
			String result = "";
			for(int i=0;i<sumList.size();i++){
				if(sumList.get(i).get("stuyear").equals("2017")){
					x = Integer.parseInt(sumList.get(i).get("sum"));
				}else if(sumList.get(i).get("stuyear").equals("2018")){
					a = Integer.parseInt(sumList.get(i).get("sum"));
				}else{
					b = Integer.parseInt(sumList.get(i).get("sum"));
				}
			}
			
			for(int i=0;i<userList.size();i++){
				int y = Integer.parseInt(userList.get(i).get("wish1s"));

				if(userList.get(i).get("stuyear").equals("2017")){
					result = numberFormat.format((float)y/(float)x*100);
				}else if(userList.get(i).get("stuyear").equals("2018")){
					result = numberFormat.format((float)y/(float)a*100);
				}else{
					result = numberFormat.format((float)y/(float)b*100);
				}
				
				userList.get(i).put("rate", result+"%");
			}
			
			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (null == userList) {
			resultMap.put("RESULT", "fail");
		} else {
			resultMap.put("RESULT", "success");
		}
		
		resultMap.put("FMAJORLIST", majorList);
		resultMap.put("USERLIST", userList);
		resultMap.put("TYPELIST", typeList);
		resultMap.put("HOTLIST", hotList);
		
		System.out.println(userList+"-------------");
		return resultMap;
	}
	
	public Map enterpage_select4Major(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map map = (Map) paraMap.get("CONDITIONMAP");

		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");

		List<Map<String, String>> userList = null;
		List<Map<String, String>> majorList = null;
		List<Map<String, String>> typeList = null;
		List<Map<String, String>> hotList = null;
		List<Map<String, String>> sumList = null;
		
		String major = (String) map.get("MAJOR");
		String type = (String) map.get("TYPE");
		String hot = (String)map.get("HOT");

		String sql1 = "Select stuMajor1,hot1,majorType,stuyear,enroll,Count(wish1) as wish1s "
			+"From enter_student_year,major "
			+"Where enroll = wish1 and stuMajor1 = majorID ";

		String sql2 = "Select majorName from major";
		String sql3 = "Select hotName From hot";
		String sql4 = "Select typeName from majorType";
		String sql5 = "Select stuyear,Count(*) as sum From enter_student_year Group by stuyear Order by stuyear ";
		
		
		if(major.equals("all")){
			major = null;
		}
		if(type.equals("all")){
			type = null;
		}
		if(hot.equals("all")){
			hot = null;
		}
		
		if (major != null) {
			sql1 = sql1 + " and enroll = '"+major+"'";
		}
		if (type != null) {
			sql1 = sql1 + " and majorType = '"+type+"'";	
		}
		if(hot != null){
			sql1 = sql1 + " and hot1 = '"+hot+"'";
		}
		
		sql1 = sql1 + " Group by enroll,stuyear Order by stuyear,enroll desc";
		
		System.out.println(major);
		System.out.println(type);
		System.out.println(hot);
		System.out.println(sql1);
		try {
			majorList = dba.getMultiRecord(sql2);
			userList = dba.getMultiRecord(sql1);
			typeList = dba.getMultiRecord(sql4);
			hotList = dba.getMultiRecord(sql3);
			sumList = dba.getMultiRecord(sql5);
			
			NumberFormat numberFormat = NumberFormat.getInstance();
			numberFormat.setMaximumFractionDigits(2);
			int x = 0;
			int a = 0;
			int b = 0;
			
			String result = "";
			for(int i=0;i<sumList.size();i++){
				if(sumList.get(i).get("stuyear").equals("2017")){
					x = Integer.parseInt(sumList.get(i).get("sum"));
				}else if(sumList.get(i).get("stuyear").equals("2018")){
					a = Integer.parseInt(sumList.get(i).get("sum"));
				}else{
					b = Integer.parseInt(sumList.get(i).get("sum"));
				}
			}
			
			for(int i=0;i<userList.size();i++){
				int y = Integer.parseInt(userList.get(i).get("wish1s"));

				if(userList.get(i).get("stuyear").equals("2017")){
					result = numberFormat.format((float)y/(float)x*100);
				}else if(userList.get(i).get("stuyear").equals("2018")){
					result = numberFormat.format((float)y/(float)a*100);
				}else{
					result = numberFormat.format((float)y/(float)b*100);
				}
				
				userList.get(i).put("rate", result+"%");
			}
			
			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (null == userList) {
			resultMap.put("RESULT", "fail");
		} else {
			resultMap.put("RESULT", "success");
		}
		
		resultMap.put("FMAJORLIST", majorList);
		resultMap.put("USERLIST", userList);
		resultMap.put("TYPELIST", typeList);
		resultMap.put("HOTLIST", hotList);
		
		System.out.println(userList+"-------------");
		return resultMap;
	}
	public Map enterpage_select5(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");

		Map<String, Object> resultMap = new HashMap<String, Object>();

		List<Map<String, String>> userList = null;
		List<Map<String, String>> familyList = null;
		List<Map<String, String>> typeList = null;

		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");

		String sql1 = "Select stuMajor1,majorType,majorName,stuyear,Count(stuMajor1) as sum,Avg(stuScoure) as savg"
						+" From enter_student_year,major"
						+" Where stuMajor1 = majorID"
						+" Group by majorName,stuyear"
						+" Order by stuyear,sum desc";
		String sql2 = "Select majorName from major";
		String sql3 = "Select typeName From majorType";
		
		try {
			userList = dba.getMultiRecord(sql1);
			familyList = dba.getMultiRecord(sql2);
			typeList = dba.getMultiRecord(sql3);
			
			int x = 0;
			int z = 0;
			int n = 0;
			for(int i=0;i<userList.size();i++){
				int y = Integer.parseInt(userList.get(i).get("sum"));
				if(userList.get(i).get("stuyear").equals("2017")){
					x = x + y;
				}else if(userList.get(i).get("stuyear").equals("2018")){
					z = z + y;
				}else{
					n = n + y;
				}
			}

			NumberFormat numberFormat = NumberFormat.getInstance();
			numberFormat.setMaximumFractionDigits(2);
			String result = "1";
			for(int i=0;i<userList.size();i++){
				int y = Integer.parseInt(userList.get(i).get("sum"));
				if(userList.get(i).get("stuyear").equals("2017")){
					result = numberFormat.format((float)y/(float)x*100);
				}else if(userList.get(i).get("stuyear").equals("2018")){
					result = numberFormat.format((float)y/(float)z*100);
				}else{
					result = numberFormat.format((float)y/(float)n*100);
				}
				
				userList.get(i).put("rate", result+"%");
			}
			
			String sql4 = "Update enter_student_year Set rate =  Where stuMajor1 = ";
			for(int i=0;i<userList.size();i++){
				sql4 = "Update enter_student_year Set rate = '"+userList.get(i).get("rate")+"' " +
						"Where stuMajor1 = '"+userList.get(i).get("stuMajor1")+"' and stuyear = '"+userList.get(i).get("stuyear")+"'";
				dba.executeUpdate(sql4);
			}
			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (null == userList) {
			resultMap.put("RESULT", "fail");
		} else {
			resultMap.put("RESULT", "success");
		}
	
		resultMap.put("USERLIST", userList);
		resultMap.put("FAMILYLIST", familyList);
		resultMap.put("TYPELIST", typeList);
		return resultMap;
	}

	public Map enterpage_select6(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");

		Map<String, Object> resultMap = new HashMap<String, Object>();

		List<Map<String, String>> userList = null;
		List<Map<String, String>> sumList = null;

		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");

		String sql1 = "Select stuyear,enroll,Count(wish1) as wish1s"
						+" From enter_student_year"
						+" Where enroll = wish1 and stuyear = 2019 Group by enroll " 
						+"Order by wish1s desc";
		String sql2 = "Select Count(*) as sum"
						+" From enter_student_year where stuyear = 2019";
		try {
			userList = dba.getMultiRecord(sql1);
			sumList = dba.getMultiRecord(sql2);
			
			NumberFormat numberFormat = NumberFormat.getInstance();
			numberFormat.setMaximumFractionDigits(2);
			String result = "1";
			for(int i=0;i<userList.size();i++){
				int y = Integer.parseInt(userList.get(i).get("wish1s"));
				int x = Integer.parseInt(sumList.get(0).get("sum"));
				
				if(userList.get(i).get("stuyear").equals("2017")){
					result = numberFormat.format((float)y/(float)x*100);
				}else if(userList.get(i).get("stuyear").equals("2018")){
					result = numberFormat.format((float)y/(float)x*100);
				}else{
					result = numberFormat.format((float)y/(float)x*100);
				}
				
				userList.get(i).put("rate", result+"%");
			}
			System.out.println(userList);
			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (null == userList) {
			resultMap.put("RESULT", "fail");
		} else {
			resultMap.put("RESULT", "success");
		}

		resultMap.put("USERLIST", userList);
		return resultMap;
	}

	public Map enterpage_select7(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");

		Map<String, Object> resultMap = new HashMap<String, Object>();

		List<Map<String, String>> userList = null;
		List<Map<String, String>> sumList = null;

		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");

		String sql1 = "Select stuyear,enroll,Count(wish1) as wish1s"
						+" From enter_student_year"
						+" Where enroll = wish1 and stuyear = 2019 Group by enroll " 
						+"Order by wish1s desc";
		String sql2 = "Select Count(*) as sum"
						+" From enter_student_year where stuyear = 2019";
		try {
			userList = dba.getMultiRecord(sql1);
			sumList = dba.getMultiRecord(sql2);
			
			NumberFormat numberFormat = NumberFormat.getInstance();
			numberFormat.setMaximumFractionDigits(2);
			String result = "1";
			for(int i=0;i<userList.size();i++){
				int y = Integer.parseInt(userList.get(i).get("wish1s"));
				int x = Integer.parseInt(sumList.get(0).get("sum"));
				
				if(userList.get(i).get("stuyear").equals("2017")){
					result = numberFormat.format((float)y/(float)x*100);
				}else if(userList.get(i).get("stuyear").equals("2018")){
					result = numberFormat.format((float)y/(float)x*100);
				}else{
					result = numberFormat.format((float)y/(float)x*100);
				}
				
				userList.get(i).put("rate", result+"%");
			}
			System.out.println(userList);
			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (null == userList) {
			resultMap.put("RESULT", "fail");
		} else {
			resultMap.put("RESULT", "success");
		}

		resultMap.put("USERLIST", userList);
		return resultMap;
	}

	public Map enterpage_select8(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");

		Map<String, Object> resultMap = new HashMap<String, Object>();

		List<Map<String, String>> userList = null;
		List<Map<String, String>> sumList = null;

		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");

		String sql1 = "Select stuyear,enroll,Count(wish1) as wish1s"
						+" From enter_student_year"
						+" Where enroll = wish1 and stuyear = 2019 Group by enroll " 
						+"Order by wish1s desc";
		String sql2 = "Select Count(*) as sum"
						+" From enter_student_year where stuyear = 2019";
		try {
			userList = dba.getMultiRecord(sql1);
			sumList = dba.getMultiRecord(sql2);
			
			NumberFormat numberFormat = NumberFormat.getInstance();
			numberFormat.setMaximumFractionDigits(2);
			String result = "1";
			for(int i=0;i<userList.size();i++){
				int y = Integer.parseInt(userList.get(i).get("wish1s"));
				int x = Integer.parseInt(sumList.get(0).get("sum"));
				
				if(userList.get(i).get("stuyear").equals("2017")){
					result = numberFormat.format((float)y/(float)x*100);
				}else if(userList.get(i).get("stuyear").equals("2018")){
					result = numberFormat.format((float)y/(float)x*100);
				}else{
					result = numberFormat.format((float)y/(float)x*100);
				}
				
				userList.get(i).put("rate", result+"%");
			}
			System.out.println(userList);
			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (null == userList) {
			resultMap.put("RESULT", "fail");
		} else {
			resultMap.put("RESULT", "success");
		}

		resultMap.put("USERLIST", userList);
		return resultMap;
	}
	public Map enterpage_selectAll(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map map = (Map) paraMap.get("CONDITIONMAP");
		List<Map<String, String>> userList = null;
		List<Map<String, String>> familyList = null;
		
		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");
		
		String major = (String) map.get("MAJOR");
		String year = (String) map.get("YEAR");
		String family = (String) map.get("FAMILY");
		
		String sql1 = "Select stuFamily1,stuName,stuScoure,familyName,majorName,majorType,relief,enroll " +
					"From enter_student_year,family,major " +
					"Where stuFamily1 = familyID and stuMajor1 = majorID ";
		String sql2 = "Select familyID,familyName From family";
		if(major != null && year != null){
			sql1 = sql1+" and stuMajor1 = '"+major+"' and stuyear = '"+year+"'";
		}
		
		if(family == null){
			family = "all";
		}
		
		if(family != null && family.equals("all") != true){
			sql1 = sql1+" and stuFamily1 = '"+family+"'";
		}
		
		
		System.out.println(sql1);
		try {
			userList = dba.getMultiRecord(sql1);
			familyList = dba.getMultiRecord(sql2);
//			System.out.println(userList);
			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (null == userList) {
			resultMap.put("RESULT", "fail");
		} else {
			resultMap.put("RESULT", "success");
		}

		resultMap.put("USERLIST", userList);
		resultMap.put("FAMILYLIST", familyList);
		resultMap.put("MAJOR",major);
		resultMap.put("YEAR",year);
		return resultMap;
	}
	public Map enterpage_selectAll1(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map map = (Map) paraMap.get("CONDITIONMAP");
		
		List<Map<String, String>> userList = new ArrayList<Map<String, String>>();
		List<Map<String, String>> wishList1 = null;
		List<Map<String, String>> wishList2 = null;
		List<Map<String, String>> wishList3 = null;
		List<Map<String, String>> wishList4 = null;
		List<Map<String, String>> wishList5 = null;
		List<Map<String, String>> wishList6 = null;
		List<Map<String, String>> wishList = null;
		List<Map<String, String>> majorList = null;
		
		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");
		String major = (String) map.get("MAJOR");
		String year = (String) map.get("YEAR");
		
		String sql1 = "Select enroll,majorType,Count(wish1) as wish1s " +
		"From enter_student_year,major Where stuMajor1 = majorID and wishNum = 1";
		String sql2 = "Select enroll,majorType,Count(wish2) as wish2s " +
		"From enter_student_year,major Where stuMajor1 = majorID and wishNum = 2";
		String sql3 = "Select enroll,majorType,Count(wish3) as wish3s " +
		"From enter_student_year,major Where stuMajor1 = majorID and wishNum = 3";
		String sql4 = "Select enroll,majorType,Count(wish4) as wish4s " +
		"From enter_student_year,major Where stuMajor1 = majorID and wishNum = 4";
		String sql5 = "Select enroll,majorType,Count(wish5) as wish5s " +
		"From enter_student_year,major Where stuMajor1 = majorID and wishNum = 5";
		String sql6 = "Select enroll,majorType,Count(wish6) as wish6s " +
		"From enter_student_year,major Where stuMajor1 = majorID and wishNum = 6";
		String sql7 = "Select stuyear,Count(*) as sum From enter_student_year Group by stuyear Order by stuyear";
		String sql8 = "Select majorName,majorType From enter_student_year,major " +
				"Where stuMajor1 = majorID and stuMajor1 = "+major+" Group by majorName";
		
		if(major!=null && year != null){
			sql1 = sql1 + " and stuMajor1 = '"+major+"' and stuyear = '"+year+"'";
			sql2 = sql2 + " and stuMajor1 = '"+major+"' and stuyear = '"+year+"'";
			sql3 = sql3 + " and stuMajor1 = '"+major+"' and stuyear = '"+year+"'";
			sql4 = sql4 + " and stuMajor1 = '"+major+"' and stuyear = '"+year+"'";
			sql5 = sql5 + " and stuMajor1 = '"+major+"' and stuyear = '"+year+"'";
			sql6 = sql6 + " and stuMajor1 = '"+major+"' and stuyear = '"+year+"'";
		}
		
		System.out.println(sql8+"---------");
		try {
			//userList = dba.getMultiRecord(sql1);
			wishList1 = dba.getMultiRecord(sql1);
			wishList2 = dba.getMultiRecord(sql2);
			wishList3 = dba.getMultiRecord(sql3);
			wishList4 = dba.getMultiRecord(sql4);
			wishList5 = dba.getMultiRecord(sql5);
			wishList6 = dba.getMultiRecord(sql6);
			wishList = dba.getMultiRecord(sql7);
			majorList = dba.getMultiRecord(sql8);
			
			System.out.println(wishList1);
			
			NumberFormat numberFormat = NumberFormat.getInstance();
			numberFormat.setMaximumFractionDigits(2);
			int x = 0;
			int a = 0;
			int b = 0;
			
			for(int i=0;i<wishList.size();i++){
				if(wishList.get(i).get("stuyear").equals("2017")){
					x = Integer.parseInt(wishList.get(i).get("sum"));
				}else if(wishList.get(i).get("stuyear").equals("2018")){
					a = Integer.parseInt(wishList.get(i).get("sum"));
				}else{
					b = Integer.parseInt(wishList.get(i).get("sum"));
				}
			}
			
			if(year.equals("2017")){
				int i = Integer.parseInt(wishList1.get(0).get("wish1s"));
				int j = Integer.parseInt(wishList2.get(0).get("wish2s"));
				int k = Integer.parseInt(wishList3.get(0).get("wish3s"));
				int l = Integer.parseInt(wishList4.get(0).get("wish4s"));
				int m = Integer.parseInt(wishList5.get(0).get("wish5s"));
				int n = Integer.parseInt(wishList6.get(0).get("wish6s"));
				Map<String,String> one = new HashMap<String,String>();
				one.put("majorName", majorList.get(0).get("majorName"));
				one.put("majorType", majorList.get(0).get("majorType"));
				one.put("wish1", numberFormat.format((float)i/(float)x*100)+"%");
				one.put("wish2", numberFormat.format((float)j/(float)x*100)+"%");
				one.put("wish3", numberFormat.format((float)k/(float)x*100)+"%");
				one.put("wish4", numberFormat.format((float)l/(float)x*100)+"%");
				one.put("wish5", numberFormat.format((float)m/(float)x*100)+"%");
				one.put("wish6", numberFormat.format((float)n/(float)x*100)+"%");
				userList.add(one);
			}else if (year.equals("2018")){
				int i = Integer.parseInt(wishList1.get(0).get("wish1s"));
				int j = Integer.parseInt(wishList2.get(0).get("wish2s"));
				int k = Integer.parseInt(wishList3.get(0).get("wish3s"));
				int l = Integer.parseInt(wishList4.get(0).get("wish4s"));
				int m = Integer.parseInt(wishList5.get(0).get("wish5s"));
				int n = Integer.parseInt(wishList6.get(0).get("wish6s"));
				Map<String,String> one = new HashMap<String,String>();
				one.put("majorName", majorList.get(0).get("majorName"));
				one.put("majorType", majorList.get(0).get("majorType"));
				one.put("wish1", numberFormat.format((float)i/(float)x*100)+"%");
				one.put("wish2", numberFormat.format((float)j/(float)x*100)+"%");
				one.put("wish3", numberFormat.format((float)k/(float)x*100)+"%");
				one.put("wish4", numberFormat.format((float)l/(float)x*100)+"%");
				one.put("wish5", numberFormat.format((float)m/(float)x*100)+"%");
				one.put("wish6", numberFormat.format((float)n/(float)x*100)+"%");
				userList.add(one);
			}else{
				int i = Integer.parseInt(wishList1.get(0).get("wish1s"));
				int j = Integer.parseInt(wishList2.get(0).get("wish2s"));
				int k = Integer.parseInt(wishList3.get(0).get("wish3s"));
				int l = Integer.parseInt(wishList4.get(0).get("wish4s"));
				int m = Integer.parseInt(wishList5.get(0).get("wish5s"));
				int n = Integer.parseInt(wishList6.get(0).get("wish6s"));
				Map<String,String> one = new HashMap<String,String>();
				one.put("majorName", majorList.get(0).get("majorName"));
				one.put("majorType", majorList.get(0).get("majorType"));
				one.put("wish1", numberFormat.format((float)i/(float)x*100)+"%");
				one.put("wish2", numberFormat.format((float)j/(float)x*100)+"%");
				one.put("wish3", numberFormat.format((float)k/(float)x*100)+"%");
				one.put("wish4", numberFormat.format((float)l/(float)x*100)+"%");
				one.put("wish5", numberFormat.format((float)m/(float)x*100)+"%");
				one.put("wish6", numberFormat.format((float)n/(float)x*100)+"%");
				userList.add(one);
			}
			
			System.out.println(userList);
			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (null == userList) {
			resultMap.put("RESULT", "fail");
		} else {
			resultMap.put("RESULT", "success");
		}

		resultMap.put("USERLIST", userList);
		
		return resultMap;
	}
	public Map enterpage_selectEnroll(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map map = (Map) paraMap.get("CONDITIONMAP");
		List<Map<String, String>> userList = null;

		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");
		String name = (String) map.get("NAME");
		
		String sql1 = "Select stuName,wish1,wish2,wish3,wish4,wish5,wish6 From enter_student_year" +
				" Where 1 = 1 ";
		
		if(name != null){
			sql1 = sql1+" and stuName = '"+name+"' ";
		}
		System.out.println(sql1+"---------");
		try {
			userList = dba.getMultiRecord(sql1);
//			System.out.println(userList);
			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (null == userList) {
			resultMap.put("RESULT", "fail");
		} else {
			resultMap.put("RESULT", "success");
		}

		resultMap.put("USERLIST", userList);
		return resultMap;
	}

}
