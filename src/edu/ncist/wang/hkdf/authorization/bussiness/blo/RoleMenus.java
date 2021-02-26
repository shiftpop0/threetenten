package edu.ncist.wang.hkdf.authorization.bussiness.blo;

import java.util.List;
import java.util.Map;

import edu.ncist.wang.hkdf.authorization.bussiness.bli.RoleMenuAble;
import edu.ncist.wang.hkdf.dao.database.DataBaseAccess;
import edu.ncist.wang.hkdf.dao.database.DataManipulationException;

public class RoleMenus implements RoleMenuAble {

	public List<Map<String, String>> getMenuList(DataBaseAccess dba,
			List<Map<String, String>> roleList) {
		// TODO Auto-generated method stub
		String sql = null;
		List<Map<String, String>> list = null;

		if((null != roleList)&& (roleList.size()> 0)){
	    	String sql1 = "select A.menuCode as id1 " +
	    			", A.parrentCode as pid1 " +
	    			", A.menuName as name1 " +
	    			", A.linkPage as url1 " +
	    			", A.menuOrder " +
	    			"from authmenu A " +
	    			"left join authrolemenu B " +
	    			"on A.menuCode = B.menuCode " +
	    			" where A.isUse =  '1' and ";
	    	
	    	String sql2 = "select C.menuCode as id1 " +
			", C.parrentCode as pid1 " +
			", C.menuName as name1 " +
			", C.linkPage as url1 " +
			", C.menuOrder " +
			"from authmenu A " +
			"left join authmenu C on A.parrentCode = C.menuCode " +
			"left join authrolemenu B " +
			"on A.menuCode = B.menuCode " +
			" where A.isUse =  '1' and  C.parrentCode is not null  and ";

	    	String sql3 = " ( 1=0 ";
	    	for(Map map: roleList){
	    		String roleCode = (String)map.get("roleCode");
	    		sql3 = sql3 + " or B.roleCode = '" + roleCode + "' ";
	    	}
	    	sql3 = sql3 + " ) ";
	    	
	    	sql = "select id1, pid1, name1, url1 from ";
	    	sql = sql + "((" + sql1 + sql3 + ") ";
	    	sql = sql + "union" ;
	    	sql = sql + "(" + sql2 + sql3 + ")) ";
	    	sql = sql + " allMenu ";
	    	sql = sql + " order by menuOrder";
		}
		try{
			list = dba.getMultiRecord(sql);
		}
		catch(DataManipulationException e){
			System.out.println("login access database error!");
			e.printStackTrace();
		}
		return list;
	}

}
