package cnedu.ncist.interstus.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import edu.ncist.wang.hkdf.bussiness.blo.AbstractService;
import edu.ncist.wang.hkdf.dao.database.DBConnectionException;
import edu.ncist.wang.hkdf.dao.database.DataBaseAccess;
import edu.ncist.wang.hkdf.dao.database.DataManipulationException;

public class InterstusService extends AbstractService{

    public Map doServiceProcess(Map paraMap) {
        // TODO Auto-generated method stub
        String actionType = (String) paraMap.get("ACTIONTYPE");
        System.out.println("ACTIONTIPE:"+actionType);
        if("interstuinfo".equals(actionType)){
            return provinceInfo(paraMap);//RESULT以及provinceList
        }else if("interstuinfoSelectedByProvince".equals(actionType)) {
            return interstuinfoSelectedByProvince(paraMap);
        }
        else{
            ActionTypeError();
        }
        return null;
    }
    
    //省份信息初始
    private Map provinceInfo(Map paraMap) {
        HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");//获取请求路径
        Map map = (Map)paraMap.get("CONDITIONMAP");//获取请求信息（标签key:value）
        Map<String, Object> resultMap = new HashMap<String, Object>();//创建返回数据集
        
        List<Map<String, String>> provinceList = null;//创建省表数据集
        List<Map<String, String>> provinceTable =null;
        
        DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");//数据连接请求
        
        String sql = null;
        String sqlForSelected = null;
            sql = "select * from province";
            sqlForSelected="select DQMC from province";
        
        try {
            provinceTable = dba.getMultiRecord(sqlForSelected);
            provinceList = dba.getMultiRecord(sql);
            dba.close();
        } catch (DataManipulationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DBConnectionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(null == provinceList) {
            resultMap.put("RESULT", "fail");
        } else {
            resultMap.put("RESULT", "success");
System.out.println("success");
        }
        resultMap.put("PROVINCELIST", provinceList);
        resultMap.put("PROVINCETABLE", provinceTable);
        
        return resultMap;
    }
    //省份筛选
    private Map interstuinfoSelectedByProvince(Map paraMap) {
        
        HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");//获取请求路径
        
        Map<String, Object> resultMap = new HashMap<String, Object>();//创建返回数据集
        
        Map map = (Map) paraMap.get("CONDITIONMAP");//获取请求信息（标签key:value）
        
        List<Map<String, String>> provinceList = null;//创建省表数据集
        List<Map<String, String>> provinceTable =null;
        
        DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");//数据连接请求

        String province = (String) map.get("PROVINCE");
        System.out.println("PROVINCE:"+province);
        
        String sql = null;
        String sqlForSelected = null;
        if(province.equals("all")){
            sql = "select * from province";
        }else if (province != "0") {
            sql = "select * from province where DQMC = '"+province+"'";
        }
        sqlForSelected="select DQMC from province";
        try {
            provinceTable = dba.getMultiRecord(sqlForSelected);
            provinceList = dba.getMultiRecord(sql);
            dba.close();
        } catch (DataManipulationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DBConnectionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(null == provinceList) {
            resultMap.put("RESULT", "fail");
        } else {
            resultMap.put("RESULT", "success");
        }
        resultMap.put("PROVINCELIST", provinceList);
        resultMap.put("PROVINCETABLE", provinceTable);
        
        return resultMap;
    }

}
