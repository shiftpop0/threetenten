package edu.ncist.wang.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class AjaxOperation {

    /**
     * ajax∑µªÿ–≈œ¢
     */
    @SuppressWarnings("unused")
	public static void ajaxToHtml(HttpServletResponse response,String msg){
    	response.setContentType("text/html;charset=GBK");
		try {
			PrintWriter out = response.getWriter();
			out.print(msg);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
}
