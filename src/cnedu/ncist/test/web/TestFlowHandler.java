package cnedu.ncist.test.web;

import com.sun.deploy.net.HttpResponse;
import edu.ncist.wang.hkdf.bussiness.blo.AbstractFlowHandler;
import net.sf.json.JSONArray;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 测试FlowHandler类
 */
public class TestFlowHandler extends AbstractFlowHandler {
    /**
     * 父类抽象方法，根据不同的 actionType 执行相应的方法
     * @param map     封装有 request);response);requestName);serviceName);flowName);actionType;conditionMap；resultMap 的map
     * @return
     */
    @Override
    public String doFlowProcess(Map map) {
        String actionType = (String)map.get("ACTIONTYPE");
        if("test".equals(actionType)){
            return test(map);
        }
        else if("map_Test".equals(actionType)){
            return map_Test(map);
        }
        else if("mapProvince".equals(actionType)){
            return mapProvince(map);
        }
        else if("dataChart1".equals(actionType)){
            return dataChart1(map);
        }
        else if("dataChart2".equals(actionType)){
            return dataChart2(map);
        }
        else if("dataChart3".equals(actionType)){
            return dataChart3(map);
        }
        else if("dataChart4".equals(actionType)){
            return dataChart4(map);
        }
        else if("dataChart5".equals(actionType)){
            return dataChart5(map);
        }
        else if("dataChart6".equals(actionType)){
            return dataChart6(map);
        }
        else if("dataChart7".equals(actionType)){
            return dataChart7(map);
        }
        else if("chart".equals(actionType)){
            return chart(map);
        }
        else if("chart2".equals(actionType)){
            return chart2(map);
        }
        else if("chart3".equals(actionType)){
            return chart3(map);
        }
        else if("work_test".equals(actionType)){
            return work_test(map);
        }else if("workDetail".equals(actionType)){
            return workDetail(map);
        }else if("careerType".equals(actionType)){
            return careerType(map);
        }else if("provinceJobTable".equals(actionType)){
            return provinceJobTable(map);
        }
        else{
            ActionTypeError();
        }
        return null;
    }

    /**
     * actionType 对应的具体的方法
     * @param paraMap
     * @return
     */
    public String test(Map paraMap){

        HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
        Map resultMap = (Map)paraMap.get("RESULTMAP");
//System.out.println(resultMap);
        request.setAttribute("middleSchoolList", resultMap.get("middleSchoolList"));

        return (String)resultMap.get("RESULT");
    }
    public String map_Test(Map paraMap){
        HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
        Map resultMap = (Map)paraMap.get("RESULTMAP");
//        Map selectedMapByProvince=resultMap.get("selectedMapByProvince")
//System.out.println(selectedMapByProvince);
//        MAP转为JSON
        JSONArray object=JSONArray.fromObject(resultMap.get("selectedMapByProvince"));
//System.out.println("JSON:"+object);
//        JSONArray array=JSONArray.fromObject(resultMap.get("majorCodeAndCount"));
//        System.out.println("JSON:"+array);


//        request请求域
        request.setAttribute("selectedMapByProvince", object);

        return (String)resultMap.get("RESULT");
    }

    public String mapProvince(Map paraMap){
        HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
        Map resultMap = (Map)paraMap.get("RESULTMAP");
        JSONArray mapProvince=JSONArray.fromObject(resultMap.get("mapProvince"));
        request.setAttribute("mapProvince", mapProvince);

        return (String)resultMap.get("RESULT");
    }

    public String chart2(Map paraMap){
        HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
        Map resultMap = (Map)paraMap.get("RESULTMAP");
        /*JSONArray name = JSONArray.fromObject(resultMap.get("name"));
        JSONArray countMap = JSONArray.fromObject(resultMap.get("countMap"));
        request.setAttribute("name",name);
        request.setAttribute("countMap",countMap);*/
        JSONArray jobFlowList = JSONArray.fromObject(resultMap.get("jobFlowList"));
        request.setAttribute("jobFlowList",jobFlowList);
        return "success";
    }

    public String work_Test(Map paraMap){

        HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
        Map map = (Map)paraMap.get("RESULTMAP");


        //   return (String)map.get("RESULT");
        return "success";
    }

    public String dataChart1(Map paraMap) {
        HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
        Map resultMap = (Map)paraMap.get("RESULTMAP");
//        MAP转为JSON
        JSONArray proTop=JSONArray.fromObject(resultMap.get("proTop"));
//        JSONArray proTop2017=JSONArray.fromObject(resultMap.get("proTop2017"));
//        JSONArray proTop2016=JSONArray.fromObject(resultMap.get("proTop2016"));
//        JSONArray proTop2015=JSONArray.fromObject(resultMap.get("proTop2015"));

        request.setAttribute("proTop", proTop);
//        request.setAttribute("proTop2017", proTop2017);
//        request.setAttribute("proTop2016", proTop2016);
//        request.setAttribute("proTop2015", proTop2015);

        return (String)resultMap.get("RESULT");
    }
    private String dataChart2(Map paraMap) {
        HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
        Map resultMap = (Map)paraMap.get("RESULTMAP");
        JSONArray topFiveMajor=JSONArray.fromObject(resultMap.get("topFiveMajor"));
        JSONArray score=JSONArray.fromObject(resultMap.get("score"));
        JSONArray provinceList=JSONArray.fromObject(resultMap.get("provinceList"));

        request.setAttribute("score",score);
        request.setAttribute("provinceList",provinceList);
        request.setAttribute("topFiveMajor",topFiveMajor);
        return "success";
    }
    private String dataChart3(Map paraMap) {
        HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
        Map resultMap = (Map)paraMap.get("RESULTMAP");
        JSONArray topAdjustRat=JSONArray.fromObject(resultMap.get("topAdjustRat"));
/*        JSONArray top2017=JSONArray.fromObject(resultMap.get("top2017"));
        JSONArray top2016=JSONArray.fromObject(resultMap.get("top2016"));
        JSONArray top2015=JSONArray.fromObject(resultMap.get("top2015"));        */
        request.setAttribute("topAdjustRat",topAdjustRat);
/*        request.setAttribute("top2017",top2017);
        request.setAttribute("top2016",top2016);
        request.setAttribute("top2015",top2015);            */

        return "success";
    }
    private String dataChart4(Map paraMap) {
        HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
        Map resultMap = (Map)paraMap.get("RESULTMAP");
        JSONArray choiceData=JSONArray.fromObject(resultMap.get("choiceData"));
        JSONArray choiceData2=JSONArray.fromObject(resultMap.get("choiceData2"));
//System.out.println("JSON:"+choiceData);
        request.setAttribute("choiceData",choiceData);
        request.setAttribute("choiceData2",choiceData2);

        return "success";
    }
    private String dataChart5(Map paraMap) {
        HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
        Map resultMap = (Map)paraMap.get("RESULTMAP");

        JSONArray choiceData1=JSONArray.fromObject(resultMap.get("choiceData1"));
        JSONArray choiceData2=JSONArray.fromObject(resultMap.get("choiceData2"));
        JSONArray choiceData3=JSONArray.fromObject(resultMap.get("choiceData3"));
        JSONArray choiceData4=JSONArray.fromObject(resultMap.get("choiceData4"));

//System.out.println("JSON:"+choiceData1);
        request.setAttribute("choiceData1",choiceData1);
        request.setAttribute("choiceData2",choiceData2);
        request.setAttribute("choiceData3",choiceData3);
        request.setAttribute("choiceData4",choiceData4);

        return "success";
    }
    private String dataChart6(Map paraMap) {
        HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
        Map map = (Map)paraMap.get("RESULTMAP");


        //   return (String)map.get("RESULT");
        return "success";
    }
    private String dataChart7(Map paraMap) {
        HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
        Map map = (Map)paraMap.get("RESULTMAP");
        //   return (String)map.get("RESULT");
        return "success";
    }
    public String chart(Map paraMap){

        HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
        Map resultMap = (Map)paraMap.get("RESULTMAP");


        Object majorList = resultMap.get("majorList");
        request.setAttribute("majorList",majorList);
//        System.out.println("majorList:"+majorList);

        JSONArray majorListJSON=JSONArray.fromObject(resultMap.get("majorList"));
//        System.out.println("JSON:"+majorListJSON);
        request.setAttribute("majorListJSON",majorListJSON);

        return (String)resultMap.get("RESULT");
    }

    public String chart3(Map paraMap){

        HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
        Map resultMap = (Map)paraMap.get("RESULTMAP");


        JSONArray dklList=JSONArray.fromObject(resultMap.get("dklList"));
        JSONArray collegeList=JSONArray.fromObject(resultMap.get("collegeList"));
        request.setAttribute("dklList",dklList);
        request.setAttribute("collegeList",collegeList);
//System.out.println(dklList);
        return (String)resultMap.get("RESULT");
    }

    public String workDetail(Map paraMap){

        HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
        Map resultMap = (Map)paraMap.get("RESULTMAP");
        Object detailObj=resultMap.get("detail");
        JSONArray detailJSON=JSONArray.fromObject(resultMap.get("detail"));


        request.setAttribute("detail",detailJSON);
        /*System.out.println("flow:"+resultMap.get("majorName"));
        request.setAttribute("majorName",resultMap.get("majorName"));
        request.setAttribute("year",resultMap.get("year"));*/
        return (String)resultMap.get("RESULT");
    }
    public String work_test(Map paraMap){

        HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
        Map resultMap = (Map)paraMap.get("RESULTMAP");

        JSONArray topCity=JSONArray.fromObject(resultMap.get("topCity"));
        JSONArray provinceList=JSONArray.fromObject(resultMap.get("provinceList"));

        request.setAttribute("topCity",topCity);
        request.setAttribute("provinceList",provinceList);


        //   return (String)map.get("RESULT");
        return "success";
    }
    public String careerType(Map paraMap){
        HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
        Map resultMap = (Map)paraMap.get("RESULTMAP");

        JSONArray careerType1=JSONArray.fromObject(resultMap.get("careerType1"));
        JSONArray careerType2=JSONArray.fromObject(resultMap.get("careerType2"));
        JSONArray careerType3=JSONArray.fromObject(resultMap.get("careerType3"));

        request.setAttribute("careerType1",careerType1);
        request.setAttribute("careerType2",careerType2);
        request.setAttribute("careerType3",careerType3);
        return "success";
    }
    public String provinceJobTable(Map paraMap){
        HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
        Map resultMap = (Map)paraMap.get("RESULTMAP");
        JSONArray provinceJobList=JSONArray.fromObject(resultMap.get("provinceJobList"));
        System.out.println("provinceJobList = " + provinceJobList);
        request.setAttribute("provinceJobList",provinceJobList);
        return "success";
    }
}