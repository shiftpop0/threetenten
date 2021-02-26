package cnedu.ncist.test.web;

import edu.ncist.wang.hkdf.bussiness.blo.AbstractRequestHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class TestRequestHandler  extends AbstractRequestHandler {

    /**
     *  ;
     * @param map  封装有 request);response);requestName);serviceName);flowName);actionType)的map
     * @return
     */
    @Override
    public Map doRequestProcess(Map map) {
        String actionType = (String)map.get("ACTIONTYPE");
        if ("test".equals(actionType)){
            return testRequest(map);
        }
        else if("map_Test".equals(actionType)){
            return mapTestRequest(map);
        }else if("majorList".equals(actionType)){
            return majorListRequest(map);
        }
        else if("dataChart1".equals(actionType)) {
            return dataChart1Request(map);
        }
        else if("dataChart2".equals(actionType)) {
            return dataChart2Request(map);
        }
        else if("dataChart3".equals(actionType)) {
            return dataChart3Request(map);
        }
        else if("dataChart4".equals(actionType)) {
            return dataChart4Request(map);
        }
        else if("dataChart5".equals(actionType)) {
            return dataChart5Request(map);
        }
        else if("dataChart6".equals(actionType)) {
            return dataChart6Request(map);
        }
        else if("dataChart7".equals(actionType)) {
            return dataChart7Request(map);
        }
        else if("chart".equals(actionType)){
            return chartRequest(map);
        }
        else if("chart2".equals(actionType)){
            return chart2Request(map);
        }
        else if("chart3".equals(actionType)){
            return chart2Request(map);
        }else if("workDetail".equals(actionType)){
            return workDetail(map);
        }
        else if("mapProvince".equals(actionType)){
            return mapProvinceRequest(map);
        }
        else if("work_test".equals(actionType)) {
            return workTestRequest(map);
        }
        else if("careerType".equals(actionType)) {
            return careerType(map);
        }
        else if("provinceJobTable".equals(actionType)) {
            return provinceJobTable(map);
        }
        else{
            ActionTypeError();
        }
        return null;
    }

    // 就业城市水平，获取专业信息
    private Map majorListRequest(Map map) {
        HttpServletRequest req = (HttpServletRequest)map.get("REQUEST");
        Map<String,String> conditionMap = new HashMap();
        String flag = req.getParameter("FLAG");
        conditionMap.put("FLAG",flag);
        return conditionMap;
    }

    /**
     * 将 conditionMap 存储Map中
     * @param paraMap
     * @return
     */
    private Map testRequest(Map paraMap) {
        HttpServletRequest req = (HttpServletRequest)paraMap.get("REQUEST");
        Map<String,String> conditionMap = new HashMap();
        String flag = req.getParameter("FLAG");
        conditionMap.put("FLAG",flag);
        return conditionMap;
    }
    private Map mapTestRequest(Map paraMap) {
        HttpServletRequest req = (HttpServletRequest)paraMap.get("REQUEST");
        Map<String,String> conditionMap = new HashMap();
        String flag = req.getParameter("FLAG");
        conditionMap.put("FLAG",flag);
        return conditionMap;
    }
    private Map dataChart1Request(Map paraMap) {
        HttpServletRequest req = (HttpServletRequest)paraMap.get("REQUEST");
        Map<String,String> conditionMap = new HashMap();
        String flag = req.getParameter("FLAG");
        conditionMap.put("FLAG",flag);
        return conditionMap;
    }
    private Map dataChart2Request(Map paraMap) {
        HttpServletRequest req = (HttpServletRequest)paraMap.get("REQUEST");
        Map<String,String> conditionMap = new HashMap();
        String flag = req.getParameter("FLAG");
        conditionMap.put("FLAG",flag);
        return conditionMap;
    }
    private Map dataChart3Request(Map paraMap) {
        HttpServletRequest req = (HttpServletRequest)paraMap.get("REQUEST");
        Map<String,String> conditionMap = new HashMap();
        String flag = req.getParameter("FLAG");
        conditionMap.put("FLAG",flag);
        return conditionMap;
    }
    private Map dataChart4Request(Map paraMap) {
        HttpServletRequest req = (HttpServletRequest)paraMap.get("REQUEST");
        Map<String,String> conditionMap = new HashMap();
        String flag = req.getParameter("FLAG");
        conditionMap.put("FLAG",flag);
        return conditionMap;
    }
    private Map dataChart5Request(Map paraMap) {
        HttpServletRequest req = (HttpServletRequest)paraMap.get("REQUEST");
        Map<String,String> conditionMap = new HashMap();
        String flag = req.getParameter("FLAG");
        conditionMap.put("FLAG",flag);
        return conditionMap;
    }
    private Map dataChart6Request(Map paraMap) {
        HttpServletRequest req = (HttpServletRequest)paraMap.get("REQUEST");
        Map<String,String> conditionMap = new HashMap();
        String flag = req.getParameter("FLAG");
        conditionMap.put("FLAG",flag);
        return conditionMap;
    }
    private Map dataChart7Request(Map paraMap) {
        HttpServletRequest req = (HttpServletRequest)paraMap.get("REQUEST");
        Map<String,String> conditionMap = new HashMap();
        String flag = req.getParameter("FLAG");
        conditionMap.put("FLAG",flag);
        return conditionMap;
    }
    private Map mapProvinceRequest(Map paraMap) {
        HttpServletRequest req = (HttpServletRequest)paraMap.get("REQUEST");
        Map<String,String> conditionMap = new HashMap();
        String flag = req.getParameter("FLAG");
        conditionMap.put("FLAG",flag);
        return conditionMap;
    }
    private Map chartRequest(Map paraMap) {
        HttpServletRequest req = (HttpServletRequest)paraMap.get("REQUEST");
        Map<String,String> conditionMap = new HashMap();
        String flag = req.getParameter("FLAG");
        conditionMap.put("FLAG",flag);
        return conditionMap;
    }
    private Map chart2Request(Map paraMap) {
        HttpServletRequest req = (HttpServletRequest)paraMap.get("REQUEST");
        Map<String,String> conditionMap = new HashMap();
        String flag = req.getParameter("FLAG");
        conditionMap.put("FLAG",flag);
        return conditionMap;
    }
    private Map chart3Request(Map paraMap) {
        HttpServletRequest req = (HttpServletRequest)paraMap.get("REQUEST");
        Map<String,String> conditionMap = new HashMap();
        String flag = req.getParameter("FLAG");
        conditionMap.put("FLAG",flag);
        return conditionMap;
    }
    private Map workDetail(Map paraMap) {
        HttpServletRequest req = (HttpServletRequest)paraMap.get("REQUEST");
        Map<String,String> conditionMap = new HashMap();
        String flag = req.getParameter("FLAG");
        conditionMap.put("FLAG",flag);
        return conditionMap;
    }
    private Map workTestRequest(Map paraMap) {
        HttpServletRequest req = (HttpServletRequest)paraMap.get("REQUEST");
        Map<String,String> conditionMap = new HashMap();
        String flag = req.getParameter("FLAG");
        conditionMap.put("FLAG",flag);
        return conditionMap;
    }
    private Map careerType(Map paraMap) {
        HttpServletRequest req = (HttpServletRequest)paraMap.get("REQUEST");
        Map<String,String> conditionMap = new HashMap();
        String flag = req.getParameter("FLAG");
        conditionMap.put("FLAG",flag);
        return conditionMap;
    }
    private Map provinceJobTable(Map paraMap) {
        HttpServletRequest req = (HttpServletRequest)paraMap.get("REQUEST");
        Map<String,String> conditionMap = new HashMap();
        String flag = req.getParameter("FLAG");
        conditionMap.put("FLAG",flag);
        return conditionMap;
    }
}
