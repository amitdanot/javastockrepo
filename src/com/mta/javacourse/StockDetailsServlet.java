package com.mta.javacourse;

import javax.servlet.http.HttpServlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings({ "serial", "unused" })
public class StockDetailsServlet extends HttpServlet  {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
	resp.setContentType("text/html");
	
	Calendar cal = Calendar.getInstance(); 
	cal.set(2014, 10, 15);
		
	Date date1 = cal.getTime();
	Stock s1 = new Stock("PIH", (float)13.1 ,(float)12.4, date1);
	
	Date date2 = cal.getTime();
	Stock s2 = new Stock("AAL", (float)5.78 ,(float)5.5, date2);
	
	Date date3 = cal.getTime();
	Stock s3 = new Stock("CAAS", (float)32.2 ,(float)31.5, date3);
	
	String str1 = new String(s1.getHtmlDescription());
	String str2 = new String(s2.getHtmlDescription());
	String str3 = new String(s3.getHtmlDescription());
	
	String res = new String(str1 + "<br>" + str2 + "<br>" + str3 + "<br>");
	resp.getWriter().println(res);
	
	
	}

}
