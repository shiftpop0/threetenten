package edu.ncist.wang.hkdf.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ncist.wang.hkdf.bussiness.bli.FlowHandlable;
import edu.ncist.wang.hkdf.bussiness.bli.RequestHandlable;
import edu.ncist.wang.hkdf.bussiness.bli.ServiceHandlable;
import edu.ncist.wang.hkdf.dao.database.DBConnectionException;
import edu.ncist.wang.hkdf.dao.database.DataBaseAccess;
import edu.ncist.wang.hkdf.dao.xml.FrameConfigParseException;
import edu.ncist.wang.hkdf.dao.xml.FrameController;

public class MainServlet extends HttpServlet{
	
	//Ȩ�޹������ݿ�ϵͳ������
	private String authDataName;
	
	//Ӧ�����ݿ�ϵͳ������
	private String appDataName;
	
	//���ݿ������ļ�����
	private String conFileName;
	
	//��������ļ�������
	private String frameName;
	
	//��ȡӦ�ó���ĵ�·��
	private String appPath;
	
	//��ȡ�����ļ�·��
	private String configPath;
	
	//��ȡ�����ļ�·��
	private String jspPath;
	
	//��ȡ��������·��
	private String realPath;
	
	//��ȡServlet��·��
//	private String servletPath;
	
	public void init(ServletConfig config) throws ServletException{
		
		super.init(config);
		
		authDataName = getInitParameter(config, "AuthDataBaseName");
		
		appDataName = getInitParameter(config, "AppDataBaseName");
		
		appPath = getInitParameter(config, "AppBasePath");
		
		configPath = getInitParameter(config, "ConfigPath");
		
		jspPath = getInitParameter(config, "JspPath");
		
		conFileName = getRealPath(config, getInitParameter(config, "DBConfigFile"));
		
		frameName = getRealPath(config, getInitParameter(config, "FrameConfigFile"));
	}
    
	//�������ļ���ת����ȫ·���ļ��������ַ
	private String getRealPath(ServletConfig config, String name){
		String osName = System.getProperty("os.name");
		System.out.println(osName);
		realPath = config.getServletContext().getRealPath("/");
		if (osName!=null && osName.contains("Window")){
			realPath += "\\WEB-INF\\" + configPath + "\\" + name;
		}else {
			realPath += "WEB-INF/"+configPath+"/"+name;
		}
		System.out.println("realPath : "+realPath);
		return realPath;
	}
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//�����ַ�������ֹ�ύ���ֱ����������
		request.setCharacterEncoding("UTF-8");
		
		DataBaseAccess authDba = null;
    	DataBaseAccess appDba = null;
    	try{
	    	authDba= DataBaseAccess.getInstance(authDataName,conFileName);
	    	appDba = DataBaseAccess.getInstance(appDataName,conFileName);
    	}
    	catch(Exception e){
			e.printStackTrace();
    	}
    	request.setAttribute("AUTHDATABASENAME", authDataName);
    	request.setAttribute("APPDATABASENAME", appDataName);
    	request.setAttribute("CONFILENAME", conFileName);
    	request.setAttribute("AUTHDATACONNECTION", authDba);
    	request.setAttribute("APPDATACONNECTION", appDba);

   		request.setAttribute("REALPATH", realPath);
        
        try{
    		boolean flag = doProcess(request, response);
    		
    		if(false == flag){
    			String welcomeFile = "/" + jspPath + "/login.jsp";
    			request.getRequestDispatcher(welcomeFile).forward(request, response);
    		}
    		
        	FrameController fc = new FrameController(frameName);
            fc.parse();
        }
        catch(FrameConfigParseException e){
        	e.printStackTrace();
        }
        
    }
    
    public String getInitParameter(ServletConfig config, String name){
    	return config.getInitParameter(name);
    }
/*    
    public String getDataBaseName(ServletConfig config){
    	return config.getInitParameter("DataBaseName");
    }
*/    
    public String getConFileName(ServletConfig config){
    	return config.getInitParameter("DBConfigFile");
    }
    
    public String getFrameName(ServletConfig config){
    	return config.getInitParameter("FrameConfigFile");
    }
    
    public String getAppPath(ServletConfig config){
    	
    	return config.getInitParameter("AppBasePath");
    }
    
    public boolean doProcess(HttpServletRequest request, HttpServletResponse response)throws FrameConfigParseException{
    	
        //��ȡ����ģ�������
    	String functionName = request.getPathInfo();
    	if((functionName == null) ||(functionName.equals(""))){
    		functionName = "/";
    	}
    	functionName = functionName.replace('/', ' ');
    	functionName = functionName.trim();
    	
    	//�ж��Ƿ��ǰ���ҳ�棬�Ƿ��أ�����ҳ���ǿ�ܵĲ��֣�����Ҫ����
    	if(functionName.indexOf('.') >= 0){
			String includeJspFile = "/" + jspPath + "/" + functionName;

    		try{
				request.getRequestDispatcher(includeJspFile).forward(request, response);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
			return true;
    	}
    	
    	if(functionName.length() == 0){
    		return false;
    	}
    	
    	//�������ַ�������ĸ�ĳɴ�д
    	String firstChar = functionName.substring(0, 1);
    	String endString = functionName.substring(1);
    	firstChar = firstChar.toUpperCase();
    	
    	String prefixName = firstChar + endString;
    	
    	//������������������֣�ȫ�޶���
    	String requestName = appPath + "." + functionName + ".web." + prefixName + "RequestHandler";
    	String serviceName = appPath + "." + functionName + ".service." + prefixName + "Service";
    	String flowName = appPath + "." + functionName + ".web." + prefixName + "FlowHandler";
    	
    	//��ȡ�ύ�Ķ�������
    	String actionType = request.getParameter("ActionType");

//    	ȫ��map
    	Map map = new HashMap();
    	map.put("REQUEST", request);
    	map.put("RESPONSE", response);
    	map.put("REQUESTNAME", requestName);
    	map.put("SERVICENAME", serviceName);
    	map.put("FLOWNAME", flowName);
    	map.put("ACTIONTYPE", actionType);
    	
    	String result = doAction(map);
    	
    	//Ajax���أ�ֱ�ӽ��������ٴ�����ֵ
    	if("ajax" == result){
    		return true;
    	}
    	
    	//û�е�¼���أ�ת���¼
    	if("noLogin" == result){
    		return false;
    	}
    	String destJspFile = null;
    	
    	List<Map> frameList = null;
    	try{
        	FrameController fc = new FrameController(frameName);
        	frameList = fc.parse();
        	
        	Map actionMap = null;
        	for(Map tempMap: frameList){
        		if(functionName.equals((String)tempMap.get("ACTIONNAME"))){
        			actionMap = tempMap;
        			break;
        		}
        	}
        	
        	List<Map> pointList = (List)actionMap.get("RESULTLIST");
        	Map<String, Object> resultMap = null;
        	for(Map tempMap : pointList){
        		if(actionType.equals((String)tempMap.get("POINTNAME"))){
        			resultMap = tempMap;
        			break;
        		}
        	}
        	
        	List<Map> resultList = (List)resultMap.get("POINTRESULT");
        	for(Map tempMap: resultList){

        		if(result.equals((String)tempMap.get("RESULTNAME"))){
        			destJspFile = (String)tempMap.get("RESULTJSP");
        			break;
        		}
        	}
        	
        	if(destJspFile != null){
    			destJspFile = "/" + jspPath + "/" + destJspFile;
    			request.getRequestDispatcher(destJspFile).forward(request, response);
        	}
        	else {
        		throw new FrameConfigParseException();
        	}
        }
        catch(FrameConfigParseException e){
        	e.printStackTrace();
        }
        catch(Exception e){
        	e.printStackTrace();
        }
        
        return true;
    }
    
    public String doAction(Map map){
    	
    	HttpServletRequest request = (HttpServletRequest)map.get("REQUEST");
    	HttpServletResponse response = (HttpServletResponse)map.get("RESPONSE");
System.out.println(map+"===============");

		String reqName = (String)map.get("REQUESTNAME");
    	String svcName = (String)map.get("SERVICENAME");
    	String flowName = (String)map.get("FLOWNAME");
    	String type = (String)map.get("ACTIONTYPE");
    	
    	String result = null;
    	try{
    		RequestHandlable rh = (RequestHandlable)getInstance(reqName);
    		ServiceHandlable sh = (ServiceHandlable)getInstance(svcName);
    		FlowHandlable fh = (FlowHandlable)getInstance(flowName);
    		
    		Map conditionMap = rh.doRequestProcess(map);
    		map.put("CONDITIONMAP", conditionMap);
    		Map resultMap = sh.doServiceProcess(map);
    		map.put("RESULTMAP", resultMap);
    		result = fh.doFlowProcess(map);
    		
    		//���ڵ�¼������Ҫ�����ʿ���Ȩ����Ϣ�ŵ�session��
    		HttpSession session = request.getSession(true);
    		if("LOGIN".equals(request.getAttribute("LOGINFLAG"))){
        		Map<String, String> userInfo = null;
    			session.setAttribute("RESULTMAP", resultMap);
    			
				userInfo = (Map<String, String>)resultMap.get("USERINFO");
    			session.setAttribute("USERINFO", userInfo);
    		}
    		else{
    			// ��֤�û��Ƿ��¼
    			Map<String, String> userInfo = (Map<String, String>)session.getAttribute("USERINFO");
    			if(null == userInfo){
    				return "noLogin";
    			}
    		}
    		
       		request.setAttribute("RESULTMAP", resultMap);
     		
    	}
    	catch(ProcessClassInstanceException | UnsupportedEncodingException | DBConnectionException e){
    		e.printStackTrace();
    	}
    	
    	return result;
    	
    }
    
    public Object getInstance(String className)throws ProcessClassInstanceException{
    	
    	Object theInst = null;
    	try{
	    	Class theObject = Class.forName(className);
			theInst=(Object)theObject.newInstance();
    	}
    	catch(ClassNotFoundException e){
    		e.printStackTrace();
    		throw new ProcessClassInstanceException();
    	}
    	catch(InstantiationException e){
    		e.printStackTrace();
    		throw new ProcessClassInstanceException();
    	}
    	catch(IllegalAccessException e){
    		e.printStackTrace();
    		throw new ProcessClassInstanceException();
    	}
    	return theInst;
    }

}
