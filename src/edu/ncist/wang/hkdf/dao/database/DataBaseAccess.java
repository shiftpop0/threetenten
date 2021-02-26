package edu.ncist.wang.hkdf.dao.database;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;  
import java.sql.Statement;

import edu.ncist.wang.hkdf.dao.xml.DBConfigParseException;
import edu.ncist.wang.hkdf.dao.xml.DBConnection;
import edu.ncist.wang.hkdf.dao.database.DBConnectionException;

public class DataBaseAccess {
	
	//数据库管理系统名称
	private String dataName;
	
	//数据库配置文件名称
	private String conFileName;
	
	//数据库配置文件解析类
	private DBConnection dbc;
	
	//数据库配置文件解析结果
	private Map<String, String> dbConfig = null;
	
	//数据库连接
	Connection con = null;
	
	public DataBaseAccess(String dataName, String conFileName){
		
		this.dataName = dataName;
		this.conFileName = conFileName;
		
		init();
	}
	
	public String init(){
		
		String url = null;
		
    	try{
	    	dbc = new DBConnection(dataName, conFileName);
	    	dbConfig = dbc.parse();
	    	
	    	//获取数据库连接字符串
	    	url = dbConfig.get("CONNECTIONSTRING");
    	}
    	catch(DBConfigParseException e){
    		System.out.println("DB configration xml file parsing error!");
    		e.printStackTrace();
    	}
    	catch(Exception e){
    		System.out.println("DB configration parsing error!");
    		e.printStackTrace();
    	}
    	return url;
	}
	
	public Connection open() throws DBConnectionException{
		
		String url = init();
		if(null == url){
			throw new DBConnectionException();
		}
		
		try{
			if(null == con){
				//设置数据库访问的编码方式
				url = url + "?useUnicode=true&characterEncoding=UTF-8";
				Class.forName(dbConfig.get("DRIVER"));//指定连接类型  
	            con = DriverManager.getConnection(url, dbConfig.get("USERNAME"), dbConfig.get("PASSWORD"));//获取连接
			}
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
			throw new DBConnectionException();
		
		}
		catch(SQLException e){
			e.printStackTrace();
			throw new DBConnectionException();
		
		}
		
		return con;
	}
	
	public Connection getConnection()throws DBConnectionException{
		if(null == con){
			return open();
		}
		else{
			return con;
		}
	}
	
	public void close()throws DBConnectionException{
		try{
			if(con != null){
				con.close();
			}
		}
		catch(SQLException e){
			e.printStackTrace();
			throw new DBConnectionException();
		}
	}
	
	public List<Map<String, String>> getMultiRecord(String sql)throws DataManipulationException{
		
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		
		try{
			PreparedStatement pst = con.prepareStatement(sql);//准备执行语句
			ResultSet rs = pst.executeQuery();//执行语句，得到结果集
			ResultSetMetaData rsmd = rs.getMetaData() ;
			
            while (rs.next()) {
            	Map<String, String> record =new HashMap<String, String>();
            	for(int i=0; i< rsmd.getColumnCount(); i++){
            		//说明：不宜使用getColumnName方法，否则无法得到as后的列名
            		String colName = rsmd.getColumnLabel(i+1);
            		
            		String colContent = rs.getString(i+1);
            		
            		record.put(colName, colContent);
            	}
            	list.add(record);
            }
            
            rs.close();  
            pst.close();
		}
		catch(SQLException e){
			e.printStackTrace();
			throw 	new DataManipulationException();
		}
//System.out.println("DataBase:"+list);
		return list;
	}
	
	public Map<String, String> getSingleRecord(String sql)throws DataManipulationException{
		
		List<Map<String, String>> list = null;
		Map<String, String> map = null;
		try{
			list = getMultiRecord(sql);
			if(null != list){
				if(list.size() > 0){
					map = list.get(0);
				}
			}
		}
		catch(DataManipulationException e){
			e.printStackTrace();
			throw new DataManipulationException();
		}
		
		return map;
	}
	
	public void  excuteBatchSql(String [] sqls)throws DataManipulationException{
		
		try{
			Statement st=con.createStatement();
			con.setAutoCommit(false);
			
			for(int i=0; i<sqls.length; i++){
				st.addBatch(sqls[i]);
			}
			st.executeBatch();
			con.commit();
			st.close();
		}
		catch(SQLException e){
			e.printStackTrace();
			throw new DataManipulationException();
		}
		
		return ;
	}
	
	public void executeUpdate(String sql)throws DataManipulationException{
		
		try{
			Statement st=con.createStatement();
			st.executeUpdate(sql);
			st.close();
		}
		catch(SQLException e){
			e.printStackTrace();
			throw new DataManipulationException();
		}
	}
	
	public static DataBaseAccess getInstance(String dataName, String conFileName)throws DBConnectionException{
		
		DataBaseAccess dba = null;
		try{
			dba= new DataBaseAccess(dataName, conFileName);
			dba.getConnection();
		}
		catch(DBConnectionException e){
		  throw e;
		}
		return dba;
	}

}
