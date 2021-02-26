package cnedu.ncist.interstus.web;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import edu.ncist.wang.hkdf.bussiness.blo.AbstractRequestHandler;

public class InterstusRequestHandler extends AbstractRequestHandler{

    public Map doRequestProcess(Map paraMap) {
        // TODO Auto-generated method stub
        String actionType = (String) paraMap.get("ACTIONTYPE");
        if("interstuinfo".equals(actionType)){
            return provinInfo(paraMap);//RESULT以及provinceList
        }else if("interstuinfoSelectedByProvince".equals(actionType)) {
            return provinInfo(paraMap);
        }else{
            ActionTypeError();
        }
        return null;
    }
    //省份信息初始
    private Map provinInfo(Map paraMap) {
        HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
        Map<String, String> conditionMap = new HashMap<String, String>();
        
        String province = request.getParameter("PROVINCE");
        conditionMap.put("PROVINCE", province);     
        //FLAG:是否导出  FAMILY:是否有省份筛选
        String flag = request.getParameter("FLAG");
        String family = request.getParameter("family");
        conditionMap.put("FLAG", flag);
        conditionMap.put("FAMILY", family);
        
        return conditionMap;
    }

}
