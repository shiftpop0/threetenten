package edu.ncist.wang.hkdf.dao.xml;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class DBConnection {

	//数据库系统的名字
	private String dataName;
	//数据库配置文件名字
	private String conFileName;
	
	//保存结果的Map
	private Map<String, String> dbMap = new HashMap<String, String>();

	public DBConnection(String dataName, String conFileName){
		this.dataName = dataName;
		this.conFileName = conFileName;
	}
	
	public Map<String, String> parse()throws DBConfigParseException{
		
        try{
        	
	 		SAXReader saxReader = new SAXReader() ;
	
	        Document document = saxReader.read(new File(conFileName));
	
	        // 获取数据库配置文件的根元素
	        Element root = document.getRootElement();
	
	        //找到当前数据库的配置信息
	        Element currentDB = null;
	        for (Iterator iter = root.elementIterator(); iter.hasNext();)
	        {
	            Element e = (Element) iter.next();
	            if(dataName.equals(e.attributeValue("name"))){
	            	currentDB = e;
	            }
	        }
	        if(null == currentDB){
	        	throw new DBMSNotFoundException();
	        }
	        
	        //获取连接字符串
	        dbMap.put("CONNECTIONSTRING", currentDB.element("ConnectionString").getText());
	        
	        //获取驱动程序
	        dbMap.put("DRIVER", currentDB.element("Driver").getText());
	       
	        //获取用户名
	        dbMap.put("USERNAME", currentDB.element("UserName").getText());
	        
	        //获取密码
	        dbMap.put("PASSWORD", currentDB.element("Password").getText());
	        
        }
        catch(NullPointerException e){
        	System.out.println("Database configration xml filename is null");
        	e.printStackTrace();
        }
        catch(DocumentException e){
        	System.out.println("Database configration xml file don't exist!");
        	e.printStackTrace();
        }
        catch(DBMSNotFoundException e){
        	System.out.println("Database Management name don't exist in Database configration xml file!");
        	e.printStackTrace();
        }
        
        return dbMap;
	}
	
}
