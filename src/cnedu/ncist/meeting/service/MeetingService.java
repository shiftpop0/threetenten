package cnedu.ncist.meeting.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import edu.ncist.wang.hkdf.bussiness.blo.AbstractService;
import edu.ncist.wang.hkdf.dao.database.DBConnectionException;
import edu.ncist.wang.hkdf.dao.database.DataBaseAccess;
import edu.ncist.wang.hkdf.dao.database.DataManipulationException;

public class MeetingService extends AbstractService {

	public Map doServiceProcess(Map map) {
		// TODO Auto-generated method stub
		String actionType = (String)map.get("ACTIONTYPE");
		
		if("meet_SelectInit".equals(actionType)){
			return meet_selectInit(map);
		}
		else if("meet_AddInit".equals(actionType)){
			return meet_addInit(map);
		}
		else if("meet_Add".equals(actionType)){
			return meet_add(map);
		}
		else if("meet_Delete".equals(actionType)){
			return meet_delete(map);
		}
		else{
			ActionTypeError();
		}

		return null;
	}

	public Map<String, Object> meet_selectInit(Map paraMap){
		
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Map<String, String>> meetList = null;
		
		//获取登录用户编号
		HttpSession session = request.getSession(true);
		Map<String, String> userInfo = (Map<String, String>)session.getAttribute("USERINFO");
		String uCode = (String)userInfo.get("userCode");
		
		map.put("USER_CODE", uCode);
		
		String startTime = (String)map.get("START_TIME");
		String endTime = (String)map.get("END_TIME");
		
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
	   	String sql = "select theOrder, inputDate, location, topic, " +
	   			"attendees, inputStudentcode, inputTime, recordurl, memo " +
	   			"from usermeeting " ;
	   	sql = sql + "where 1 = 1 " ;
	   	if((!"".equals(startTime))&&(!"".equals(endTime))){
	   		sql = sql +" and inputDate <= '" + endTime + "' " +
	   			" and inputDate >= '" + startTime + "' ";
	   	}
	   	sql = sql + " and inputStudentCode in ";
	   	sql = sql + "(select userCode from authgroupmember where groupCode in ";
	   	sql = sql + "(select groupCode from authgroupmember where userCode = '" +
			uCode + "'))";
	   	sql = sql + " order by inputDate desc";
//System.out.println(sql);

		try{
			meetList = dba.getMultiRecord(sql);
			dba.close();
		}
		catch(DataManipulationException e){
			System.out.println("get member info access database error!");
			e.printStackTrace();
		}
		catch(DBConnectionException e){
			System.out.println("database close error!");
			e.printStackTrace();
		}
		
		if(null == meetList){
			resultMap.put("RESULT", "fail");
		}
		else{
			resultMap.put("RESULT", "success");
		}
		
		resultMap.put("MEETINFO", meetList);
		resultMap.put("CONDITIONMAP", map);
		
		return resultMap;
	}

	public Map<String, Object> meet_addInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");

		Map conditionMap = (Map)paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, String>> meetList = null;
		Map<String, String> meetInfo = null;
		
		//根据标志判断是新建用户还是修改用户
		String flag = (String)conditionMap.get("FLAG");
		String orderCode = (String)conditionMap.get("MEET_CODE");
		if("update".equals(flag)){
			//获取修改的会议纪要信息
			DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
			
		   	String sql = "select theOrder" +
				", inputDate, location, topic, attendees, recordurl, memo " +
				"from usermeeting " +
				"where theOrder = " ;
		   	sql = sql + orderCode;
			sql = sql + "" ;
//System.out.println(sql);
			try{
				meetList = dba.getMultiRecord(sql);
				dba.close();
			}
			catch(DataManipulationException e){
				System.out.println("get meeting info access database error!");
				e.printStackTrace();
			}
			catch(DBConnectionException e){
				System.out.println("database close error!");
				e.printStackTrace();
			}
			
			if((null == meetList)||(meetList.size() == 0)) {
				resultMap.put("RESULT", "fail");
			}
			else{
				resultMap.put("RESULT", "success");
				meetInfo = meetList.get(0);
			}
		}
		
		resultMap.put("RESULT", "success");
		resultMap.put("CONDITIONMAP", conditionMap);
		resultMap.put("MEETINFO", meetInfo);
		
		return resultMap;
	}

	public Map<String, Object> meet_add(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");

		Map map = (Map)paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String flag = "add";
		String theOrder = "";
		
		Date now = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = df.format(now);
		
		//上传文件
		String realPath = (String)request.getAttribute("REALPATH");
		String serverPath = "\\uploadfile\\meetingRecord\\";
		String tempPath = serverPath + "temp\\";
		
		String fileName = "";

        File fileDir = new File(realPath + serverPath);  
		if(fileDir.isDirectory() && fileDir.exists()==false){  
            fileDir.mkdir();  
        }  
		
        // 实例化一个硬盘文件工厂,用来配置上传组件ServletFileUpload     
        DiskFileItemFactory factory =  new DiskFileItemFactory();  
          
        //设置文件存放的临时文件夹，这个文件夹要真实存在  
        factory.setRepository(new File(realPath + tempPath));  
        
        int len = 1024 * 1024;
        
        //设置最大占用的内存  
        factory.setSizeThreshold(len);  
          
        //创建ServletFileUpload对象  
        ServletFileUpload sfu = new ServletFileUpload(factory);  
        sfu.setHeaderEncoding("utf-8");  
          
        //设置单个文件最大值byte   
        sfu.setFileSizeMax(len * 1024);  
          
        //所有上传文件的总和最大值byte     
        sfu.setSizeMax(len * 1024);  
          
        List<FileItem> items =  null;  
        
        try {  
            items = sfu.parseRequest(request);  
        }catch (SizeLimitExceededException e) {     
            System.out.println("文件大小超过了最大值");     
        } catch(FileUploadException e) {     
            e.printStackTrace();     
        }   
          
        //取得items的迭代器  
        Iterator<FileItem> iter = items==null?null:items.iterator();  
          
        //迭代items，目前系统只提供一个文件上传 
        while(iter!=null && iter.hasNext()){  
            FileItem item = (FileItem) iter.next();  
              
            if (item.isFormField()) {
                //如果传过来的是普通的表单域  
            	
            	try{
                	//获取表单域标志FLAG
                    if(item.getFieldName().equals("FLAG")){
                    	flag = item.getString("utf-8");
                    	if(null == flag){
                    		flag = "";
                    	}
                        map.put("FLAG", flag);
                    }
                	
                	//获取表单域自动序号MEET_CODE
                    if(item.getFieldName().equals("MEET_CODE")){
                    	theOrder = item.getString("utf-8");
                    	if(null == theOrder){
                    		theOrder = "";
                    	}
//System.out.println(theOrder);                    	
                        map.put("MEET_CODE", theOrder);
                    }
                	
                	//获取表单域输入时间INPUT_TIME
                    if(item.getFieldName().equals("INPUT_TIME")){
                    	String inputTime = item.getString("utf-8");
                    	if(null == inputTime){
                    		inputTime = "";
                    	}
                        map.put("INPUT_TIME", inputTime);
                    }
                	
                	//获取表单域会议地点MEET_LOCATION
                    if(item.getFieldName().equals("MEET_LOCATION")){
                    	String location = item.getString("utf-8");
                    	if(null == location){
                    		location = "";
                    	}
                        map.put("MEET_LOCATION", location);
                    }
                	
                	//获取表单域会议主题MEET_TOPIC
                    if(item.getFieldName().equals("MEET_TOPIC")){
                    	String topic = item.getString("utf-8");
                    	if(null == topic){
                    		topic = "";
                    	}
                        map.put("MEET_TOPIC", topic);
                    }
                	
                	//获取表单域会议人员MEET_ATTENDEES
                    if(item.getFieldName().equals("MEET_ATTENDEES")){
                    	String attendees = item.getString("utf-8");
                    	if(null == attendees){
                    		attendees = "";
                    	}
                        map.put("MEET_ATTENDEES", attendees);
                    }
                	
                	//获取表单域备注MEET_MEMO
                    if(item.getFieldName().equals("MEET_MEMO")){
                    	String memo = item.getString("utf-8");
                    	if(null == memo){
                    		memo = "";
                    	}
                        map.put("MEET_MEMO", memo);
                    }
            	}
            	catch(UnsupportedEncodingException e){
            		e.printStackTrace();
            	}
            	
            }
            else {
            	//这里是上传文件的表单域
                fileName = item.getName();
				int pos = item.getName().lastIndexOf("\\");
                if(pos >= 0){
                	fileName = item.getName().substring(pos);
                }

                if((null != fileName) && (!"".equals(fileName))){
	                
    				pos = item.getName().lastIndexOf(".");
                    if(pos >= 0){
                    	fileName = item.getName().substring(pos);
System.out.println(fileName);                    	
                    }
                    else{
                    	fileName = "";
                    }
    				
    				String uuid = UUID.randomUUID().toString();  
	                fileName = uuid + fileName;
                	
	                try{
		                BufferedInputStream in = new BufferedInputStream(item.getInputStream());  
		                //文件存储在D:/upload/images目录下,这个目录也得存在   
		                BufferedOutputStream out = new BufferedOutputStream(     
		                        new FileOutputStream(new File(realPath + serverPath + fileName)));   
		                Streams.copy(in, out, true);
	                }
	                catch(IOException e){
	                	System.out.println(e.toString());
	                }
                }
                else {
                	fileName = "";
                }

            }  
        }  
		//上传文件结束
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
	   	String sql = "";
	   	if("add".equals(flag)){
	   		
			//获取用户编号
			HttpSession session = request.getSession(true);
			Map<String, String> userInfo = (Map<String, String>)session.getAttribute("USERINFO");
			String uCode = (String)userInfo.get("userCode");
			
		   	sql = "insert into usermeeting(" +
   			"inputDate, location, topic, attendees, inputStudentCode, inputTime, recordurl, memo" +
			") " +
			"values(" ;
		
			sql = sql + "'" + (String)map.get("INPUT_TIME") + "'";
			sql = sql + ",'" + (String)map.get("MEET_TOPIC") + "'";
			sql = sql + ",'" + (String)map.get("MEET_LOCATION") + "'";
			sql = sql + ",'" + (String)map.get("MEET_ATTENDEES") + "'";
			sql = sql + ",'" + uCode + "'";
			sql = sql + ",'" + dateStr + "'";
			if("".equals(fileName)){
				sql = sql + ",''";
			}
			else{
				sql = sql + ",'" + "/Trainning/uploadfile/meetingRecord/" + fileName + "'";
			}
			sql = sql + ",'" + (String)map.get("MEET_MEMO") + "'";
		   	sql = sql + ")";
		   	
	   	}
	   	else{
	   		sql = "update usermeeting set ";
	   		sql = sql + "inputDate = '" + (String)map.get("INPUT_TIME") + "' ";
	   		sql = sql + ",location = '" + (String)map.get("MEET_LOCATION") + "' ";
	   		sql = sql + ",topic = '" + (String)map.get("MEET_TOPIC") + "' ";
	   		sql = sql + ",attendees = '" + (String)map.get("MEET_ATTENDEES") + "' ";
			if(!"".equals(fileName)){
		   		sql = sql + ",recordurl = '" +  "/uploadfile/meetingRecord/" + fileName + "' ";
			}
	   		sql = sql + ",memo = '" + (String)map.get("MEET_MEMO") + "' ";
	   		sql = sql + " where ";
	   		sql = sql + " theOrder = " + theOrder + "";
	   	}
System.out.println(sql);
		try{
			dba.executeUpdate(sql);
//			dba.close();
		}
		catch(DataManipulationException e){
			System.out.println("update meeting info access database error!");
			e.printStackTrace();
		}
		
		//获取会议记录列表开始
		List<Map<String, String>> meetList = null;
		
		//获取登录用户编号
		HttpSession session = request.getSession(true);
		Map<String, String> userInfo = (Map<String, String>)session.getAttribute("USERINFO");
		String uCode = (String)userInfo.get("userCode");
		
		map.put("USER_CODE", uCode);
		
		String startTime = (String)map.get("START_TIME");
		String endTime = (String)map.get("END_TIME");
		
	   	sql = "select theOrder, inputDate, location, topic, " +
	   			"attendees, inputStudentcode, inputTime, memo " +
	   			"from usermeeting " ;
	   	sql = sql + "where 1 = 1 " ;
	   	if((!"".equals(startTime))&&(!"".equals(endTime))&& (null != startTime) && (null != endTime)){
	   	sql = sql +" and inputDate <= '" + endTime + "' " +
	   			" and inputDate >= '" + startTime + "' ";
	   	}
	   	sql = sql + " and inputStudentCode in ";
	   	sql = sql + "(select userCode from authgroupmember where groupCode in ";
	   	sql = sql + "(select groupCode from authgroupmember where userCode = '" +
			uCode + "'))";
//System.out.println(sql);

		try{
			meetList = dba.getMultiRecord(sql);
			dba.close();
		}
		catch(DataManipulationException e){
			System.out.println("get member info access database error!");
			e.printStackTrace();
		}
		catch(DBConnectionException e){
			System.out.println("database close error!");
			e.printStackTrace();
		}
		
		if(null == meetList){
			resultMap.put("RESULT", "fail");
		}
		else{
			resultMap.put("RESULT", "success");
		}
		
		resultMap.put("MEETINFO", meetList);

		//获取会议列表结束，返回显示列表
		
		resultMap.put("CONDITIONMAP", map);
		
		return resultMap;
	}

	public Map<String, Object> meet_delete(Map paraMap){
		
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Map<String, String>> meetList = null;
		
		//获取登录用户编号
		HttpSession session = request.getSession(true);
		Map<String, String> userInfo = (Map<String, String>)session.getAttribute("USERINFO");
		String uCode = (String)userInfo.get("userCode");
		
		map.put("USER_CODE", uCode);
		
		String theOrder = (String)map.get("MEET_CODE");
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
	   	String sql = "delete from  usermeeting " ;
	   	sql = sql + "where theOrder =" + theOrder ;

		try{
			dba.executeUpdate(sql);
		}
		catch(DataManipulationException e){
			System.out.println("get meeting info access database error!");
			e.printStackTrace();
		}
		
	   	sql = "select theOrder, inputDate, location, topic, " +
			"attendees, inputStudentcode, inputTime, recordurl, memo " +
			"from usermeeting " ;
		sql = sql + "where 1 = 1 " ;
		sql = sql + " and inputStudentCode in ";
		sql = sql + "(select userCode from authgroupmember where groupCode in ";
		sql = sql + "(select groupCode from authgroupmember where userCode = '" +
		uCode + "'))";
//System.out.println(sql);
		
		try{
			meetList = dba.getMultiRecord(sql);
			dba.close();
		}
		catch(DataManipulationException e){
			System.out.println("get member info access database error!");
			e.printStackTrace();
		}
		catch(DBConnectionException e){
			System.out.println("database close error!");
			e.printStackTrace();
		}

		if(null == meetList){
			resultMap.put("RESULT", "fail");
		}
		else{
			resultMap.put("RESULT", "success");
		}
		
		resultMap.put("MEETINFO", meetList);
		resultMap.put("CONDITIONMAP", map);
		
		return resultMap;
	}

}
