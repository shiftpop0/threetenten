package cnedu.ncist.util;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;

import org.apache.poi2.hssf.record.cf.CellRange;
import org.apache.poi2.hssf.usermodel.HSSFCell;
import org.apache.poi2.hssf.usermodel.HSSFCellStyle;
import org.apache.poi2.hssf.usermodel.HSSFDataFormat;
import org.apache.poi2.hssf.usermodel.HSSFFont;
import org.apache.poi2.hssf.usermodel.HSSFRichTextString;
import org.apache.poi2.hssf.usermodel.HSSFRow;
import org.apache.poi2.hssf.usermodel.HSSFSheet;
import org.apache.poi2.hssf.usermodel.HSSFWorkbook;
import org.apache.poi2.hssf.util.Region;
import  org.apache.poi2.hssf.*;

/**
 * Servlet implementation class ExcelExpert
 */
 
public class ExcelExport {
	/** 
     * * 
     *  
     * @param filename 保存到客户端的文件名           例：用户.xls 
     * @param title 标题行                      例：String[]{"名称","地址"} 
     * @param key 从查询结果List取得的MAP的KEY顺序， 
     *              需要和title顺序匹配，               例：String[]{"name","address"} 
     * @param values  结果集 
     * @param httpServletResponse 
     * @throws IOException 
     */ 
	//部门导出表格6
    public static void createDept(String filename, String[] title, String[] key, List<Map> values, HttpServletResponse httpServletResponse) throws IOException {  
        String filename2 = new String(filename.getBytes(), "iso-8859-1");  
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();  
        HSSFWorkbook workbook = null;  
        httpServletResponse.setHeader("Content-disposition", "attachment; filename=" + filename2);  
        httpServletResponse.setContentType("application/x-download");  
        workbook = new HSSFWorkbook();  
        HSSFSheet sheet = workbook.createSheet();  
        
        //Changed by NCIST WangYangting 20150611
        //增加Excel表格的格式设置--各列的宽度,共计6列

        sheet.setColumnWidth((short)0, (short)3500);
        sheet.setColumnWidth((short)1, (short)4000);
        sheet.setColumnWidth((short)2, (short)3500);
        sheet.setColumnWidth((short)3, (short)3500);
 
      //当前时间
		Date now = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String date = df.format(now);
        
        //设置标题字体
        HSSFFont titlefont = workbook.createFont();   
        titlefont.setFontName("黑体");   
        titlefont.setFontHeightInPoints((short) 14);// 字体大小   
        titlefont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗 
 
        // 标题样式   
        HSSFCellStyle titlestyle = workbook.createCellStyle();   
        titlestyle.setFont(titlefont);   
        titlestyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中   
        titlestyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
        titlestyle.setLocked(true);   
        titlestyle.setWrapText(true);// 自动换行         
       /* titlestyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        titlestyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        titlestyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        titlestyle.setBorderRight(HSSFCellStyle.BORDER_THIN);*/
/*        //时间样式
        HSSFCellStyle datestyle = workbook.createCellStyle();   
        datestyle.setFont(titlefont);   
        datestyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);// 左右居右   
        datestyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
        datestyle.setLocked(true); */
        
      //标题  
         HSSFRow row = null;  
         HSSFCell cell = null;  
         row = sheet.createRow((short) 0);
         row.setHeight((short) 1200);
         cell = row.createCell((short)0);
         cell.setCellValue(new HSSFRichTextString("学校信息一览表"));
         //起始行、起始列
         sheet.addMergedRegion(new Region((short)0, (short)0, (short)0, (short)5)); 
         cell.setCellStyle(titlestyle);
         
         //时间
  /*       cell = row.createCell((short)1);
         cell.setCellValue(new HSSFRichTextString(date));
         //起始行、起始列
         sheet.addMergedRegion(new Region((short)0, (short)0, (short)0, (short)5)); 
         cell.setCellStyle(datestyle);*/
         
        
        //设置表头字体
        HSSFFont headfont = workbook.createFont();   
        headfont.setFontName("黑体");   
        headfont.setFontHeightInPoints((short) 10);// 字体大小   
        headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗   
        
        // 表头样式   
        HSSFCellStyle headstyle = workbook.createCellStyle();   
         headstyle.setFont(headfont);   
         headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中   
         headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
         headstyle.setLocked(true);   
         headstyle.setWrapText(true);// 自动换行
         //设置边框
         headstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
         headstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
         headstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
         headstyle.setBorderRight(HSSFCellStyle.BORDER_THIN); 
        
        //设置单元格的字体
        HSSFFont font = workbook.createFont();   
        font.setFontName("宋体");   
        font.setFontHeightInPoints((short) 10);   

        // 设置单元格样式   
        HSSFCellStyle style = workbook.createCellStyle();   
        style.setFont(font);   
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中   
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
        style.setWrapText(true);   
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);

        //Changed ended

        HSSFCellStyle textStyle = workbook.createCellStyle();
        HSSFDataFormat format = workbook.createDataFormat();
        textStyle.setDataFormat(format.getFormat("@"));
        

        
        row = sheet.createRow((short) 1);
        row.setHeight((short) 800);
        for (int i = 0; title != null && i < title.length; i++) {  
             cell = row.createCell((short) i);  
             cell.setCellStyle(headstyle);
             cell.setCellType(HSSFCell.CELL_TYPE_STRING);  
             cell.setCellValue(new HSSFRichTextString(title[i]));
        } 
        

        Map map = null; 
        int j =  values.size();
        
        for (int i = 0; values != null && i < j; i++) {  
            row = sheet.createRow((short) (i + 2));  //row 控制表头所在行  
            row.setHeight((short) 500);
            map = values.get(i);  
            for (int i2 = 0; i2 < key.length; i2++) {  
                cell = row.createCell((short) (i2));  
                cell.setCellStyle(style);//设置单元格格式
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);  
                if (map.get(key[i2]) == null) {  
                    cell.setCellValue(new HSSFRichTextString(""));  
                } else {  
                    cell.setCellValue(new HSSFRichTextString(map.get(key[i2]).toString()));  
                }  
            }  
        }
        
        
        workbook.write(servletOutputStream);  
  
        servletOutputStream.flush();  
        servletOutputStream.close();  
 
        httpServletResponse.setContentType("text/html");  
        
     
    }  
  //人员导出表格9
    public static void createEmploy(String filename, String[] title, String[] key, List<Map> values, HttpServletResponse httpServletResponse) throws IOException {  
        String filename2 = new String(filename.getBytes(), "iso-8859-1");  
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();  
        HSSFWorkbook workbook = null;  
        httpServletResponse.setHeader("Content-disposition", "attachment; filename=" + filename2);  
        httpServletResponse.setContentType("application/x-download");  
        workbook = new HSSFWorkbook();  
        HSSFSheet sheet = workbook.createSheet();  
        
        //Changed by NCIST WangYangting 20150611
        //增加Excel表格的格式设置--各列的宽度,共计6列

        sheet.setColumnWidth((short)0, (short)4000);
        sheet.setColumnWidth((short)1, (short)3000);
        sheet.setColumnWidth((short)2, (short)3000);
        sheet.setColumnWidth((short)3, (short)3500);
        sheet.setColumnWidth((short)4, (short)3000);
        sheet.setColumnWidth((short)5, (short)4000);
        sheet.setColumnWidth((short)6, (short)4000);
        sheet.setColumnWidth((short)7, (short)4000);
        sheet.setColumnWidth((short)8, (short)4000);
        sheet.setColumnWidth((short)9, (short)4000);
        sheet.setColumnWidth((short)10, (short)3000);
        sheet.setColumnWidth((short)11, (short)3000);
        sheet.setColumnWidth((short)12, (short)3500);
        sheet.setColumnWidth((short)13, (short)3000);
        sheet.setColumnWidth((short)14, (short)4000);
        sheet.setColumnWidth((short)15, (short)4000);
        sheet.setColumnWidth((short)16, (short)4000);
        sheet.setColumnWidth((short)17, (short)4000);
        sheet.setColumnWidth((short)18, (short)4000);
        sheet.setColumnWidth((short)19, (short)3000);
        sheet.setColumnWidth((short)20, (short)3000);
        sheet.setColumnWidth((short)21, (short)3500);
        sheet.setColumnWidth((short)22, (short)3000);
        sheet.setColumnWidth((short)23, (short)4000);
        sheet.setColumnWidth((short)24, (short)4000);
         
      
        
        //设置标题字体
        HSSFFont titlefont = workbook.createFont();   
        titlefont.setFontName("黑体");   
        titlefont.setFontHeightInPoints((short) 14);// 字体大小   
        titlefont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗   
        
        // 标题样式   
       HSSFCellStyle titlestyle = workbook.createCellStyle();   
        titlestyle.setFont(titlefont);   
       titlestyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中   
       titlestyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
       titlestyle.setLocked(true);   
       titlestyle.setWrapText(true);// 自动换行 
       
     //标题  
       HSSFRow row = null;  
       HSSFCell cell = null;  
       row = sheet.createRow((short) 0);
       row.setHeight((short) 1200);
       cell = row.createCell((short)0);
      // cell.setCellValue(new HSSFRichTextString("人员信息一览表"));
       //起始行、起始列
       sheet.addMergedRegion(new Region((short)0, (short)0, (short)0, (short)8)); 
       cell.setCellStyle(titlestyle);
       
       //设置表头字体
       HSSFFont headfont = workbook.createFont();   
       headfont.setFontName("黑体");   
       headfont.setFontHeightInPoints((short) 10);// 字体大小   
       headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗   
       
       // 表头样式   
       HSSFCellStyle headstyle = workbook.createCellStyle();   
        headstyle.setFont(headfont);   
        headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中   
        headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
        headstyle.setLocked(true);   
        headstyle.setWrapText(true);// 自动换行
        //设置边框
        headstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        headstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        headstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        headstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        //设置单元格的字体
        HSSFFont font = workbook.createFont();   
        font.setFontName("宋体");   
        font.setFontHeightInPoints((short) 10);   

        // 设置单元格样式   
        HSSFCellStyle style = workbook.createCellStyle();   
        style.setFont(font);   
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中   
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
        style.setWrapText(true);   
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);

        //Changed ended

        HSSFCellStyle textStyle = workbook.createCellStyle();
        HSSFDataFormat format = workbook.createDataFormat();
        textStyle.setDataFormat(format.getFormat("@"));
 
        
        row = sheet.createRow((short) 1);
        row.setHeight((short) 800);
        for (int i = 0; title != null && i < title.length; i++) {  
             cell = row.createCell((short) i);  
             cell.setCellStyle(headstyle);
             cell.setCellType(HSSFCell.CELL_TYPE_STRING);  
             cell.setCellValue(new HSSFRichTextString(title[i]));
        } 
        
        
        Map map = null;  
        for (int i = 0; values != null && i < values.size(); i++) {  
        	 row = sheet.createRow((short) (i + 2));  
            row.setHeight((short) 500);
            map = values.get(i);  
            for (int i2 = 0; i2 < key.length; i2++) {  
                cell = row.createCell((short) (i2));  
                cell.setCellStyle(style);//设置单元格格式
                if (map.get(key[i2]) == null) {  
                    cell.setCellValue(new HSSFRichTextString(""));  
                } else {  
                    cell.setCellValue(new HSSFRichTextString(map.get(key[i2]).toString()));  
                }  
            }  
        }
        
        
        workbook.write(servletOutputStream);  
        servletOutputStream.flush();  
        servletOutputStream.close();  
 
        
    }  
   
    
    //专家导出表格
    public static void createExpert(String filename, String[] title, String[] key, List<Map> values, HttpServletResponse httpServletResponse) throws IOException {  
        String filename2 = new String(filename.getBytes(), "iso-8859-1");  
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();  
        HSSFWorkbook workbook = null;  
        httpServletResponse.setHeader("Content-disposition", "attachment; filename=" + filename2);  
        httpServletResponse.setContentType("application/x-download");  
        workbook = new HSSFWorkbook();  
        HSSFSheet sheet = workbook.createSheet();  
        
        //Changed by NCIST WangYangting 20150611
        //增加Excel表格的格式设置--各列的宽度,共计6列

        sheet.setColumnWidth((short)0, (short)4000);
        sheet.setColumnWidth((short)1, (short)3000);
        sheet.setColumnWidth((short)2, (short)5000);
        sheet.setColumnWidth((short)3, (short)3500);
        sheet.setColumnWidth((short)4, (short)3000);
        sheet.setColumnWidth((short)5, (short)4000);
        sheet.setColumnWidth((short)6, (short)4000);
 
        //设置标题字体
        HSSFFont titlefont = workbook.createFont();   
        titlefont.setFontName("黑体");   
        titlefont.setFontHeightInPoints((short) 14);// 字体大小   
        titlefont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗   
        
        //标题样式   
       HSSFCellStyle titlestyle = workbook.createCellStyle();   
        titlestyle.setFont(titlefont);   
       titlestyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中   
       titlestyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
     //标题  
       HSSFRow row = null;  
       HSSFCell cell = null;  
       row = sheet.createRow((short) 0);
       row.setHeight((short) 1200);
       cell = row.createCell((short)0);
       cell.setCellValue(new HSSFRichTextString("录取志愿率情况表"));
       //起始行、起始列
       sheet.addMergedRegion(new Region((short)0, (short)0, (short)0, (short)5)); 
       cell.setCellStyle(titlestyle);
       
       //设置表头字体
       HSSFFont headfont = workbook.createFont();   
       headfont.setFontName("黑体");   
       headfont.setFontHeightInPoints((short) 10);// 字体大小   
       headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗   
       
       // 表头样式   
       HSSFCellStyle headstyle = workbook.createCellStyle();   
        headstyle.setFont(headfont);   
        headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中   
        headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
        headstyle.setLocked(true);   
        headstyle.setWrapText(true);// 自动换行
        //设置边框
        headstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        headstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        headstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        headstyle.setBorderRight(HSSFCellStyle.BORDER_THIN); 
        
        
        //设置单元格的字体
        HSSFFont font = workbook.createFont();   
        font.setFontName("宋体");   
        font.setFontHeightInPoints((short) 10);   

        // 设置单元格样式   
        HSSFCellStyle style = workbook.createCellStyle();   
        style.setFont(font);   
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中   
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
        style.setWrapText(true);   
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);

        //Changed ended

        HSSFCellStyle textStyle = workbook.createCellStyle();
        HSSFDataFormat format = workbook.createDataFormat();
        textStyle.setDataFormat(format.getFormat("@"));
 
        
        
        row = sheet.createRow((short) 1);
        row.setHeight((short) 800);
        for (int i = 0; title != null && i < title.length; i++) {  
             cell = row.createCell((short) i);  
             cell.setCellStyle(headstyle);
             cell.setCellType(HSSFCell.CELL_TYPE_STRING);  
             cell.setCellValue(new HSSFRichTextString(title[i]));
        } 
        
        Map map = null;  
        for (int i = 0; values != null && i < values.size(); i++) {  
        	 row = sheet.createRow((short) (i + 2));  
            row.setHeight((short) 500);
            map = values.get(i);  
            for (int i2 = 0; i2 < key.length; i2++) {  
                cell = row.createCell((short) (i2));  
                cell.setCellStyle(style);//设置单元格格式
                if (map.get(key[i2]) == null) {  
                    cell.setCellValue(new HSSFRichTextString(""));  
                } else {  
                    cell.setCellValue(new HSSFRichTextString(map.get(key[i2]).toString()));  
                }  
            }  
        }
        
        
        workbook.write(servletOutputStream);  
        servletOutputStream.flush();  
        servletOutputStream.close();  
        httpServletResponse.setContentType("text/html");  
        
    }  
    
    
    //复盘导出表格4
    public static void resAnanlyse(String filename, String[] title, String[] key, List<Map> values, HttpServletResponse httpServletResponse) throws IOException {  
        String filename2 = new String(filename.getBytes(), "iso-8859-1");  
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();  
        HSSFWorkbook workbook = null;  
        httpServletResponse.setHeader("Content-disposition", "attachment; filename=" + filename2);  
        httpServletResponse.setContentType("application/x-download");  
        workbook = new HSSFWorkbook();  
        HSSFSheet sheet = workbook.createSheet();  
        
        //Changed by NCIST WangYangting 20150611
        //增加Excel表格的格式设置--各列的宽度,共计6列

        sheet.setColumnWidth((short)0, (short)5000);
        sheet.setColumnWidth((short)1, (short)5000);
        sheet.setColumnWidth((short)2, (short)5000);
        sheet.setColumnWidth((short)3, (short)5000);
        sheet.setColumnWidth((short)4, (short)5000);
        sheet.setColumnWidth((short)5, (short)5000);
        sheet.setColumnWidth((short)6, (short)5000);
        sheet.setColumnWidth((short)7, (short)5000);
        sheet.setColumnWidth((short)8, (short)5000);
        
 
        //设置标题字体
        HSSFFont titlefont = workbook.createFont();   
        titlefont.setFontName("黑体");   
        titlefont.setFontHeightInPoints((short) 14);// 字体大小   
        titlefont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗   
        
        // 标题样式   
       HSSFCellStyle titlestyle = workbook.createCellStyle();   
       titlestyle.setFont(titlefont);   
       titlestyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中   
       titlestyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
       
       //标题  
          HSSFRow row = null;  
          HSSFCell cell = null;  
          row = sheet.createRow((short) 0);
          row.setHeight((short) 1200);
          cell = row.createCell((short)0);
          cell.setCellValue(new HSSFRichTextString("调剂率高于20%专业录取报到情况表"));
          //起始行、起始列
          sheet.addMergedRegion(new Region((short)0, (short)0, (short)0, (short)3)); 
          cell.setCellStyle(titlestyle);
          
          //设置表头字体
          HSSFFont headfont = workbook.createFont();   
          headfont.setFontName("黑体");   
          headfont.setFontHeightInPoints((short) 10);// 字体大小   
          headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗   
          
          // 表头样式   
          HSSFCellStyle headstyle = workbook.createCellStyle();   
           headstyle.setFont(headfont);   
           headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中   
           headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
           headstyle.setLocked(true);   
           headstyle.setWrapText(true);// 自动换行
           //设置边框
           headstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
           headstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
           headstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
           headstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);     
        //设置单元格的字体
        HSSFFont font = workbook.createFont();   
        font.setFontName("宋体");   
        font.setFontHeightInPoints((short) 10);   

        // 设置单元格样式   
        HSSFCellStyle style = workbook.createCellStyle();   
        style.setFont(font);   
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中   
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
        style.setWrapText(true);   
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);

        //Changed ended

        HSSFCellStyle textStyle = workbook.createCellStyle();
        HSSFDataFormat format = workbook.createDataFormat();
        textStyle.setDataFormat(format.getFormat("@"));
 
   
        
        row = sheet.createRow((short) 1);
        row.setHeight((short) 800);
        for (int i = 0; title != null && i < title.length; i++) {  
             cell = row.createCell((short) i);  
             cell.setCellStyle(headstyle);
             cell.setCellType(HSSFCell.CELL_TYPE_STRING);  
             cell.setCellValue(new HSSFRichTextString(title[i]));
        } 
        
        //i 控制表头所在行
        Map map = null;  
        for (int i = 0; values != null && i < values.size(); i++) {  
        	 row = sheet.createRow((short) (i + 2));  
            row.setHeight((short) 500);
            map = values.get(i);  
            for (int i2 = 0; i2 < key.length; i2++) {  
                cell = row.createCell((short) (i2));  
                cell.setCellStyle(style);//设置单元格格式
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);  
                if (map.get(key[i2]) == null) {  
                    cell.setCellValue(new HSSFRichTextString(""));  
                } else {  
                    cell.setCellValue(new HSSFRichTextString(map.get(key[i2]).toString()));  
                }  
            }  
        }
        
        
        workbook.write(servletOutputStream);  
        servletOutputStream.flush();  
        servletOutputStream.close();  
        httpServletResponse.setContentType("text/html");  
        
    }  
   
    
  //演练计划导出表格6
    public static void createExercise(String filename, String[] title, String[] key, List<Map> values, HttpServletResponse httpServletResponse) throws IOException {  
    	  String filename2 = new String(filename.getBytes(), "iso-8859-1");  
          ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();  
          HSSFWorkbook workbook = null;  
          httpServletResponse.setHeader("Content-disposition", "attachment; filename=" + filename2);  
          httpServletResponse.setContentType("application/x-download");  
          workbook = new HSSFWorkbook();  
          HSSFSheet sheet = workbook.createSheet();  
          
          //Changed by NCIST WangYangting 20150611
          //增加Excel表格的格式设置--各列的宽度,共计6列

          sheet.setColumnWidth((short)0, (short)3500);
          sheet.setColumnWidth((short)1, (short)4000);
          sheet.setColumnWidth((short)2, (short)3500);
          sheet.setColumnWidth((short)3, (short)3500);
          sheet.setColumnWidth((short)4, (short)3500);
          sheet.setColumnWidth((short)5, (short)3500);
          sheet.setColumnWidth((short)6, (short)3500);
          sheet.setColumnWidth((short)7, (short)3500);
   
        //当前时间
  		Date now = new Date();
  		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
  		String date = df.format(now);
          
          //设置标题字体
          HSSFFont titlefont = workbook.createFont();   
          titlefont.setFontName("黑体");   
          titlefont.setFontHeightInPoints((short) 14);// 字体大小   
          titlefont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗 
   
          // 标题样式   
          HSSFCellStyle titlestyle = workbook.createCellStyle();   
          titlestyle.setFont(titlefont);   
          titlestyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中   
          titlestyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
          titlestyle.setLocked(true);   
          titlestyle.setWrapText(true);// 自动换行         
         /* titlestyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
          titlestyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
          titlestyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
          titlestyle.setBorderRight(HSSFCellStyle.BORDER_THIN);*/
  /*        //时间样式
          HSSFCellStyle datestyle = workbook.createCellStyle();   
          datestyle.setFont(titlefont);   
          datestyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);// 左右居右   
          datestyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
          datestyle.setLocked(true); */
          
        //标题  
           HSSFRow row = null;  
           HSSFCell cell = null;  
           row = sheet.createRow((short) 0);
           row.setHeight((short) 1200);
           cell = row.createCell((short)0);
           cell.setCellValue(new HSSFRichTextString("录取志愿率及报到情况表"));
           //起始行、起始列
           sheet.addMergedRegion(new Region((short)0, (short)0, (short)0, (short)5)); 
           cell.setCellStyle(titlestyle);
           
           //时间
    /*       cell = row.createCell((short)1);
           cell.setCellValue(new HSSFRichTextString(date));
           //起始行、起始列
           sheet.addMergedRegion(new Region((short)0, (short)0, (short)0, (short)5)); 
           cell.setCellStyle(datestyle);*/
           
          
          //设置表头字体
          HSSFFont headfont = workbook.createFont();   
          headfont.setFontName("黑体");   
          headfont.setFontHeightInPoints((short) 10);// 字体大小   
          headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗   
          
          // 表头样式   
          HSSFCellStyle headstyle = workbook.createCellStyle();   
           headstyle.setFont(headfont);   
           headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中   
           headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
           headstyle.setLocked(true);   
           headstyle.setWrapText(true);// 自动换行
           //设置边框
           headstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
           headstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
           headstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
           headstyle.setBorderRight(HSSFCellStyle.BORDER_THIN); 
          
          //设置单元格的字体
          HSSFFont font = workbook.createFont();   
          font.setFontName("宋体");   
          font.setFontHeightInPoints((short) 10);   

          // 设置单元格样式   
          HSSFCellStyle style = workbook.createCellStyle();   
          style.setFont(font);   
          style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中   
          style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
          style.setWrapText(true);   
          style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
          style.setBorderTop(HSSFCellStyle.BORDER_THIN);
          style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
          style.setBorderRight(HSSFCellStyle.BORDER_THIN);

          //Changed ended

          HSSFCellStyle textStyle = workbook.createCellStyle();
          HSSFDataFormat format = workbook.createDataFormat();
          textStyle.setDataFormat(format.getFormat("@"));
          

          
          row = sheet.createRow((short) 1);
          row.setHeight((short) 800);
          for (int i = 0; title != null && i < title.length; i++) {  
               cell = row.createCell((short) i);  
               cell.setCellStyle(headstyle);
               cell.setCellType(HSSFCell.CELL_TYPE_STRING);  
               cell.setCellValue(new HSSFRichTextString(title[i]));
          } 
          
         //i 控制表头所在行
          Map map = null;  
          for (int i = 0; values != null && i < values.size(); i++) {  
         	 row = sheet.createRow((short) (i + 2));  
              row.setHeight((short) 500);
              map = values.get(i);  
              for (int i2 = 0; i2 < key.length; i2++) {  
                  cell = row.createCell((short) (i2));  
                  cell.setCellStyle(style);//设置单元格格式
                  cell.setCellType(HSSFCell.CELL_TYPE_STRING);  
                  if (map.get(key[i2]) == null) {  
                      cell.setCellValue(new HSSFRichTextString(""));  
                  } else {  
                      cell.setCellValue(new HSSFRichTextString(map.get(key[i2]).toString()));  
                  }  
              }  
          }
          
          
          workbook.write(servletOutputStream);  
    
          servletOutputStream.flush();  
          servletOutputStream.close();  
   
          httpServletResponse.setContentType("text/html");  
          
       
      }  
  //培训计划导出表格6
    public static void createRestrain(String filename, String[] title, String[] key, List<Map> values, HttpServletResponse httpServletResponse) throws IOException {  
        String filename2 = new String(filename.getBytes(), "iso-8859-1");  
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();  
        HSSFWorkbook workbook = null;  
        httpServletResponse.setHeader("Content-disposition", "attachment; filename=" + filename2);  
        httpServletResponse.setContentType("application/x-download");  
        workbook = new HSSFWorkbook();  
        HSSFSheet sheet = workbook.createSheet();  
        
        //Changed by NCIST WangYangting 20150611
        //增加Excel表格的格式设置--各列的宽度,共计6列

        sheet.setColumnWidth((short)0, (short)5000);
        sheet.setColumnWidth((short)1, (short)3500);
        sheet.setColumnWidth((short)2, (short)3500);
        sheet.setColumnWidth((short)3, (short)3500);
        sheet.setColumnWidth((short)4, (short)3500);
        sheet.setColumnWidth((short)5, (short)3500);
      
      //设置标题字体
        HSSFFont titlefont = workbook.createFont();   
        titlefont.setFontName("黑体");   
        titlefont.setFontHeightInPoints((short) 14);// 字体大小   
        titlefont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗 
 
        // 标题样式   
        HSSFCellStyle titlestyle = workbook.createCellStyle();   
        titlestyle.setFont(titlefont);   
        titlestyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中   
        titlestyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
        titlestyle.setLocked(true);   
        titlestyle.setWrapText(true);// 自动换行   
        
        //标题  
           HSSFRow row = null;  
           HSSFCell cell = null;  
           row = sheet.createRow((short) 0);
           row.setHeight((short) 1200);
           cell = row.createCell((short)0);
           cell.setCellValue(new HSSFRichTextString("培训计划一览表"));
           //起始行、起始列
           sheet.addMergedRegion(new Region((short)0, (short)0, (short)0, (short)5)); 
           cell.setCellStyle(titlestyle);
   
        //titlestyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        //titlestyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //titlestyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        //titlestyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        
           //设置表头字体
           HSSFFont headfont = workbook.createFont();   
           headfont.setFontName("黑体");   
           headfont.setFontHeightInPoints((short) 10);// 字体大小   
           headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗   
           
           // 表头样式   
           HSSFCellStyle headstyle = workbook.createCellStyle();   
            headstyle.setFont(headfont);   
            headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中   
            headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
            headstyle.setLocked(true);   
            headstyle.setWrapText(true);// 自动换行
            //设置边框
            headstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            headstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            headstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            headstyle.setBorderRight(HSSFCellStyle.BORDER_THIN); 
            
        //设置单元格的字体
        HSSFFont font = workbook.createFont();   
        font.setFontName("宋体");   
        font.setFontHeightInPoints((short) 10);   

        // 设置单元格样式   
        HSSFCellStyle style = workbook.createCellStyle();   
        style.setFont(font);   
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中   
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
        style.setWrapText(true);   
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);

        //Changed ended

        HSSFCellStyle textStyle = workbook.createCellStyle();
        HSSFDataFormat format = workbook.createDataFormat();
        textStyle.setDataFormat(format.getFormat("@"));
        

     
   //     row = sheet.createRow((short) 0);
    //    row.setHeight((short) 1000);
    //    cell = row.createCell((short)0);
       // cell.setCellValue(new HSSFRichTextString("采购计划管理明细信息"));
       // sheet.addMergedRegion(new Region((short)0, (short)0, (short)0, (short)5)); 
//        cell.setCellStyle(titlestyle);
        
        row = sheet.createRow((short) 1);
        row.setHeight((short) 800);
        for (int i = 0; title != null && i < title.length; i++) {  
             cell = row.createCell((short) i);  
             cell.setCellStyle(headstyle);
             cell.setCellType(HSSFCell.CELL_TYPE_STRING);  
             cell.setCellValue(new HSSFRichTextString(title[i]));
        } 
        
        
        //i 控制表头所在行
        Map map = null;  
        for (int i = 0; values != null && i < values.size(); i++) {  
       	 row = sheet.createRow((short) (i + 2));  
            row.setHeight((short) 500);
            map = values.get(i);  
            for (int i2 = 0; i2 < key.length; i2++) {  
                cell = row.createCell((short) (i2));  
                cell.setCellStyle(style);//设置单元格格式
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);  
                if (map.get(key[i2]) == null) {  
                    cell.setCellValue(new HSSFRichTextString(""));  
                } else {  
                    cell.setCellValue(new HSSFRichTextString(map.get(key[i2]).toString()));  
                }  
            }  
        }
        
        workbook.write(servletOutputStream);  
  
        servletOutputStream.flush();  
        servletOutputStream.close();  
 
        httpServletResponse.setContentType("text/html");  
        
     
    }   
	/** 
     * * 
     *  
     * @param filename 保存到客户端的文件名           例：用户.xls 
     * @param title 标题行                      例：String[]{"名称","地址"} 
     * @param key 从查询结果List取得的MAP的KEY顺序， 
     *              需要和title顺序匹配，               例：String[]{"name","address"} 
     * @param values  结果集 
     * @param httpServletResponse 
     * @throws IOException 
     */  
    public static void createN(String filename, String[] title, String[] key, List<Map> values, HttpServletResponse httpServletResponse) throws IOException {  
        String filename2 = new String(filename.getBytes(), "iso-8859-1");  
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();  
        HSSFWorkbook workbook = null;  
        httpServletResponse.setHeader("Content-disposition", "attachment; filename=" + filename2);  
        httpServletResponse.setContentType("application/x-download");  
        workbook = new HSSFWorkbook();  
        HSSFSheet sheet = workbook.createSheet();  
        
        //Changed by NCIST WangYangting 20150611
        //增加Excel表格的格式设置--各列的宽度,共计6列

        sheet.setColumnWidth((short)0, (short)8000);
        sheet.setColumnWidth((short)1, (short)3200);
        sheet.setColumnWidth((short)2, (short)3200);
        sheet.setColumnWidth((short)3, (short)3500);
        sheet.setColumnWidth((short)4, (short)2600);
        sheet.setColumnWidth((short)5, (short)2600);
        
        //设置标题字体
        HSSFFont titlefont = workbook.createFont();   
        titlefont.setFontName("宋体");   
        titlefont.setFontHeightInPoints((short) 14);// 字体大小   
        titlefont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗   
        
        // 表头样式   
//        HSSFCellStyle titlestyle = workbook.createCellStyle();   
//        titlestyle.setFont(titlefont);   
//        titlestyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中   
//        titlestyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
//        titlestyle.setLocked(true);   
//        titlestyle.setWrapText(true);// 自动换行         
        //titlestyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        //titlestyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //titlestyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        //titlestyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        
        //设置表头字体
//        HSSFFont headfont = workbook.createFont();   
//        headfont.setFontName("宋体");   
//        headfont.setFontHeightInPoints((short) 10);// 字体大小   
//        headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗   
        
        // 表头样式   
//        HSSFCellStyle headstyle = workbook.createCellStyle();   
//        headstyle.setFont(headfont);   
//        headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中   
//        headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
//        headstyle.setLocked(true);   
//        headstyle.setWrapText(true);// 自动换行         
//        headstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        headstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        headstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        headstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        
        //设置单元格的字体
        HSSFFont font = workbook.createFont();   
        font.setFontName("宋体");   
        font.setFontHeightInPoints((short) 10);   

        // 设置单元格样式   
        HSSFCellStyle style = workbook.createCellStyle();   
        style.setFont(font);   
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中   
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
        style.setWrapText(true);   
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);

        //Changed ended

        HSSFCellStyle textStyle = workbook.createCellStyle();
        HSSFDataFormat format = workbook.createDataFormat();
        textStyle.setDataFormat(format.getFormat("@"));
        

        HSSFRow row = null;  
        HSSFCell cell = null;  
   //     row = sheet.createRow((short) 0);
    //    row.setHeight((short) 1000);
    //    cell = row.createCell((short)0);
       // cell.setCellValue(new HSSFRichTextString("采购计划管理明细信息"));
       // sheet.addMergedRegion(new Region((short)0, (short)0, (short)0, (short)5)); 
//        cell.setCellStyle(titlestyle);
        
        row = sheet.createRow((short) 0);
        row.setHeight((short) 800);
        for (int i = 0; title != null && i < title.length; i++) {  
            cell = row.createCell((short) i);  
//            cell.setCellStyle(headstyle);
//            cell.setCellType(HSSFCell.CELL_TYPE_STRING);  
            cell.setCellValue(new HSSFRichTextString(title[i]));
        } 
        
        Map map = null;  
        for (int i = 0; values != null && i < values.size(); i++) {  
            row = sheet.createRow((short) (i + 1));  
            row.setHeight((short) 500);
            map = values.get(i);  
            for (int i2 = 0; i2 < key.length; i2++) {  
                cell = row.createCell((short) (i2));  
                cell.setCellStyle(style);//设置单元格格式

//                cell.setCellType(HSSFCell.CELL_TYPE_STRING);  
                if (map.get(key[i2]) == null) {  
                    cell.setCellValue(new HSSFRichTextString(""));  
                } else {  
                    cell.setCellValue(new HSSFRichTextString(map.get(key[i2]).toString()));  
                }  
            }  
        }
        
        
        workbook.write(servletOutputStream);  
        servletOutputStream.flush();  
        servletOutputStream.close();  
        
        
    }  
    
    
    /** 
     * * 
     *  
     * @param filename 保存到客户端的文件名           例：用户.xls 
     * @param title 标题行                      例：String[]{"名称","地址"} 
     * @param key 从查询结果List取得的MAP的KEY顺序， 
     *              需要和title顺序匹配，               例：String[]{"name","address"} 
     * @param values  结果集 
     * @param httpServletResponse 
     * @throws IOException 
     */  
    public static void createM(String filename, String[] title, String[] key, List<Map> values, HttpServletResponse httpServletResponse) throws IOException {  
        String filename2 = new String(filename.getBytes(), "iso-8859-1");  
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();  
        HSSFWorkbook workbook = null;  
        httpServletResponse.setHeader("Content-disposition", "attachment; filename=" + filename2);  
        httpServletResponse.setContentType("application/x-download");  
        workbook = new HSSFWorkbook();  
        HSSFSheet sheet = workbook.createSheet();  
        
        //Changed by NCIST WangYangting 20150611
        //增加Excel表格的格式设置--各列的宽度,共计6列


        sheet.setColumnWidth((short)0, (short)8000);
        sheet.setColumnWidth((short)1, (short)3200);
        sheet.setColumnWidth((short)2, (short)3200);
        sheet.setColumnWidth((short)3, (short)3500);
        sheet.setColumnWidth((short)4, (short)2600);
        sheet.setColumnWidth((short)5, (short)2600);
        
        //设置标题字体
        HSSFFont titlefont = workbook.createFont();   
        titlefont.setFontName("宋体");   
        titlefont.setFontHeightInPoints((short) 14);// 字体大小   
        titlefont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗   
        
        // 表头样式   
        HSSFCellStyle titlestyle = workbook.createCellStyle();   
        titlestyle.setFont(titlefont);   
        titlestyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中   
        titlestyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
        titlestyle.setLocked(true);   
        titlestyle.setWrapText(true);// 自动换行         
        //titlestyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        //titlestyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //titlestyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        //titlestyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        
        //设置表头字体
        HSSFFont headfont = workbook.createFont();   
        headfont.setFontName("宋体");   
        headfont.setFontHeightInPoints((short) 10);// 字体大小   
        headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗   
        
        // 表头样式   
        HSSFCellStyle headstyle = workbook.createCellStyle();   
        headstyle.setFont(headfont);   
        headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中   
        headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
        headstyle.setLocked(true);   
        headstyle.setWrapText(true);// 自动换行         
        headstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        headstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        headstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        headstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        
        //设置单元格的字体
        HSSFFont font = workbook.createFont();   
        font.setFontName("宋体");   
        font.setFontHeightInPoints((short) 10);   

        // 设置单元格样式   
        HSSFCellStyle style = workbook.createCellStyle();   
        style.setFont(font);   
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中   
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
        style.setWrapText(true);   
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);

        //Changed ended

        HSSFCellStyle textStyle = workbook.createCellStyle();
        HSSFDataFormat format = workbook.createDataFormat();
        textStyle.setDataFormat(format.getFormat("@"));
        

        HSSFRow row = null;  
        HSSFCell cell = null;  
        row = sheet.createRow((short) 0);
        row.setHeight((short) 1000);
        cell = row.createCell((short)0);
        cell.setCellValue(new HSSFRichTextString("采购计划管理明细信息"));
        sheet.addMergedRegion(new Region((short)0, (short)0, (short)0, (short)5)); 
        cell.setCellStyle(titlestyle);
        
        row = sheet.createRow((short) 1);
        row.setHeight((short) 800);
        for (int i = 0; title != null && i < title.length; i++) {  
            cell = row.createCell((short) i);  
            cell.setCellStyle(headstyle);
//            cell.setCellType(HSSFCell.CELL_TYPE_STRING);  
            cell.setCellValue(new HSSFRichTextString(title[i]));
        } 
        
        Map map = null;  
        for (int i = 0; values != null && i < values.size(); i++) {  
            row = sheet.createRow((short) (i + 2));  
            row.setHeight((short) 500);
            map = values.get(i);  
            for (int i2 = 0; i2 < key.length; i2++) {  
                cell = row.createCell((short) (i2));  
                cell.setCellStyle(style);//设置单元格格式


//                cell.setCellType(HSSFCell.CELL_TYPE_STRING);  
                if (map.get(key[i2]) == null) {  
                    cell.setCellValue(new HSSFRichTextString(""));  
                } else {  
                    cell.setCellValue(new HSSFRichTextString(map.get(key[i2]).toString()));  
                }  
            }  
        }
        
        
        workbook.write(servletOutputStream);  
        servletOutputStream.flush();  
        servletOutputStream.close();  
        
        
    }  
    
    
    
    
}

