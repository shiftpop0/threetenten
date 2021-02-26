package cnedu.ncist.interstus.web;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ncist.wang.hkdf.bussiness.blo.AbstractFlowHandler;


public class InterstusFlowHandler extends AbstractFlowHandler{

    public String doFlowProcess(Map paraMap) {
        String actionType = (String)paraMap.get("ACTIONTYPE");
        
        if("interstuinfo".equals(actionType)){
            return provinceInfo(paraMap);//RESULT以及provinceList
        }else if("interstuinfoSelectedByProvince".equals(actionType)) {
            return interstuinfoSelectedByProvince(paraMap);
        }else{
            ActionTypeError();
        }
        return null;
    }
    private String interstuinfoSelectedByProvince(Map paraMap) {
        HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
        HttpServletResponse response = (HttpServletResponse)paraMap.get("RESPONSE");
        Map map = (Map)paraMap.get("RESULTMAP");
        
        request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
        request.setAttribute("PROVINCELIST", map.get("PROVINCELIST"));
        request.setAttribute("PROVINCETABLE", map.get("PROVINCETABLE"));
        return (String) map.get("RESULT");
    }
    //省份信息初始
    private String provinceInfo(Map paraMap) {
        
        HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
        HttpServletResponse response = (HttpServletResponse)paraMap.get("RESPONSE");
        Map map = (Map)paraMap.get("RESULTMAP");
        
        request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
        request.setAttribute("PROVINCELIST", map.get("PROVINCELIST"));
        request.setAttribute("PROVINCETABLE", map.get("PROVINCETABLE"));
        return (String) map.get("RESULT");
    }

}
