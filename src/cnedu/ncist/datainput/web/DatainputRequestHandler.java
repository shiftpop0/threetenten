package cnedu.ncist.datainput.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import cnedu.ncist.util.ExcelUtil;








import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import cnedu.ncist.util.DatainputService;
import cnedu.ncist.util.MysqlDemo;

import edu.ncist.wang.hkdf.bussiness.blo.AbstractRequestHandler;


public class DatainputRequestHandler extends AbstractRequestHandler {
	private static final int buffer = 2048; 


	public Map doRequestProcess(Map map){
		
		String actionType = (String)map.get("ACTIONTYPE");
		if("fileup".equals(actionType)){
			return fileup(map);
		}
		else if("delete".equals(actionType)){
			return delete(map);
		}
		else if("put".equals(actionType)){
			return put(map);
		}else if ("calculate".equals(actionType)) {
			return calculate(map);
		}else if ("cratetable".equals(actionType)) {
			return cratetable(map);
		}else if ("Excle".equals(actionType)) {
			return excle(map);
		}
		else{
			ActionTypeError();
		}
		return null;
	}

	private Map excle(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
	
		//ExcelUtil eu = new ExcelUtil();
		//String a = eu.uploadFile(request);
		
		//System.out.println(a+"-------------");
		
		//List<Map<String, String>> majorList = eu.loadExcel(a);
		
		//System.out.println(majorList.get(0).get("���֤��"));
		
		String year = request.getParameter("year");
		
		conditionMap.put("YEAR", year);
		//conditionMap.put("MAJORLIST", majorList);
		return conditionMap;
	}

	private Map cratetable(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
		return conditionMap;
	}

	private Map calculate(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
		return conditionMap;
	}

	private Map put(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		String year = request.getParameter("year");
		conditionMap.put("YEAR", year);
		return conditionMap;
	}

	private Map delete(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
		return conditionMap;
	}

	public Map<String, String> fileup(Map paraMap){
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
	
		String year = request.getParameter("year");
		
		System.out.println(year);

		//���FileItem�� ��ס��ÿ�������嵱�����ݣ� 
		
		//��ù����� 
		DiskFileItemFactory f= new DiskFileItemFactory(); 
		
		//��ý������� 
		ServletFileUpload sfu = new ServletFileUpload(f); 
	   System.out.println("==============================");
		//����request : 
		try {
			System.out.println("==============================");
			List<FileItem> items = (List)sfu.parseRequest(request);
			
			FileItem f1 = items.get(0);//��ͨ�ֶΣ� ��ӦFileItem��
			FileItem f2 = items.get(1);//�ļ��ϴ��� ��Ӧ��FileItem�� 
			System.out.println("1111111"+f1);
			System.out.println("222222222"+f2);    
		
			DatainputService ds = new DatainputService();
			
			//��ͨ�ֶλ��
			/*
			 * FileItem�� ����൱��API������ 
			 *  
			 *  isFormField();�жϸ�����Ƿ���һ����ͨ����� ��һ����ͨ����� true�� 
			 *               false�� ������һ���ļ��ϴ������ 
			 *   
			 *  //��ͨ�ֶκõ����Ժ�ֵ�� 
			 *  getFieldName();�����ͨ�ֶε��������ƣ� 
			 *  getString(); ����������ƶ�Ӧ��ֵ����
			 *  
			 *  //�ļ��ϴ�������� 
			 *  getName();����ļ������ƣ� 
			 *  getInputStream(); ���ص�InputStream �� �����з�װ��ͼƬ�����ݣ� 
			 *  getSize();���ͼƬ�ֽڸ�����  
			 *  
			 *  
			 *            
			 */
			boolean flag1 = f1.isFormField();// true
			boolean flag2 = f2.isFormField();// false
			
//			System.out.println(flag1);
			System.out.println("��־λ"+flag2);
			
		
			
			if(!flag2){//�ļ��ϴ��� 
				//�ļ������ƣ� 
				System.out.println(111);
				String name = f2.getName();//����ļ�������; 
				//����ļ��Ĵ�С�� 
				long size = f2.getSize();
				
				//�ļ��ľ�������ݣ� һ����һ������ 
				InputStream in= f2.getInputStream();//in �Ͷ�ȡ�������嵱�е����ݣ� 
				
				System.out.println("�ļ�������"+name);
				System.out.println(size);
				System.out.println(in.available());// ���ʣ�µ��ֽڸ����� 
				//�ҵ�λ�ý����ݽ��д�ţ�  
				
				String path="D:/Temp"+"/"+new Date().getTime()+name; 
				System.out.println("------------------------"+path);
				
				//ʹ�����Խӣ� 
				IOUtils.copy(in, new FileOutputStream(new File(path))); 
				in.close();
				System.gc(); //����Դ���л��� �����ļ���ռ���޷���ѹ
				
				
				
				//��zip�ļ����н�ѹ
				List<String> unZip = unZip(path);
				System.out.println(unZip);
			   
				for(int i=0;i<unZip.size();i++){
					ds.input(unZip.get(i),year);
				}
				
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return conditionMap;
		
		}
	
	/** 
	  * ��ѹZip�ļ�
	  * ���ؽ�ѹ���ļ��������ļ����ļ��еľ���·���ļ��� 
	  * @param path �ļ�Ŀ¼ 
	  */
	 public static List<String> unZip(String path){ 
	    int count = -1; 
	    String savepath = ""; 
	    File file = null; 
	    InputStream is = null; 
	    FileOutputStream fos = null; 
	    BufferedOutputStream bos = null; 
	    savepath = path.substring(0, path.lastIndexOf(".")) + File.separator; //�����ѹ�ļ�Ŀ¼ 
	    String fpath = savepath.substring(0, savepath.length()-1);
	    System.out.println("�����ѹ�ļ�Ŀ¼:"+fpath);  //D:/Temp/15580650806622018����������
	    new File(savepath).mkdir(); //��������Ŀ¼ 
	    ZipFile zipFile = null; 
	    try{ 
	      zipFile = new ZipFile(path,"gbk"); //��������������� 
	      Enumeration<?> entries = zipFile.getEntries(); 
	      while(entries.hasMoreElements()){ 
	        byte buf[] = new byte[buffer]; 
	        ZipEntry entry = (ZipEntry)entries.nextElement(); 
	        String filename = entry.getName(); 
	        boolean ismkdir = false; 
	        if(filename.lastIndexOf("/") != -1){ //�����ļ��Ƿ�����ļ��� 
	         ismkdir = true; 
	        } 
	        filename = savepath + filename; 
	        if(entry.isDirectory()){ //������ļ����ȴ��� 
	         file = new File(filename); 
	         file.mkdirs(); 
	          continue; 
	        } 
	        file = new File(filename); 
	        if(!file.exists()){ //�����Ŀ¼�ȴ��� 
	         if(ismkdir){ 
	         new File(filename.substring(0, filename.lastIndexOf("/"))).mkdirs(); //Ŀ¼�ȴ��� 
	         } 
	        } 
	        file.createNewFile(); //�����ļ� 
	        is = zipFile.getInputStream(entry); 
	        fos = new FileOutputStream(file); 
	        bos = new BufferedOutputStream(fos, buffer); 
	        while((count = is.read(buf)) > -1){ 
	          bos.write(buf, 0, count); 
	        } 
	        bos.flush(); 
	        bos.close(); 
	        fos.close(); 
	        is.close(); 
	      } 
	      zipFile.close(); 
	    }catch(IOException ioe){ 
	      ioe.printStackTrace(); 
	    }finally{ 
	       try{ 
	       if(bos != null){ 
	         bos.close(); 
	       } 
	       if(fos != null) { 
	         fos.close(); 
	       } 
	       if(is != null){ 
	         is.close(); 
	       } 
	       if(zipFile != null){ 
	         zipFile.close(); 
	       } 
	       }catch(Exception e) { 
	         e.printStackTrace(); 
	       } 
	     } 
	    //��ý�ѹ�ļ���·��
	   // String f = "D:/Temp/15580650806622018����������";
	    //String f = "D:/Temp/15580650806622018����������";
	    File file2 = new File(fpath);
	    String[] test=file2.list();
	    for(int i=0;i<test.length;i++){
	      System.out.println(test[i]);
	    }
	    System.out.println("------------------");
	    String fileName = "";
	    File[] tempList = file2.listFiles();
	    List<String> list = new ArrayList<String>();
	    for (int i = 0; i < tempList.length; i++) {
	      if (tempList[i].isFile()) {
	    	fileName = tempList[i].getAbsolutePath();
	    	list.add(fileName);
	       // System.out.println("��   ����"+fileName);
	      }
	      if (tempList[i].isDirectory()) {
	    	fileName = tempList[i].getAbsolutePath();
	    	list.add(fileName);
	       // System.out.println("�ļ��У�"+fileName);
	      }
	    }
		return list;
	   } 


	public Map<String, String> job_init(Map paraMap){
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
		//��ȡҳ���ѯ����
		String userName = request.getParameter("userName");
		if(null == userName){
			userName = "";
		}
		String courseId = getParameters(request,"courseId");
		if(null == courseId){
			courseId = "";
		}
		String classId = request.getParameter("classId");
		if(null == classId){
			classId = "";
		}
		conditionMap.put("userName",userName);
		conditionMap.put("courseId",courseId);
		conditionMap.put("classId",classId);
		return conditionMap;
	}

}
