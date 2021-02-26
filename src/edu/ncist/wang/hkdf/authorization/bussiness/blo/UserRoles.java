package edu.ncist.wang.hkdf.authorization.bussiness.blo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.ncist.wang.hkdf.authorization.bussiness.bli.UserRoleAble;
import edu.ncist.wang.hkdf.dao.database.DataBaseAccess;
import edu.ncist.wang.hkdf.dao.database.DataManipulationException;

public class UserRoles implements UserRoleAble {

	public List<Map<String, String>> getUserRoles(DataBaseAccess dba, String userCode) {
		// TODO Auto-generated method stub
		
    	String sql = "select roleCode from authuserrole where userCode ='";
		sql = sql + userCode + "'";
		
		List<Map<String, String>> list = null;
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
