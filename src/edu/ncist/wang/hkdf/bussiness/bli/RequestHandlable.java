package edu.ncist.wang.hkdf.bussiness.bli;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface RequestHandlable {
	
	public Map doRequestProcess(Map map);

}
