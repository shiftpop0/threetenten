package cnedu.ncist.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import org.apache.poi.hssf.model.Sheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Row;

//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	
	private String filePath;
	
	public ExcelUtil()
	{
		filePath = "";
	}
	
	public String getFilePath()
	{
		return filePath;
	}
	
	public void setFilePath(String filePath)
	{
		this.filePath = filePath;
	}

	public String getRealPath(HttpServletRequest request, String serverPath)
	{
		String realPath = "";
		
		realPath = (String)request.getAttribute("REALPATH");
		//String serverPath = "\\uploadfile\\meetingRecord\\";
		String tempPath = serverPath + "temp\\";
		
		realPath = realPath + tempPath;
		
		return realPath;
	}
	
	public String uploadFile(HttpServletRequest request)
	{

        int MAXLEN = 1024 * 1024;
		
		String fullFileName = "";
		
		String realPath = "D:\\Temp\\";
		
        File fileDir = new File(realPath);  
		if(fileDir.isDirectory() && fileDir.exists()==false){  
            fileDir.mkdir();  
        }  
		
        // 实例化一个硬盘文件工厂,用来配置上传组件ServletFileUpload     
        DiskFileItemFactory factory =  new DiskFileItemFactory();  
          
        //设置文件存放的临时文件夹，这个文件夹要真实存在  
        factory.setRepository(new File(realPath));  
		
        //设置最大占用的内存  
        factory.setSizeThreshold(MAXLEN);  
          
        //创建ServletFileUpload对象  
        ServletFileUpload sfu = new ServletFileUpload(factory);  
        sfu.setHeaderEncoding("UTF-8");  
          
        //设置单个文件最大值byte   
        sfu.setFileSizeMax(MAXLEN * 1024);  
          
        //所有上传文件的总和最大值byte     
        sfu.setSizeMax(MAXLEN * 1024);  
          
        List<FileItem> items =  null;  
        
        try {  
            items = sfu.parseRequest(request);  
        }catch (SizeLimitExceededException e) {     
            System.out.println("文件大小超过了最大值");     
        } catch(FileUploadException e) {     
            e.printStackTrace();     
        }   
          
        
        //取得items的迭代器  
        Iterator<FileItem> iter = null;
        if(items !=null)
        {
        	iter = items.iterator();  
        }
        
        //迭代items，目前系统只提供一个文件上传 
        while(iter!=null && iter.hasNext()){  
            FileItem item = (FileItem) iter.next();
            
            if (!item.isFormField()) {
            
            	//这里是上传文件的表单域
                String fileName = item.getName();
				int pos = item.getName().lastIndexOf("\\");
                if(pos >= 0){
                	fileName = item.getName().substring(pos);
                }

                if((null != fileName) && (!"".equals(fileName))){
	                
    				pos = item.getName().lastIndexOf(".");
                    if(pos >= 0){
                    	fileName = item.getName().substring(pos);
                  	
                    }
                    else{
                    	fileName = "";
                    }
    				
    				String uuid = UUID.randomUUID().toString();  
	                fileName = uuid + fileName;
	                
	                fullFileName = realPath + fileName;
	                
	                BufferedInputStream in = null;
	                BufferedOutputStream out = null;
                	
	                try{
		                in = new BufferedInputStream(item.getInputStream());  
		                //文件存储在D:/upload/images目录下,这个目录也得存在   
		                out = new BufferedOutputStream(     
		                        new FileOutputStream(new File(fullFileName)));   
		                Streams.copy(in, out, true);
	                	
		                in.close();
	                	out.close();
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
              
		return fullFileName;
	}
	
	
	public List<Map<String, String>> loadExcel(String filePath) {
 
		List<Map<String, String>> excelGroups = null;
		String errorMsg = "";
		
		InputStream is = null;
		try {
			HSSFWorkbook workBook = null;
			try {
				is = new FileInputStream(filePath);
				if (filePath.endsWith(".xls")) { // 97-03
					workBook = new HSSFWorkbook(is);
//				} else if (filePath.endsWith(".xlsx")) { // 2007
					//workBook = new XSSFWorkbook(is);
				}
				else {
					System.out.println("不支持的文件类型！");
					return null;
				}
			} catch (Exception e) {
				System.out.println("解析Excel文件出错！");
				e.printStackTrace();
			} finally {
				is.close();
			}
			int sheets = 0;
			if(null != workBook)
			{
				sheets = workBook.getNumberOfSheets();
			}
			
			excelGroups = new ArrayList<Map<String, String>>();
			
			HSSFSheet sheet = workBook.getSheetAt(0); // 读取第一个sheet
			int rows = sheet.getPhysicalNumberOfRows(); // 获得行数
			if (rows > 1) { // 第一行默认为标题
				
				HSSFRow row = sheet.getRow(0);
				int cells = row.getLastCellNum();// 获得列数
				
				String [] columnName = new String[cells];
				for(int k=0; k< cells; k++)
				{
					HSSFCell cell = row.getCell((short) k);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					columnName[k] = cell.getStringCellValue();
				}
				
				for (int j = 1; j < rows; j++) {
					row = (HSSFRow) sheet.getRow(j);
					Map<String, String> groupObj = new HashMap<String, String>();
					cells = row.getLastCellNum();// 获得列数
					if (cells > 0) {
						for (int k = 0; k < cells; k++) {
							HSSFCell cell = row.getCell((short) k);
							// 全部置成String类型的单元格
							String cellValue = "";
							if(null != cell){
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								cellValue = cell.getStringCellValue();
							}
							groupObj.put(columnName[k], cellValue);
						}
					} else {
						errorMsg = "EXCEL没有数据，请确定。";
					}
					excelGroups.add(groupObj);
				}
			} else {
				errorMsg = "EXCEL没有数据，请确定。";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (errorMsg.length() > 0) {
			System.out.println("错误消息：" + errorMsg);
		}
		return excelGroups;
	}

}
