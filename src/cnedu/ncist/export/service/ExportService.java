package cnedu.ncist.export.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import edu.ncist.wang.hkdf.bussiness.blo.AbstractService;
import edu.ncist.wang.hkdf.dao.database.DBConnectionException;
import edu.ncist.wang.hkdf.dao.database.DataBaseAccess;
import edu.ncist.wang.hkdf.dao.database.DataManipulationException;

public class ExportService extends AbstractService{

	public Map doServiceProcess(Map paraMap) {
		// TODO Auto-generated method stub
		String actionType = (String)paraMap.get("ACTIONTYPE");
		
		if("bd_new".equals(actionType)){
			return enterstu_select1(paraMap);
		}else if("lq".equals(actionType)){
			return enterstu_select2(paraMap);
		}else if("lq_20".equals(actionType)){
			return enterstu_select1_1(paraMap);
		}else if("lz_bd".equals(actionType)){
			return enterstu_select2_2(paraMap);
		}
		else{
			ActionTypeError();
		}
		
		return null;
	}

	private Map enterstu_select1(Map paraMap) {

		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Map<String, String>> familyList = null;
		List<Map<String, String>> majorList = null;
		List<Map<String, String>> userList = null;
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
		String sql = "Select * from BD_newstudent";
		
		try {
			familyList = dba.getMultiRecord(sql);
			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(null == familyList){
			resultMap.put("RESULT", "fail");
		}
		else{
			resultMap.put("RESULT", "success");
		}
		resultMap.put("FAMILYLIST", familyList);
		return resultMap;
	}
	
	public Map enterstu_select1_1(Map paraMap){
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map map = (Map)paraMap.get("CONDITIONMAP");
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
		List<Map<String, String>> familyList = null;
		
		String sql = "Select ZYMC,LQZY_sum,BD_sum,BD_rate,BKZY1_sum,BKZY2_sum,TiaoJi_sum,TiaoJi_rate From tb_rate";
		
		try {
			familyList = dba.getMultiRecord(sql);
//			System.out.println(userList);
			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(null == familyList){
			resultMap.put("RESULT", "fail");
		}
		else{
			resultMap.put("RESULT", "success");
		}
		
		resultMap.put("FAMILYLIST", familyList);

		return resultMap;
	}
	
	public Map enterstu_select2(Map paraMap){
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map map = (Map)paraMap.get("CONDITIONMAP");
		
		List<Map<String, String>> familyList = null;
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
		String sql = "Select * from LQ";
		
		try {
			familyList = dba.getMultiRecord(sql);

			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(null == familyList){
			resultMap.put("RESULT", "fail");
		}
		else{
			resultMap.put("RESULT", "success");
		}
		
		resultMap.put("FAMILYLIST", familyList);

		return resultMap;
	}
	
	public Map enterstu_select2_2 (Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map map = (Map)paraMap.get("CONDITIONMAP");
		
		List<Map<String, String>> familyList = null;
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");

		String sql = "Select ZYMC,LQZY_sum,BD_sum,BD_rate,BKZY1_sum,BKZY2_sum,TiaoJi_sum,TiaoJi_rate From tb_rate";
		
		try {
			familyList = dba.getMultiRecord(sql);
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(null == familyList) {
			resultMap.put("RESULT", "fail");
		}
		else {
			resultMap.put("RESULT", "success");
		}
		
		resultMap.put("FAMILYLIST", familyList);
		//System.out.println(familyList+"---------");
		return resultMap;
	}
}
