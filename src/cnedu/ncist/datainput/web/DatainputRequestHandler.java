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
		
		//System.out.println(majorList.get(0).get("身份证号"));
		
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

		//获得FileItem： 封住了每个请求体当中内容： 
		
		//获得工厂： 
		DiskFileItemFactory f= new DiskFileItemFactory(); 
		
		//获得解析器： 
		ServletFileUpload sfu = new ServletFileUpload(f); 
	   System.out.println("==============================");
		//解析request : 
		try {
			System.out.println("==============================");
			List<FileItem> items = (List)sfu.parseRequest(request);
			
			FileItem f1 = items.get(0);//普通字段： 对应FileItem：
			FileItem f2 = items.get(1);//文件上传： 对应的FileItem： 
			System.out.println("1111111"+f1);
			System.out.println("222222222"+f2);    
		
			DatainputService ds = new DatainputService();
			
			//普通字段获得
			/*
			 * FileItem： 这个类当中API方法： 
			 *  
			 *  isFormField();判断该组件是否是一个普通组件： 是一个普通组件： true： 
			 *               false： 表明是一个文件上传组件： 
			 *   
			 *  //普通字段好的属性和值： 
			 *  getFieldName();获得普通字段的属性名称： 
			 *  getString(); 获得属性名称对应的值：　
			 *  
			 *  //文件上传的组件： 
			 *  getName();获得文件的名称： 
			 *  getInputStream(); 返回的InputStream ： 流当中封装了图片的数据： 
			 *  getSize();获得图片字节个数：  
			 *  
			 *  
			 *            
			 */
			boolean flag1 = f1.isFormField();// true
			boolean flag2 = f2.isFormField();// false
			
//			System.out.println(flag1);
			System.out.println("标志位"+flag2);
			
		
			
			if(!flag2){//文件上传： 
				//文件的名称： 
				System.out.println(111);
				String name = f2.getName();//获得文件的名称; 
				//获得文件的大小： 
				long size = f2.getSize();
				
				//文件的具体的内容： 一定是一个流： 
				InputStream in= f2.getInputStream();//in 就读取了请求体当中的数据： 
				
				System.out.println("文件的名字"+name);
				System.out.println(size);
				System.out.println(in.available());// 获得剩下的字节个数： 
				//找到位置讲数据进行存放：  
				
				String path="D:/Temp"+"/"+new Date().getTime()+name; 
				System.out.println("------------------------"+path);
				
				//使用流对接： 
				IOUtils.copy(in, new FileOutputStream(new File(path))); 
				in.close();
				System.gc(); //对资源进行回收 否则文件被占用无法解压
				
				
				
				//对zip文件进行解压
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
	  * 解压Zip文件
	  * 返回解压后文件里所有文件和文件夹的绝对路径的集合 
	  * @param path 文件目录 
	  */
	 public static List<String> unZip(String path){ 
	    int count = -1; 
	    String savepath = ""; 
	    File file = null; 
	    InputStream is = null; 
	    FileOutputStream fos = null; 
	    BufferedOutputStream bos = null; 
	    savepath = path.substring(0, path.lastIndexOf(".")) + File.separator; //保存解压文件目录 
	    String fpath = savepath.substring(0, savepath.length()-1);
	    System.out.println("保存解压文件目录:"+fpath);  //D:/Temp/15580650806622018年招生数据
	    new File(savepath).mkdir(); //创建保存目录 
	    ZipFile zipFile = null; 
	    try{ 
	      zipFile = new ZipFile(path,"gbk"); //解决中文乱码问题 
	      Enumeration<?> entries = zipFile.getEntries(); 
	      while(entries.hasMoreElements()){ 
	        byte buf[] = new byte[buffer]; 
	        ZipEntry entry = (ZipEntry)entries.nextElement(); 
	        String filename = entry.getName(); 
	        boolean ismkdir = false; 
	        if(filename.lastIndexOf("/") != -1){ //检查此文件是否带有文件夹 
	         ismkdir = true; 
	        } 
	        filename = savepath + filename; 
	        if(entry.isDirectory()){ //如果是文件夹先创建 
	         file = new File(filename); 
	         file.mkdirs(); 
	          continue; 
	        } 
	        file = new File(filename); 
	        if(!file.exists()){ //如果是目录先创建 
	         if(ismkdir){ 
	         new File(filename.substring(0, filename.lastIndexOf("/"))).mkdirs(); //目录先创建 
	         } 
	        } 
	        file.createNewFile(); //创建文件 
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
	    //获得解压文件的路径
	   // String f = "D:/Temp/15580650806622018年招生数据";
	    //String f = "D:/Temp/15580650806622018年招生数据";
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
	       // System.out.println("文   件："+fileName);
	      }
	      if (tempList[i].isDirectory()) {
	    	fileName = tempList[i].getAbsolutePath();
	    	list.add(fileName);
	       // System.out.println("文件夹："+fileName);
	      }
	    }
		return list;
	   } 


	public Map<String, String> job_init(Map paraMap){
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
		//获取页面查询参数
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
