package edu.ncist.wang.hkdf.authorization.bussiness.bli;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface MenuListAble {
	
	public List<Map<String, String>> login(HttpServletRequest request, Map map);
	
	public Map<String, String> getUserInfo(HttpServletRequest request, String userCode);
	
	public List<Map<String, String>> getMenuList(HttpServletRequest request, String userCode);

}
