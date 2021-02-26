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

	//框架库配置文件名字
	private String frameName;
		
	//保存结果的List
	private List<Map> frameList = new ArrayList<Map>();
	
	
	public FrameController(String frameName){
		this.frameName = frameName;
	}
	
	public List<Map> parse()throws FrameConfigParseException{
		
        try{
        	
	 		SAXReader saxReader = new SAXReader() ;
	
	        Document document = saxReader.read(new File(frameName));
	
	        // 获取框架配置文件的根元素
	        Element root = document.getRootElement();
	
	        //遍历所有的动作配置信息
	        for (Iterator iter = root.elementIterator(); iter.hasNext();)
	        {
	        	Map map = new HashMap();
	        	
	        	//获取一个动作节点
	        	Element e = (Element) iter.next();
	        	
	        	//判断是否是Action节点
	            if("Action".equals(e.getName())){
		            
		        	//获取Action节点的名字，例如login
	            	map.put("ACTIONNAME", e.attributeValue("name"));
		        	
		        	List<Map> resultList = new ArrayList<Map>();
		        	
		        	//遍历此Action节点下所有操作点
		        	for(Iterator pointIter = e.elementIterator();pointIter.hasNext();){
		        		
		        		Map operationMap = new HashMap();
		        		List<Map> pointList = new ArrayList<Map>();
		        		
		        		//获取一个操作点
		        		Element point = (Element)pointIter.next();
		        		
		        		//添加操作点的名字到operationMap
		        		operationMap.put("POINTNAME", point.attributeValue("name"));
			        	
		        		//遍历这个操作点下的所有结果动作
			        	for(Iterator innerIter = point.elementIterator("Result"); innerIter.hasNext();){
			        		
			        		//得到一个结果动作
			        		Element resultElement = (Element)innerIter.next();
			        		
			        		Map<String, String> eleMap = new HashMap<String, String>();
			        		
			        		//把结果动作的名称和对应的JSP页面放到eleMap中
			        		eleMap.put("RESULTNAME", resultElement.attributeValue("name"));
			        		eleMap.put("RESULTJSP", resultElement.getText());

			        		//将得到的一个操作点动作结果放到列表pointList
			        		pointList.add(eleMap);
			        	}
			        	
			        	//保存一个操作点的信息，添加到动作列表resultList
			        	operationMap.put("POINTRESULT", pointList);
			        	resultList.add(operationMap);
		        	}
		        	
		        	//将动作保存到动作数据map中
		        	map.put("RESULTLIST", resultList);
	            }
	            
	            //将动作添加到最终的框架列表frameList中
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
