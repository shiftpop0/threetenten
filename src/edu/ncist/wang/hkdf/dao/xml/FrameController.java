package edu.ncist.wang.hkdf.dao.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import edu.ncist.wang.hkdf.dao.xml.FrameConfigParseException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class FrameController {

	//��ܿ������ļ�����
	private String frameName;
		
	//��������List
	private List<Map> frameList = new ArrayList<Map>();
	
	
	public FrameController(String frameName){
		this.frameName = frameName;
	}
	
	public List<Map> parse()throws FrameConfigParseException{
		
        try{
        	
	 		SAXReader saxReader = new SAXReader() ;
	
	        Document document = saxReader.read(new File(frameName));
	
	        // ��ȡ��������ļ��ĸ�Ԫ��
	        Element root = document.getRootElement();
	
	        //�������еĶ���������Ϣ
	        for (Iterator iter = root.elementIterator(); iter.hasNext();)
	        {
	        	Map map = new HashMap();
	        	
	        	//��ȡһ�������ڵ�
	        	Element e = (Element) iter.next();
	        	
	        	//�ж��Ƿ���Action�ڵ�
	            if("Action".equals(e.getName())){
		            
		        	//��ȡAction�ڵ�����֣�����login
	            	map.put("ACTIONNAME", e.attributeValue("name"));
		        	
		        	List<Map> resultList = new ArrayList<Map>();
		        	
		        	//������Action�ڵ������в�����
		        	for(Iterator pointIter = e.elementIterator();pointIter.hasNext();){
		        		
		        		Map operationMap = new HashMap();
		        		List<Map> pointList = new ArrayList<Map>();
		        		
		        		//��ȡһ��������
		        		Element point = (Element)pointIter.next();
		        		
		        		//��Ӳ���������ֵ�operationMap
		        		operationMap.put("POINTNAME", point.attributeValue("name"));
			        	
		        		//��������������µ����н������
			        	for(Iterator innerIter = point.elementIterator("Result"); innerIter.hasNext();){
			        		
			        		//�õ�һ���������
			        		Element resultElement = (Element)innerIter.next();
			        		
			        		Map<String, String> eleMap = new HashMap<String, String>();
			        		
			        		//�ѽ�����������ƺͶ�Ӧ��JSPҳ��ŵ�eleMap��
			        		eleMap.put("RESULTNAME", resultElement.attributeValue("name"));
			        		eleMap.put("RESULTJSP", resultElement.getText());

			        		//���õ���һ�������㶯������ŵ��б�pointList
			        		pointList.add(eleMap);
			        	}
			        	
			        	//����һ�����������Ϣ����ӵ������б�resultList
			        	operationMap.put("POINTRESULT", pointList);
			        	resultList.add(operationMap);
		        	}
		        	
		        	//���������浽��������map��
		        	map.put("RESULTLIST", resultList);
	            }
	            
	            //��������ӵ����յĿ���б�frameList��
		        frameList.add(map);
	        }
	        
        }
        catch(NullPointerException e){
        	System.out.println("Demo Frame configration xml filename is null");
        	e.printStackTrace();
        }
        catch(DocumentException e){
        	System.out.println("Demo Frame configration xml file don't exist!");
        	e.printStackTrace();
        }
        
        return frameList;
	}
	/**/	
}
