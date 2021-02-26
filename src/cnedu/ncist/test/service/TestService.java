package cnedu.ncist.test.service;

import edu.ncist.wang.hkdf.bussiness.blo.AbstractService;
import edu.ncist.wang.hkdf.dao.database.DBConnectionException;
import edu.ncist.wang.hkdf.dao.database.DataBaseAccess;
import edu.ncist.wang.hkdf.dao.database.DataManipulationException;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class TestService extends AbstractService {
    @Override
    public Map doServiceProcess(Map map) throws UnsupportedEncodingException, DBConnectionException {
        String actionType = (String)map.get("ACTIONTYPE");

        if ("test".equals(actionType)){
            return testService(map);
        }else if ("map_Test".equals(actionType)){
            return mapTestService(map);
        }else if("mapProvince".equals(actionType)) {
            return mapProvinceService(map);
        }else if("dataChart1".equals(actionType)){
            return dataChart1Service(map);
        }else if("dataChart2".equals(actionType)){
            return dataChart2Service(map);
        }else if("dataChart3".equals(actionType)){
            return dataChart3Service(map);
        }else if("dataChart4".equals(actionType)){
            return dataChart4Service(map);
        }else if("dataChart5".equals(actionType)){
            return dataChart5Service(map);
        }else if("dataChart6".equals(actionType)){
            return dataChart6Service(map);
        }else if("dataChart7".equals(actionType)){
            return dataChart7Service(map);
        }else if ("chart".equals(actionType)){
            return chartService(map);
        }else if ("chart2".equals(actionType)){
            return chart2Service(map);
        }else if ("chart3".equals(actionType)){
            return chart3Service(map);
        }else if("work_test".equals(actionType)){
            return workTestService(map);
        }else if("workDetail".equals(actionType)){
            return workDetail(map);
        }else if("careerType".equals(actionType)){
            return careerType(map);
        }else if("provinceJobTable".equals(actionType)){
            return provinceJobTable(map);
        }
        return null;
    }

    /**
     * 测试功能，无意义
     */
    private Map testService(Map paraMap) {
        HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
        Map map = (Map)paraMap.get("CONDITIONMAP");
        Map<String, Object> resultMap = new HashMap<String, Object>();

        String flag = (String)map.get("FLAG");

        DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");

        String sql = null;
        sql = "SELECT XM,ZXMC,CJ FROM enter_student  WHERE CJ > 600 ORDER BY CJ DESC";
        List<Map<String, String>> middleSchoolList = null;
        try {
            middleSchoolList = dba.getMultiRecord(sql);
//System.out.println(middleSchoolList);
            dba.close();
        } catch (DataManipulationException | DBConnectionException e) {
            e.printStackTrace();
        }
        resultMap.put("middleSchoolList", middleSchoolList);
//        sql = sql + "where 1 = 1 " ;
        resultMap.put("RESULT", "success");

        return resultMap;
    }

    /**
     * 招生全国省份分布地图
     */
    private Map mapTestService(Map paraMap) throws DBConnectionException {
        HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
        Map map = (Map)paraMap.get("CONDITIONMAP");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String flag = (String)map.get("FLAG");
        DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");
        String year = request.getParameter("YEAR");
        if(year==null){
            year="2018";
        }
        String sql = "select a.DQMC name,count(*) value,a.SZSX_wk skx,a.SZSX_lk skx2\n" +
                "from province_controlLine a left join enter_student b\n" +
                "on a.DQDM=b.DQDM\n" +
                "where b.YEAR1= " +year+
                " group by a.DQMC";

        List<Map<String, String>> selectedMapByProvince = null;
        List<Map<String,String>> selectTop3ByProvince = null;
        try {
            selectedMapByProvince = dba.getMultiRecord(sql);
        } catch (DataManipulationException e) {
            e.printStackTrace();
        }
        for(int i =0;i<selectedMapByProvince.size();i++){
            String province=selectedMapByProvince.get(i).get("name");
            String sql1= "select ZYMC,LQZY,COUNT(*) as number from enter_student RIGHT JOIN sheet2 on enter_student.LQZY = \n" +
                    "sheet2.ZYDH where DQDM = (select DQDM from province WHERE DQMC1 = '"+province+"')\n" +
                    " and sheet2.YEAR1 = "+year+" GROUP BY LQZY ORDER BY number desc limit 0,3";
            try {
                selectTop3ByProvince = dba.getMultiRecord(sql1);
            } catch (DataManipulationException e) {
                e.printStackTrace();
            }
            for(int j=0;j<selectTop3ByProvince.size();j++){
                String top3="";
                if(j==0)
                    top3="first";
                else if(j==1)
                    top3="second";
                else if(j==2)
                    top3="third";
                selectedMapByProvince.get(i).put(top3,selectTop3ByProvince.get(j).get("ZYMC"));
            }
        }
        dba.close();
        request.setAttribute("YEAR",year);
        resultMap.put("selectedMapByProvince", selectedMapByProvince);
        resultMap.put("RESULT", "success");

        return resultMap;
    }

    /**
     * 招生全国省份分布地图子功能实现
     * 点击地图的省份区域之后进入该省份的详细录取信息
     */
    private Map mapProvinceService(Map paraMap) throws UnsupportedEncodingException {
        HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Map conditionMap = (Map)paraMap.get("CONDITIONMAP");
        DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");
//        获取筛选年份
        String year = request.getParameter("year");
        if(year==null){
            year="2018";
        }
//        获取点击省份的省份名
        String name =request.getParameter("name");
//        转码，url传参为get方式请求，页面采用utf-8传入，服务器默认iso8895-1接受，所以先解码再转码
//        name=new String(name.getBytes("iso8859-1"), StandardCharsets.UTF_8);
//        根据省份和年份分专业计算录取人数
        String sql = "select count(*) number,B.ZYMC from\n" +
                "(select a.DQMC,b.LQZY from province a LEFT JOIN enter_student b\n" +
                "on a.DQDM = b.DQDM \n" +
                "where b.YEAR1 = "+year+" and a.DQMC1 = '"+name+"') A LEFT JOIN sheet2 B\n" +
                "on A.LQZY = B.ZYDH\n" +
                "where B.YEAR1 = " + year +
                " GROUP BY B.ZYDH " +
                "ORDER BY number desc";
//System.out.println(sql);
        List<Map<String, String>> mapProvince = null;
        try {
            mapProvince = dba.getMultiRecord(sql);
            dba.close();
        } catch (DataManipulationException | DBConnectionException e) {
            e.printStackTrace();
        }

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("mapProvince", mapProvince);
        resultMap.put("RESULT", "success");

        request.setAttribute("YEAR",year);
        request.setAttribute("PROVINCE",name);

        return resultMap;
    }

    /**
     * 就业城市水平功能实现
     */
    private Map chartService(Map paraMap) {
        HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
        Map conditionMap = (Map)paraMap.get("CONDITIONMAP");

        DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");

        List<Map<String, String>> majorList=null;
        try {
            String majorStudentCountSql ="SELECT major,count(*) as count,year from student.jy_city GROUP BY major,year";  //专业某一年的就业总人数
            majorList = dba.getMultiRecord(majorStudentCountSql);                                                         //[{major=信息管理与信息系统, year=2017, count=63},
            for (Map<String, String> tempMap : majorList) {
                String majorName = tempMap.get("major");
                int year = Integer.parseInt(tempMap.get("year"));
                String majorStudentSql= "SELECT major,level,year,count(level) as count from student.jy_city    " +
                        "where major= '" + majorName+ "' and year=" + year +
                        " GROUP BY level,year" ;
                List<Map<String, String>> cityLevelList = dba.getMultiRecord(majorStudentSql);                            //  [{major=网络工程, level=1, year=2017, count(level)=37}, {major=网络工程, level=2, year=2017, count(level)=18}, {major=网络工程, level=3, year=2017, count(level)=40}]
                String detailSql ="SELECT * from student.jy_city where year="+year;
                tempMap.put("detail","<a href=\"test?ActionType=workDetail&majorName="+majorName+"&year="+year+"\">detail</a>");             //详情链接
                for (Map<String, String> map1 : cityLevelList) {                                     //{major=网络工程, level=1, year=2017, count(level)=37}
                    String level = map1.get("level");
                    String count  = map1.get("count");
                    if (level.equals("1")){
                        tempMap.put("level1",count);
                    }else if (level.equals("2")){
                        tempMap.put("level2",count);
                    }else {
                        tempMap.put("level3",count);
                    }
                }
            }
            dba.close();
        } catch (DataManipulationException | DBConnectionException e) {
            e.printStackTrace();
        }

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("majorList",majorList);
        resultMap.put("RESULT", "success");
        return resultMap;
    }

    /**
     *  就业流向图
     */
    private Map chart2Service(Map paraMap) {
        HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
        Map map = (Map)paraMap.get("CONDITIONMAP");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        //        筛选年份
        int year = 2019;
        String yearStr = request.getParameter("YEAR");
        if(yearStr!=null){
            year=Integer.parseInt(yearStr);
        }
        String flag = (String)map.get("FLAG");

        DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");
        /*String sql = "select t1.FWMC1,t2.XM,t2.JTDZ from province t1,(select XM,JTDZ,DQDM from enter_student) t2 where t1.DQDM=t2.DQDM";
        String sql2 = "select college,major,name,year,dwszd from student.jy_city";


        List<Map<String, String>> name=null;


        int huaBeiNum = 0;
        int huaNanNum = 0;
        int huaDongNum = 0;
        int xiNanNum = 0;
        int xiBeiNum = 0;*/

//        根据名字和入学/毕业年份查询生源地和就业地
        String sql3 = "select A.name,A.FWMC1 come,B.FWMC1 goto from\n" +
                "(select a.name,b.FWMC1 from jy_city a LEFT JOIN province b\n" +
                "on a.province = b.DQMC \n" +
                "where a.year = "+year+") A LEFT JOIN\n" +
                "(select a.XM,b.FWMC1 from enter_student a LEFT JOIN province b\n" +
                "on a.DQDM = b.DQDM\n" +
                "where a.YEAR1 = "+(year-4)+") B\n" +
                "on A.name = B.XM";
//        就业流向
        List<Map<String, String>> jobFlowList=null;
        try {
            jobFlowList = dba.getMultiRecord(sql3);
            dba.close();
        } catch (DataManipulationException | DBConnectionException e) {
            e.printStackTrace();
        }

        /*try {
            List<Map<String, String>> address = dba.getMultiRecord(sql);
            jobFlow = dba.getMultiRecord(sql3);
            name = dba.getMultiRecord(sql2);

            for (Map<String, String> map1 : address) {
                for (Map<String, String> map2 : name) {

                    String xm = map1.get("XM");
                    String name1 = map2.get("name");
                    if (xm.equals(name1)){
                        map2.put("FWMC1",map1.get("FWMC1"));
                        String jtdz = map1.get("JTDZ").trim();
                        String company = map2.get("dwszd");

                        String region = getRegion(company);
                        if (region.equals("华北")){
                            huaBeiNum++;
                        }else if (region.equals("华南")){
                            huaNanNum++;
                        }else if (region.equals("华东")){
                            huaDongNum++;
                        }else if (region.equals("西北")){
                            xiBeiNum++;
                        }else if (region.equals("西南")){
                            xiNanNum++;
                        }

                        map2.put("region",region);
                        map2.put("JTDZ",jtdz);
                    }
                }

            }


            dba.close();
        } catch (DataManipulationException e) {
            e.printStackTrace();
        } catch (DBConnectionException e) {
            e.printStackTrace();
        }

        Map countMap = new HashMap<>();


        countMap.put("huaBeiNum",huaBeiNum);
        countMap.put("huaNanNum",huaNanNum);
        countMap.put("huaDongNum",huaDongNum);
        countMap.put("xiNanNum",xiNanNum);
        countMap.put("xiBeiNum",xiBeiNum);

        resultMap.put("countMap",countMap);
        resultMap.put("name",name);*/

        resultMap.put("jobFlowList",jobFlowList);
        resultMap.put("RESULT","success");
        request.setAttribute("YEAR",year);

        return resultMap;
    }

    /**
     * 就业学生分布功能
     */
    private Map workTestService(Map paraMap) {
        HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
        Map map = (Map)paraMap.get("CONDITIONMAP");
        Map<String, Object> resultMap = new HashMap<String, Object>();

        DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");
        String year = request.getParameter("YEAR");
        if(year==null){
            year="2019";
        }
        String flag = (String)map.get("FLAG");
        List<Map<String, String>> topCity = null;
        List<Map<String, String>> provinceList = null;

        String topCitySql = "select count(*) value,a.city name,b.longitude,b.latitude from jy_city a\n" +
                "LEFT JOIN jy_city_coordinate b on a.city = b.city\n" +
                "where a.year = " +year+
                " group by a.city\n" +
                "ORDER BY count(*) desc limit 0,10";

        String provinceSql = "select count(*) value,a.province name,b.longitude,b.latitude\n" +
                "from jy_city a LEFT JOIN jy_province_coordinate b\n" +
                "on a.province = b.province\n" +
                "where a.year = " +year+
                " GROUP BY a.province\n" +
                "ORDER BY count(*) desc";


        try {
            topCity = dba.getMultiRecord(topCitySql);
            provinceList = dba.getMultiRecord(provinceSql);
            dba.close();
        } catch (DataManipulationException | DBConnectionException e) {
            e.printStackTrace();
        }
        resultMap.put("RESULT", "success");
        resultMap.put("topCity",topCity );
        resultMap.put("provinceList",provinceList );
        request.setAttribute("YEAR",year);

        return resultMap;
    }

    private Map workDetail(Map paraMap) throws UnsupportedEncodingException {
        HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
        Map map = (Map)paraMap.get("CONDITIONMAP");
        int year = Integer.parseInt(request.getParameter("year"));
        String majorName = request.getParameter("majorName");
        System.out.println(majorName);
//        majorName=new String(majorName.getBytes("iso8859-1"), StandardCharsets.UTF_8);
        DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");
        List<Map<String, String>> detail=null;
        try {
            String detailSql = "SELECT * from student.jy_city where major='"+majorName+"' and year='"+year+"'";
            System.out.println(detailSql);
            detail = dba.getMultiRecord(detailSql);
            dba.close();
        } catch (DataManipulationException | DBConnectionException e) {
            e.printStackTrace();
        }
        System.out.println("detail = " + detail);
        /*majorName=new String(majorName.getBytes("iso8859-1"), StandardCharsets.UTF_8);
        System.out.println(majorName);
        DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");
        resultMap.put("RESULT", "success");
        resultMap.put("majorName", majorName);
        resultMap.put("year", year);
        System.out.println(resultMap);*/
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("detail",detail);
        resultMap.put("RESULT", "success");
        return resultMap;
    }

    private Map careerType(Map paraMap){
        HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
        Map map = (Map)paraMap.get("CONDITIONMAP");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");
        List<Map<String, String>> careerType1=null;
        List<Map<String, String>> careerType2=null;
        List<Map<String, String>> careerType3=null;
        String sql1 = "select type name,number value from jy_number where year = 2017";
        String sql2 = "select type name,number value from jy_number where year = 2018";
        String sql3 = "select type name,number value from jy_number where year = 2019";
        try {
            careerType1 = dba.getMultiRecord(sql1);
            careerType2 = dba.getMultiRecord(sql2);
            careerType3 = dba.getMultiRecord(sql3);
            dba.close();
        } catch (DataManipulationException | DBConnectionException e) {
            e.printStackTrace();
        }

        resultMap.put("careerType1",careerType1);
        resultMap.put("careerType2",careerType2);
        resultMap.put("careerType3",careerType3);
        resultMap.put("RESULT", "success");
        return resultMap;
    }
    private Map provinceJobTable(Map paraMap) throws UnsupportedEncodingException {
        HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
        Map map = (Map)paraMap.get("CONDITIONMAP");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");
        List<Map<String, String>> provinceJobList=null;
        String year = request.getParameter("year");
        String province = request.getParameter("province");
//        province=new String(province.getBytes("iso8859-1"), StandardCharsets.UTF_8);

        String sql = "select college,student_number,major,name,year,dwszd,company,city,province from jy_city\n" +
                " where province = '"+province+"' and year = "+year;
        try {
            provinceJobList = dba.getMultiRecord(sql);
            dba.close();
        } catch (DataManipulationException | DBConnectionException e) {
            e.printStackTrace();
        }

        resultMap.put("provinceJobList",provinceJobList);
        resultMap.put("RESULT", "success");
        return resultMap;
    }

    private Map dataChart1Service(Map paraMap) {
        HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
        Map map = (Map)paraMap.get("CONDITIONMAP");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");

        String year = request.getParameter("YEAR");
System.out.println(year);
        if(year==null){
            year = "2018";
        }
System.out.println(year);
        String sql1 = "select COUNT(*) as number,ZYMC \n" +
                "from enter_student RIGHT JOIN sheet2\n" +
                "on enter_student.LQZY = sheet2.ZYDH \n" +
                "where sheet2.YEAR1 = "+year+" and enter_student.YEAR1 = "+year+"\n" +
                "GROUP BY LQZY ORDER BY number desc";
/*
        String sql2 = "select COUNT(*) as number,ZYMC \n" +
                "from enter_student RIGHT JOIN sheet2\n" +
                "on enter_student.LQZY = sheet2.ZYDH \n" +
                "where sheet2.YEAR1 = 2017 and enter_student.YEAR1 = 2017\n" +
                "GROUP BY LQZY ORDER BY number desc";

        String sql3 = "select COUNT(*) as number,ZYMC \n" +
                "from enter_student RIGHT JOIN sheet2\n" +
                "on enter_student.LQZY = sheet2.ZYDH \n" +
                "where sheet2.YEAR1 = 2016 and enter_student.YEAR1 = 2016\n" +
                "GROUP BY LQZY ORDER BY number desc";

        String sql4 = "select COUNT(*) as number,ZYMC \n" +
                "from enter_student RIGHT JOIN sheet2\n" +
                "on enter_student.LQZY = sheet2.ZYDH \n" +
                "where sheet2.YEAR1 = 2015 and enter_student.YEAR1 = 2015\n" +
                "GROUP BY LQZY ORDER BY number desc";
*/
        List<Map<String, String>> proTop = null;
/*        List<Map<String, String>> proTop2017 = null;
        List<Map<String, String>> proTop2016 = null;
        List<Map<String, String>> proTop2015 = null;      */
        try {
            proTop = dba.getMultiRecord(sql1);
/*            proTop2017 = dba.getMultiRecord(sql2);
            proTop2016 = dba.getMultiRecord(sql3);
            proTop2015 = dba.getMultiRecord(sql4);     */
            dba.close();
        } catch (DataManipulationException | DBConnectionException e) {
            e.printStackTrace();
        }

        request.setAttribute("YEAR",year);
        resultMap.put("proTop", proTop);
/*        resultMap.put("proTop2017", proTop2017);
        resultMap.put("proTop2016", proTop2016);
        resultMap.put("proTop2015", proTop2015);   */
        resultMap.put("RESULT", "success");

        return resultMap;
    }

    private Map dataChart2Service(Map paraMap) throws UnsupportedEncodingException, DBConnectionException {
        HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Map map = (Map)paraMap.get("CONDITIONMAP");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");
//        年份
        String year = request.getParameter("YEAR");
//System .out .println(year);
        if(year==null){
            year = "2018";
        }
//        省份
        String province = request.getParameter("PROVINCE");
//System .out .println(province);
        if(province!=null){
            province = new String(province.getBytes("ISO-8859-1"),"UTF-8");
        }else{
            province = "北京市";
        }
//        理工：'B',文史：'0'
        String branch = request.getParameter("BRANCH");
        if(branch==null){
            branch = "B";
        }
//System .out .println(year+" "+province);
        String sql = "select avg(CJ) as avg,ZYMC from enter_student " +
                " LEFT JOIN sheet2 on enter_student.LQZY = sheet2.ZYDH" +
                " where sheet2.KLDM = '"+branch+"' and DQDM = " +
                "(select DQDM from province where DQMC = '"+province+"')" +
                " and enter_student.YEAR1 = "+year+" and sheet2.YEAR1 = " +year+
                " GROUP BY ZYMC ORDER BY avg desc";
//System .out .println(sql);
        String provinceSql = "select DQMC from province";
        List<Map<String, String>> topFiveMajor = null;
        List<Map<String, String>> provinceList = null;
        try {
            topFiveMajor = dba.getMultiRecord(sql);
            provinceList = dba.getMultiRecord(provinceSql);
        } catch (DataManipulationException e) {
            e.printStackTrace();
        }
        String sql2 = "";
        String majorName = "";
        List<List<Map<String,String>>> score = new ArrayList<>();
        for(int i = 0;i<topFiveMajor.size();i++){
            majorName = topFiveMajor.get(i).get("ZYMC");
            sql2 = "select a.CJ from enter_student a,sheet2 b" +
                    " where a.LQZY = b.ZYDH and b.ZYMC = '"+majorName+"'" +
                    " and a.YEAR1 = "+year+" and b.YEAR1 = "+year+
                    " and b.KLDM = '"+branch+"'";
            try{
                List <Map<String,String>> singleScore = dba.getMultiRecord(sql2);
                score.add(singleScore);
            }catch (DataManipulationException e) {
                e.printStackTrace();
            }
        }
        dba.close();
        request.setAttribute("YEAR",year);
        request.setAttribute("PROVINCE",province);
        request.setAttribute("BRANCH",branch);
        resultMap.put("score",score);
        resultMap.put("provinceList",provinceList);
        resultMap.put("topFiveMajor",topFiveMajor);
        resultMap.put("RESULT", "success");

        return resultMap;
    }

    private Map dataChart3Service(Map paraMap) {
        HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
        Map map = (Map)paraMap.get("CONDITIONMAP");
        Map<String, Object> resultMap = new HashMap<String, Object>();

        DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");

        String year = request.getParameter("YEAR");
//System.out.println(year);
        if(year==null){
            year = "2018";
        }
//System.out.println(year);
        String sql="select LQZY_sum,TiaoJi_sum,(TiaoJi_rate-'%') number,ZYMC FROM\n" +
                "tb_rate where YEAR1 = "+year+" and (TiaoJi_rate+0) > 0\n" +
                "ORDER BY (TiaoJi_rate+0) desc";
/*        String sql2="select LQZY_sum,TiaoJi_sum,(TiaoJi_rate-'%') number,ZYMC FROM\n" +
                "tb_rate where YEAR1 = 2017 and (TiaoJi_rate+0) > 0\n" +
                "ORDER BY (TiaoJi_rate+0) desc";
        String sql3="select LQZY_sum,TiaoJi_sum,(TiaoJi_rate-'%') number,ZYMC FROM\n" +
                "tb_rate where YEAR1 = 2016 and (TiaoJi_rate+0) > 0\n" +
                "ORDER BY (TiaoJi_rate+0) desc";
        String sql4="select LQZY_sum,TiaoJi_sum,(TiaoJi_rate-'%') number,ZYMC FROM\n" +
                "tb_rate where YEAR1 = 2015 and (TiaoJi_rate+0) > 0\n" +
                "ORDER BY (TiaoJi_rate+0) desc";
 */
//        专业调剂率
        List<Map<String, String>> topAdjustRat = null;
/*        List<Map<String, String>> top2017 = null;
        List<Map<String, String>> top2016 = null;
        List<Map<String, String>> top2015 = null;
 */
        try {
            topAdjustRat=dba.getMultiRecord(sql);
//            top2017=dba.getMultiRecord(sql2)
//            top2016=dba.getMultiRecord(sql3);
//            top2015=dba.getMultiRecord(sql4);
            dba.close();
        } catch (DataManipulationException | DBConnectionException e) {
            e.printStackTrace();
        }
        resultMap.put("RESULT", "success");
        request.setAttribute("YEAR",year);
        resultMap.put("topAdjustRat", topAdjustRat);
//        resultMap.put("top2017", top2017);
//        resultMap.put("top2016", top2016);
//        resultMap.put("top2015", top2015);

        return resultMap;
    }

    private Map dataChart4Service(Map paraMap) {
        HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
        Map map = (Map)paraMap.get("CONDITIONMAP");
        Map<String, Object> resultMap = new HashMap<String, Object>();

        DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");
//        年份
        String year = request.getParameter("YEAR");
        if(year==null){
            year = "2018";
        }

        String sql="select (BKZY1_rate-'%') first_choice,ROUND((LQZY_sum*100/BKZY_allsum),2) total_choice,ZYMC from tb_rate\n" +
                "where YEAR1 = " +year+
                " ORDER BY (BKZY1_rate+0) desc limit 0,10";

        String sql2="select (BKZY1_rate-'%') first_choice,ROUND((LQZY_sum*100/BKZY_allsum),2) total_choice,ZYMC from tb_rate\n" +
                "where YEAR1 = " +year+
                " ORDER BY total_choice desc limit 0,10";

        List<Map<String, String>> choiceData = null;
        List<Map<String, String>> choiceData2 = null;

        try {
            choiceData=dba.getMultiRecord(sql);
            choiceData2=dba.getMultiRecord(sql2);
            dba.close();
        } catch (DataManipulationException | DBConnectionException e) {
            e.printStackTrace();
        }
        resultMap.put("RESULT", "success");
        resultMap.put("choiceData", choiceData);
        resultMap.put("choiceData2", choiceData2);
        request.setAttribute("YEAR",year);

        return resultMap;
    }

    private Map dataChart5Service(Map paraMap) throws UnsupportedEncodingException {
        HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");

        Map map = (Map)paraMap.get("CONDITIONMAP");
        Map<String, Object> resultMap = new HashMap<String, Object>();

        DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");

        String year = request.getParameter("YEAR");//年份
        if(year==null){
            year = "2018";
        }
        String branch = request.getParameter("branch");//科目类别
//System.out.println(branch);
        if(branch!=null){
            branch = new String(branch.getBytes("ISO-8859-1"),"UTF-8");
        }else
            branch = "B";
//        int a[] = {15,25,35};//分数：录取线+a[i]
        List<Map<String, String>> choiceData1 = null;
        List<Map<String, String>> choiceData2 = null;
        List<Map<String, String>> choiceData3 = null;
        List<Map<String, String>> choiceData4 = null;

        //各省总人数
        String sql1="select DQMC,count(*) as number from\n"+
                "(select KSH,DQDM,CJ from (select KSH,DQDM,CJ,LQZY from enter_student where YEAR1="+year+") as a LEFT JOIN\n"+
                "(select ZYDH,YEAR1,KLDM from sheet2 where YEAR1="+year+") as b on a.LQZY=b.ZYDH\n"+
                "where b.KLDM='"+branch+"') as c LEFT JOIN\n"+
                "(select DQDM,DQMC,YEAR1,SZSX_lk from province_controlLine where YEAR1="+year+") as d\n"+
                "on c.DQDM=d.DQDM\n"+
                "where c.CJ<d.SZSX_lk+15 "+
                "GROUP BY DQMC";
        String sql2="select DQMC,count(*) as number from\n"+
                "(select KSH,DQDM,CJ from (select KSH,DQDM,CJ,LQZY from enter_student where YEAR1="+year+") as a LEFT JOIN\n"+
                "(select ZYDH,YEAR1,KLDM from sheet2 where YEAR1="+year+") as b on a.LQZY=b.ZYDH\n"+
                "where b.KLDM='"+branch+"') as c LEFT JOIN\n"+
                "(select DQDM,DQMC,YEAR1,SZSX_lk from province_controlLine where YEAR1="+year+") as d\n"+
                "on c.DQDM=d.DQDM\n"+
                "where c.CJ>=d.SZSX_lk+15 and c.CJ<d.SZSX_lk+25\n"+
                "GROUP BY DQMC";
        String sql3="select DQMC,count(*) as number from\n"+
                "(select KSH,DQDM,CJ from (select KSH,DQDM,CJ,LQZY from enter_student where YEAR1="+year+") as a LEFT JOIN\n"+
                "(select ZYDH,YEAR1,KLDM from sheet2 where YEAR1="+year+") as b on a.LQZY=b.ZYDH\n"+
                "where b.KLDM='"+branch+"') as c LEFT JOIN\n"+
                "(select DQDM,DQMC,YEAR1,SZSX_lk from province_controlLine where YEAR1="+year+") as d\n"+
                "on c.DQDM=d.DQDM\n"+
                "where c.CJ>=d.SZSX_lk+25 and c.CJ<d.SZSX_lk+35\n"+
                "GROUP BY DQMC";
        String sql4="select DQMC,count(*) as number from\n"+
                "(select KSH,DQDM,CJ from (select KSH,DQDM,CJ,LQZY from enter_student where YEAR1="+year+") as a LEFT JOIN\n"+
                "(select ZYDH,YEAR1,KLDM from sheet2 where YEAR1="+year+") as b on a.LQZY=b.ZYDH\n"+
                "where b.KLDM='"+branch+"') as c LEFT JOIN\n"+
                "(select DQDM,DQMC,YEAR1,SZSX_lk from province_controlLine where YEAR1="+year+") as d\n"+
                "on c.DQDM=d.DQDM\n"+
                "where c.CJ>=d.SZSX_lk+35\n"+
                "GROUP BY DQMC";
/*System.out.println(sql1);
System.out.println(sql2);
System.out.println(sql3);
System.out.println(sql4);*/
        try {
            choiceData1=dba.getMultiRecord(sql1);
            choiceData2=dba.getMultiRecord(sql2);
            choiceData3=dba.getMultiRecord(sql3);
            choiceData4=dba.getMultiRecord(sql4);
            dba.close();
        } catch (DataManipulationException | DBConnectionException e) {
            e.printStackTrace();
        }

       /* for(int i=0;i<3;i++){

            String sql2="select DQMC,count(*) as number from\n"+
                    "(select KSH,DQDM,CJ from (select KSH,DQDM,CJ,LQZY from enter_student where YEAR1="+year+") as a LEFT JOIN\n"+
                    "(select ZYDH,YEAR1,KLMC from sheet2 where YEAR1="+year+") as b on a.LQZY=b.ZYDH\n"+
                    "where b.KLMC='"+branch+"') as c LEFT JOIN\n"+
                    "(select DQDM,DQMC,YEAR1,SZSX_lk from province_controlLine where YEAR1="+year+") as d\n"+
                    "on c.DQDM=d.DQDM\n"+
                    "where c.CJ>d.SZSX_lk+"+a[i]+"\n"+
                    "GROUP BY DQMC";
System.out.println(sql2);
            try {
                if(i == 0)
                choiceData2=dba.getMultiRecord(sql2);
                else if(i == 1)
                choiceData3=dba.getMultiRecord(sql2);
                else
                choiceData4=dba.getMultiRecord(sql2);

            } catch (DataManipulationException e) {
                e.printStackTrace();
            }
        }*/

        resultMap.put("choiceData1", choiceData1);
        resultMap.put("choiceData2", choiceData2);
        resultMap.put("choiceData3", choiceData3);
        resultMap.put("choiceData4", choiceData4);
        request.setAttribute("YEAR",year);
        request.setAttribute("BRANCH",branch);
        resultMap.put("RESULT", "success");

        return resultMap;
    }

    private Map dataChart6Service(Map paraMap) {
        HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
        Map map = (Map)paraMap.get("CONDITIONMAP");
        Map<String, Object> resultMap = new HashMap<String, Object>();

        String flag = (String)map.get("FLAG");

        DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");
        resultMap.put("RESULT", "success");

        return resultMap;
    }

    private Map dataChart7Service(Map paraMap) {
        HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
        Map map = (Map)paraMap.get("CONDITIONMAP");
        Map<String, Object> resultMap = new HashMap<String, Object>();

        String flag = (String)map.get("FLAG");

        DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");
        resultMap.put("RESULT", "success");

        return resultMap;
    }

    private Map chart3Service(Map paraMap) throws UnsupportedEncodingException {
        HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
        Map map = (Map)paraMap.get("CONDITIONMAP");
        //String flag = (String)map.get("FLAG");
        DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");
//        筛选学院
        String college = request.getParameter("COLLEGE");
        if(college!=null){
            college = new String(college.getBytes("ISO-8859-1"),"UTF-8");
        }else{
            college = "计算机学院";
        }
//        筛选年份
        int year = 2019;
        String yearStr = request.getParameter("YEAR");
        if(yearStr!=null){
            year=Integer.parseInt(yearStr);
        }
//        专业对口率
        List<Map<String, String>> dklList=null;
//        学院筛选
        List<Map<String, String>> collegeList=null;
        /*String jyxgsql="SELECT t4.major,t4.year,t4.ky,t4.xgjy,t2.lqrs FROM\n" +
                "(select t1.major,t1.year,t1.ky,t3.xgjy from \n" +
                "(SELECT major,year,count(*) xgjy from jy_city where status<>'2' GROUP BY year,major) as t3\n" +
                "right JOIN (SELECT major,year,count(*) ky from jy_city where status='2' GROUP BY year,major) as t1\n" +
                "on t1.major=t3.major and t1.year=t3.year) as t4\n" +
                "LEFT JOIN (SELECT major,year, count(*) lqrs from jy_city GROUP BY year,major) as t2\n" +
                "on t4.major=t2.major and t4.year=t2.year;";*/
        String sql="SELECT A.major,A.number, B.total_number,\n" +
                "ROUND(A.number/B.total_number*100,2) 'dkl',A.year\t\n" +
                "from (select a.major,count(*) number,a.year \n" +
                "from jy_city a LEFT JOIN sheet2 b \n" +
                "on a.major = b.ZYMC \n" +
                "where a.company like '%科技%' and a.year = "+year+" and b.YEAR1 = "+(year-4)+" and a.college = '" + college +
                "' \nGROUP BY a.major) A LEFT JOIN \n" +
                "(select b.ZYMC,count(*) total_number,a.YEAR1 \n" +
                "FROM enter_student a LEFT JOIN sheet2 b \n" +
                "on a.LQZY = b.ZYDH \n" +
                "where a.YEAR1 = "+(year-4)+" and b.YEAR1 = " + (year-4) +
                " GROUP BY b.ZYMC) B \n" +
                "on A.major = B.ZYMC";
        String collegeSql = "SELECT college from jy_city \n" +
                "GROUP BY college";
//System.out.println(sql);
        try{
            dklList = dba.getMultiRecord(sql);
            collegeList = dba.getMultiRecord(collegeSql);
            dba.close();
        }catch (DataManipulationException | DBConnectionException e) {
            e.printStackTrace();
        }

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("dklList",dklList);
        resultMap.put("collegeList",collegeList);
        resultMap.put("RESULT", "success");

        request.setAttribute("YEAR",year);
        request.setAttribute("COLLEGE",college);
        return resultMap;
    }

    /**
     * @param majorCodeList
     * @return 学生的志愿代号排名
     */
    private List<Node> majorOrderByCount(List<Map<String, String>> majorCodeList,List<Map<String, String>> DQDMAndDQMC) {

        List<Node> majorOrderList = new ArrayList<>();

        for (Map<String, String> tempMap : majorCodeList) {
            Set<Map.Entry<String, String>> entrySet = tempMap.entrySet();
            for (Map.Entry<String, String> entry : entrySet) {
                String value = entry.getValue();
                if ( majorOrderList.size()==0){
                    if (!value.equals("")){
                        majorOrderList.add(new Node(value,1));
                    }
                }
                int size = majorOrderList.size();
                boolean tag = false;
                for (int i = 0; i <size ; i++) {
                    Node node = majorOrderList.get(i);
                    if (node.getMajorCode().equals(value)){
                        Integer count = node.getCount();
                        node.setCount(count+1);
                        tag = true;
                        break;
                    }
                }
                if (!tag){
                    if (!value.equals("")){
                        majorOrderList.add(new Node(value,1));
                    }
                }
            }
        }
        Collections.sort(majorOrderList);

        return majorOrderList;
    }

    private static String getRegion(String company){


        String[] huaBei = {"北京","天津","山西","内蒙","辽宁","吉林","黑龙江",};
        String[] huaNan = {"河北","湖北","湖南","广东","广西","海南","香港","澳门"};
        String[] huaDong = {"上海","江苏","浙江","安徽","福建","江西","山东","台湾"};
        String[] xiNan = {"重庆", "四川", "贵州", "云南", "西藏"};
        String[] xiBei = {"陕西","甘肃", "青海", "宁夏", "新疆"};

        for (String s : huaBei) {
            if (company.contains(s)){
                return "华北";
            }
        }
        for (String s : huaNan) {
            if (company.contains(s)){
                return "华南";
            }
        }
        for (String s : huaDong) {
            if (company.contains(s)){
                return "华东";
            }
        }
        for (String s : xiBei) {
            if (company.contains(s)){
                return "西北";
            }
        }
        for (String s : huaBei) {
            if (company.contains(s)){
                return "西南";
            }
        }

        return "";
    }

}
class Node implements Comparable{
    private String majorCode;
    private Integer count;
    private String majorName;

    public Node(String majorCode, Integer count) {
        this.majorCode = majorCode;
        this.count = count;
    }

    public Node(String majorCode, Integer count, String majorName) {
        this.majorCode = majorCode;
        this.count = count;
        this.majorName = majorName;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getMajorCode() {
        return majorCode;
    }

    public void setMajorCode(String majorCode) {
        this.majorCode = majorCode;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public int compareTo(Object o) {
        Node node = (Node)o;
        return -(this.count-((Node) o).getCount());
    }

    @Override
    public String toString() {
        return "Node{" +
                "majorCode='" + majorCode + '\'' +
                ", count=" + count +
                ", majorName='" + majorName + '\'' +
                '}';
    }
}