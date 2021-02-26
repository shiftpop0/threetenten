package edu.ncist.wang.hkdf.authorization.bussiness.bli;

import java.util.List;
import java.util.Map;

import edu.ncist.wang.hkdf.dao.database.DataBaseAccess;

public interface UserRoleAble {
	
	public List<Map<String, String>> getUserRoles(DataBaseAccess dba, String userCode);

}
