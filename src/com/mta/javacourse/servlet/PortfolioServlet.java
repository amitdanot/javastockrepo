package com.mta.javacourse.servlet;

import javax.servlet.http.HttpServlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mta.javacourse.model.Portfolio;
import com.mta.javacourse.service.PortfolioManager;

@SuppressWarnings({ "serial", "unused" })
public class PortfolioServlet extends HttpServlet  {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
	resp.setContentType("text/html");
	
	PortfolioManager portfolioManager = new PortfolioManager();
	
	Portfolio portfolio = portfolioManager .getPortfolio();
	Portfolio portfolio2 = new Portfolio(portfolio);
	portfolio2.setTitle("Portfolio #2");
	
	resp.getWriter().println(portfolio.getHtmlString());
	resp.getWriter().println(portfolio2.getHtmlString());
	
	portfolio.removeStock(portfolio.getStocks()[0].getSymbol());
	
	resp.getWriter().println(portfolio.getHtmlString());
	resp.getWriter().println(portfolio2.getHtmlString());
	
	portfolio2.getStocks()[2].setBid(55.55f);
	
	resp.getWriter().println(portfolio.getHtmlString());
	resp.getWriter().println(portfolio2.getHtmlString());
	
	}
}
