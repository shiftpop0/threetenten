package edu.ncist.wang.hkdf.authorization.bussiness.bli;

import java.util.List;
import java.util.Map;

import edu.ncist.wang.hkdf.dao.database.DataBaseAccess;

public interface RoleMenuAble {
	
	public List<Map<String, String>> getMenuList(DataBaseAccess dba, List<Map<String, String>> roleList);

}
