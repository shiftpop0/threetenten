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

	//���ݿ�ϵͳ������
	private String dataName;
	//���ݿ������ļ�����
	private String conFileName;
	
	//��������Map
	private Map<String, String> dbMap = new HashMap<String, String>();

	public DBConnection(String dataName, String conFileName){
		this.dataName = dataName;
		this.conFileName = conFileName;
	}
	
	public Map<String, String> parse()throws DBConfigParseException{
		
        try{
        	
	 		SAXReader saxReader = new SAXReader() ;
	
	        Document document = saxReader.read(new File(conFileName));
	
	        // ��ȡ���ݿ������ļ��ĸ�Ԫ��
	        Element root = document.getRootElement();
	
	        //�ҵ���ǰ���ݿ��������Ϣ
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
	        
	        //��ȡ�����ַ���
	        dbMap.put("CONNECTIONSTRING", currentDB.element("ConnectionString").getText());
	        
	        //��ȡ��������
	        dbMap.put("DRIVER", currentDB.element("Driver").getText());
	       
	        //��ȡ�û���
	        dbMap.put("USERNAME", currentDB.element("UserName").getText());
	        
	        //��ȡ����
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
