package com.mta.javacourse;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
public class MathClassServlet extends HttpServlet {
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		resp.setContentType("text/html");
		int radius = 50;
		double area;
		area = (radius*radius)* Math.PI;
		String line1 = new String("calculation 1: Area of circle with radius " + radius + " is: " + area + " square­ cm ");
		
		
		//triangle
		int angleB=30;
		int hypotenuse=50;
		double angleBInRadians = Math.toRadians(angleB);
		double opposite = Math.sin(angleBInRadians) * hypotenuse;
		String roundOpposite = String.format("%.2f", opposite);
		String line2 = new String ("calculation 2: Length of opposite where angle B is: " + angleB + " degrees" + " is: " + roundOpposite + ".");
		
		
		//power
		int base = 20;
		int exp = 13;
		long power = (long) Math.pow(base, exp);
		String line3 = new String ("calculation 3: Power of " + base +" with exp of " + exp + " is: " + power);

		
String resultStr = "<br>" + line1 + "<br>" + line2 + "<br>" + line3;
		
		resp.setContentType("text/html");
		resp.getWriter().println(resultStr);
		}
}



