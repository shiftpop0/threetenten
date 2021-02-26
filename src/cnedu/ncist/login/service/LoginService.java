package cnedu.ncist.login.service;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hxtt.concurrent.l;

import cnedu.ncist.util.Calculation;
import edu.ncist.wang.hkdf.authorization.bussiness.blo.MenuList;
import edu.ncist.wang.hkdf.bussiness.blo.AbstractService;
import edu.ncist.wang.hkdf.dao.database.DBConnectionException;
import edu.ncist.wang.hkdf.dao.database.DataBaseAccess;
import edu.ncist.wang.hkdf.dao.database.DataManipulationException;

public class LoginService extends AbstractService{

	public Map<String, Object> doServiceProcess(Map paraMap){
		// TODO Auto-generated method stub
		
		String actionType = (String)paraMap.get("ACTIONTYPE");
		
		if("login_Init".equals(actionType)){
			return login_login(paraMap);
		}
		else{
			ActionTypeError();
		}
		
		return null;

	}
	
	public Map<String, Object> login_login(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("CONDITIONMAP");
		
		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");
		
		List<Map<String, String>> userList1 = null;
		List<Map<String, String>> majorList1 = null;
		List<Map<String, String>> majorList = null;
		List<Map<String, String>> majorList2 = null;
		List<Map<String, String>> majorList3 = null;
		List<Map<String, String>> relList = null;
		List<Map<String, String>> rlrateList = null;
		List<Map<String, String>> sumList = null;
		List<Map<String, String>> sumList1 = null;
		
		String sql1 = "Select ZYDH1,ZYDH2,ZYDH3,ZYDH4,ZYDH5,ZYDH6,LQZY"
			+" From enter_student"
			+" Where YEAR1 = 2018";
		String sql2 = "Select Count(LQZY) AS sum From enter_student Where ZYZYTJ = 0 and YEAR1 = 2018";
		String sql3 = "Select ZYMC,ZYDH From major Order by ZYDH";
		
		String sql5 =  "SELECT * FROM tb_rate WHERE YEAR1 = 2018 ORDER BY qz desc";
		
		
		String sql6 = "Select ZYDH,ZYMC,Count(ZYMC) as sum,YEAR1 From enter_student,major Where LQZY = ZYDH and ZYZYTJ = 1 Group by YEAR1,ZYMC Order by YEAR1,sum desc";
		
		String sql7 = "Select COUNT(ZYMC) as sums,YEAR1 From enter_student,major Where LQZY = ZYDH Group by YEAR1 Order by YEAR1";
		
		
		
		String sql8 ="SELECT * FROM(SELECT Tiaoji_sum.ZYDH,Tiaoji_sum.ZYMC,Tiaoji_sum.KLDM,Tiaoji_sum.KLMC, "
				+ "Tiaoji_sum.YEAR1,Tiaoji_sum.TiaoJisum,LQ_sum.LQsum,CONCAT(ROUND(Tiaoji_sum.TiaoJisum/LQ_sum.LQsum*100,2),'','%') AS TiaoJi_Rate "
				+ "FROM(SELECT major.ZYDH,major.ZYMC,majortype.KLDM,majortype.KLMC,enter_student.YEAR1, "
				+ "COUNT(enter_student.LQZY) AS TiaoJisum FROM enter_student,major,majortype WHERE "
				+ "enter_student.LQZY = major.ZYDH AND LQZY != ZYDH1 AND LQZY != ZYDH2 AND LQZY != ZYDH3 "
				+ "AND LQZY != ZYDH4 AND LQZY != ZYDH5 AND LQZY != ZYDH6 AND enter_student.LQFS != 12 "
				+ "AND enter_student.LQFS != 13 AND enter_student.LQFS != 23 AND major.KLDM = majortype.KLDM "
				+ "GROUP BY major.ZYDH,enter_student.YEAR1) AS Tiaoji_sum LEFT JOIN "
				+ "(SELECT LQZY,COUNT(enter_student.LQZY) AS LQsum,YEAR1 FROM enter_student "
				+ "GROUP BY YEAR1,LQZY) AS LQ_sum ON LQ_sum.LQZY = Tiaoji_sum.ZYDH AND LQ_sum.YEAR1 = Tiaoji_sum.YEAR1) AS a WHERE a.YEAR1 = '2018' "
				+ "ORDER BY a.YEAR1 DESC,a.TiaoJisum DESC";
		
		
		String sql9 = "SELECT * FROM tb_rate WHERE YEAR1 = 2018 ORDER BY LQZY_sum desc";
		
		
		String sql10 =  "SELECT * FROM tb_rate WHERE YEAR1 = 2018 ORDER BY LQZY1_sum desc";
		System.out.println("---majorListsql5=="+sql5);
		System.out.println("---rlrateListsql8=="+sql8);
		System.out.println("---majorList2sql9=="+sql9);
		System.out.println("---majorList3sql10=="+sql10);
		
		//�������Ҫ����һ����¼��ȡȨ�ޱ�־
		//ÿ����Ŀ�ڵ�¼������һ�ζ���Ҫ�� ��һ����򣬲���ʡ��
		//��������õ����������
		request.setAttribute("LOGINFLAG", "LOGIN");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List userList = null;
		List<Map<String, String>> list = null;
		MenuList menuList = new MenuList();
		list = menuList.login(request, map);
		
		if((null == list) || (list.size() == 0)){
			System.out.println("login user don't exists!");
		}
		else{
			System.out.println("lonin user number =" + list.size());
		}
		
		String userCode = (String)map.get("LOGINID");
		List<Map<String, String>> mList = menuList.getMenuList(request, userCode);
		
		//String verifyCode = (String)map.get("VERIFYCODE");
		
		Map<String, String> userInfo = null;
		userInfo = menuList.getUserInfo(request, userCode);
		
		if(null == list){
			resultMap.put("RESULT", "fail");
		}
		else if(list.size() == 0){
			resultMap.put("RESULT", "fail");
		}
		else if(list.size() > 1){
			resultMap.put("RESULT", "fail");
		}
		else{
			if((null == mList)||(mList.size() == 0)){
				resultMap.put("RESULT", "fail");
			}
			else{
	    		HttpSession session = request.getSession(true);
	    		String verifyCodeGen = (String)session.getAttribute("VerifyCode"); 
//	    		if(verifyCode.equals(verifyCodeGen)){
					resultMap.put("RESULT", "success");
					resultMap.put("MENULIST", mList);
//System.out.println(mList);
					resultMap.put("USERINFO", userInfo);
//	    		}
//	    		else{
//					resultMap.put("RESULT", "fail");
//	    		}
			}
		}

		try{
			userList1 = dba.getMultiRecord(sql1);
			majorList = dba.getMultiRecord(sql5);
			/*majorList1 = dba.getMultiRecord(sql3);
			relList = dba.getMultiRecord(sql6);*/
			rlrateList = dba.getMultiRecord(sql8);
			/*sumList = dba.getMultiRecord(sql7);
			sumList1 = dba.getMultiRecord(sql2);*/
			majorList2 = dba.getMultiRecord(sql9);
			majorList3 = dba.getMultiRecord(sql10);
			dba.close();
		}catch(DBConnectionException e){
			e.printStackTrace();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(null == userList1){
			resultMap.put("RESULT", "fail");
		}
		else{
			resultMap.put("RESULT", "success");
		}
		
		HttpSession session = request.getSession(true);
		session.setAttribute("MAJORLIST", majorList);
		session.setAttribute("RLRATELIST", rlrateList);
		session.setAttribute("MAJORLIST2", majorList2);
		session.setAttribute("MAJORLIST3", majorList3);
//		System.out.println(majorList3);
		return resultMap;
	}

}
