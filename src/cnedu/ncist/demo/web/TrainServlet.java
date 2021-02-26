package cnedu.ncist.demo.web;

import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ncist.wang.hkdf.web.MainServlet;

public class TrainServlet extends MainServlet {

	public void init(ServletConfig config) throws ServletException{
		
		super.init(config);
		
	}
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        super.service(request, response);

    }
}
