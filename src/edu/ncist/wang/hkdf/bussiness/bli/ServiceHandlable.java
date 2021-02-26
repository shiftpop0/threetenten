package edu.ncist.wang.hkdf.bussiness.bli;

import edu.ncist.wang.hkdf.dao.database.DBConnectionException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public interface ServiceHandlable {

	public Map doServiceProcess(Map map) throws UnsupportedEncodingException, DBConnectionException;

}
