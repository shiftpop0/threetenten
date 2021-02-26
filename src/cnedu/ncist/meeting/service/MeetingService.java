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
		
		//��ȡ��¼�û����
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
		
		//���ݱ�־�ж����½��û������޸��û�
		String flag = (String)conditionMap.get("FLAG");
		String orderCode = (String)conditionMap.get("MEET_CODE");
		if("update".equals(flag)){
			//��ȡ�޸ĵĻ����Ҫ��Ϣ
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
		
		//�ϴ��ļ�
		String realPath = (String)request.getAttribute("REALPATH");
		String serverPath = "\\uploadfile\\meetingRecord\\";
		String tempPath = serverPath + "temp\\";
		
		String fileName = "";

        File fileDir = new File(realPath + serverPath);  
		if(fileDir.isDirectory() && fileDir.exists()==false){  
            fileDir.mkdir();  
        }  
		
        // ʵ����һ��Ӳ���ļ�����,���������ϴ����ServletFileUpload     
        DiskFileItemFactory factory =  new DiskFileItemFactory();  
          
        //�����ļ���ŵ���ʱ�ļ��У�����ļ���Ҫ��ʵ����  
        factory.setRepository(new File(realPath + tempPath));  
        
        int len = 1024 * 1024;
        
        //�������ռ�õ��ڴ�  
        factory.setSizeThreshold(len);  
          
        //����ServletFileUpload����  
        ServletFileUpload sfu = new ServletFileUpload(factory);  
        sfu.setHeaderEncoding("utf-8");  
          
        //���õ����ļ����ֵbyte   
        sfu.setFileSizeMax(len * 1024);  
          
        //�����ϴ��ļ����ܺ����ֵbyte     
        sfu.setSizeMax(len * 1024);  
          
        List<FileItem> items =  null;  
        
        try {  
            items = sfu.parseRequest(request);  
        }catch (SizeLimitExceededException e) {     
            System.out.println("�ļ���С���������ֵ");     
        } catch(FileUploadException e) {     
            e.printStackTrace();     
        }   
          
        //ȡ��items�ĵ�����  
        Iterator<FileItem> iter = items==null?null:items.iterator();  
          
        //����items��Ŀǰϵͳֻ�ṩһ���ļ��ϴ� 
        while(iter!=null && iter.hasNext()){  
            FileItem item = (FileItem) iter.next();  
              
            if (item.isFormField()) {
                //���������������ͨ�ı���  
            	
            	try{
                	//��ȡ�����־FLAG
                    if(item.getFieldName().equals("FLAG")){
                    	flag = item.getString("utf-8");
                    	if(null == flag){
                    		flag = "";
                    	}
                        map.put("FLAG", flag);
                    }
                	
                	//��ȡ�����Զ����MEET_CODE
                    if(item.getFieldName().equals("MEET_CODE")){
                    	theOrder = item.getString("utf-8");
                    	if(null == theOrder){
                    		theOrder = "";
                    	}
//System.out.println(theOrder);                    	
                        map.put("MEET_CODE", theOrder);
                    }
                	
                	//��ȡ��������ʱ��INPUT_TIME
                    if(item.getFieldName().equals("INPUT_TIME")){
                    	String inputTime = item.getString("utf-8");
                    	if(null == inputTime){
                    		inputTime = "";
                    	}
                        map.put("INPUT_TIME", inputTime);
                    }
                	
                	//��ȡ�������ص�MEET_LOCATION
                    if(item.getFieldName().equals("MEET_LOCATION")){
                    	String location = item.getString("utf-8");
                    	if(null == location){
                    		location = "";
                    	}
                        map.put("MEET_LOCATION", location);
                    }
                	
                	//��ȡ�����������MEET_TOPIC
                    if(item.getFieldName().equals("MEET_TOPIC")){
                    	String topic = item.getString("utf-8");
                    	if(null == topic){
                    		topic = "";
                    	}
                        map.put("MEET_TOPIC", topic);
                    }
                	
                	//��ȡ���������ԱMEET_ATTENDEES
                    if(item.getFieldName().equals("MEET_ATTENDEES")){
                    	String attendees = item.getString("utf-8");
                    	if(null == attendees){
                    		attendees = "";
                    	}
                        map.put("MEET_ATTENDEES", attendees);
                    }
                	
                	//��ȡ����עMEET_MEMO
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
            	//�������ϴ��ļ��ı���
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
		                //�ļ��洢��D:/upload/imagesĿ¼��,���Ŀ¼Ҳ�ô���   
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
		//�ϴ��ļ�����
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
	   	String sql = "";
	   	if("add".equals(flag)){
	   		
			//��ȡ�û����
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
		
		//��ȡ�����¼�б�ʼ
		List<Map<String, String>> meetList = null;
		
		//��ȡ��¼�û����
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

		//��ȡ�����б������������ʾ�б�
		
		resultMap.put("CONDITIONMAP", map);
		
		return resultMap;
	}

	public Map<String, Object> meet_delete(Map paraMap){
		
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Map<String, String>> meetList = null;
		
		//��ȡ��¼�û����
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
