package com.mta.javacourse.model;

import com.mta.javacourse.Stock;
/**
 * This class represents a Portfolio of Stocks.
 * where the maximum of stocks in the Portfolio is 5.
 * 
 * @author AmitDanot
 * @since 26/4/2015
 */

public class Portfolio {
	
	public final static int MAX_PORTFOLIO_SIZE = 5;

	private String title;
	private int portfolioSize;
	private Stock[] stocks;
	
	/**
	 * C'tor of Portfolio.
	 * Receives the title of the portfolio.
	 * Creates an instance of an array of Stocks 
	 * Set the Portfolio Size to start as 0.
	 * @param title
	 * 		  the title of the Portfolio
	 * @see com.mta.javacourse
	 */
	
	public Portfolio(String title) {
		this.title = title;
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.portfolioSize = 0;
		
	}
	public void addStock(Stock stock){
		
		if(portfolioSize< MAX_PORTFOLIO_SIZE)
		{
			stocks[this.portfolioSize] = stock;
			portfolioSize++;
		}
	}

	public Stock[] getStocks(){
		return stocks;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPortfolioSize() {
		return portfolioSize;
	}
	public void setPortfolioSize(int portfolioSize) {
		this.portfolioSize = portfolioSize;
	}
	public static int getMaxPortfolioSize() {
		return MAX_PORTFOLIO_SIZE;
	}
	public void setStocks(Stock[] stocks) {
		this.stocks = stocks;
	}
	/**
	 * Method uses the portfolio's stock details.
	 * @return string with portfolio's detains in HTML code.
	 */
	public String getHtmlString(){
		String htmlResString = new String();
		htmlResString = htmlResString+"<h1>"+this.title+"</h1> <br>";
		
		for(int i=0; i<portfolioSize;i++)
		{
			htmlResString = htmlResString + stocks[i].getHtmlDescription()+"<br>";
		}
		
		return htmlResString;
	}
}