package com.mta.javacourse.model;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.mta.javacourse.model.Portfolio.ALGO_RECOMMENDATION;
@SuppressWarnings("unused")
/**
 * this class represents a Stock of Stocks
 * @param symbol
 * @param bid - for buy
 * @param ask - for sell
 * @param date 
 * @param recommendation 
 * @param stockQuantity - number of stocks with the same type
 * @author Amit Danot
 *
 */
public class Stock {
	
	//dfaf
	
	private String symbol;
	private float ask;
	private float bid;
	private Date date;
	private ALGO_RECOMMENDATION recommendation;
	private int stockQuantity;
	private DateFormat dateFt = new SimpleDateFormat("MM/dd/yyyy");
	
	/**
	 * C'tor of stock
	 * 
	 * @param symbol - name of stock
	 * @param ask - ask value
	 * @param bid - bid value
	 * @param date - create date of the stock
	 * recommendation and stockQuantity are set to default of 0 until we use them
	 * @author Amit
	 */
	public Stock(String symbol, float ask, float bid, Date date) {
		this.symbol = symbol;
		this.ask = ask;
		this.bid = bid;
		this.date = date;
		this.recommendation = ALGO_RECOMMENDATION.HOLD; 
		this.stockQuantity = 0;
	}
	/**
	 * copy c'tor of stock
	 * @param stock1
	 */
	
	public Stock(Stock stock1) {
		this(stock1.getSymbol(),stock1.getAsk(),stock1.getBid(),new Date(stock1.getDate().getTime()));
		this.recommendation = stock1.getRecommendation();
		this.stockQuantity = stock1.getStockQuantity();
	}
	
	/**
	 * Method uses the stock's details (bid,date,ask,symbol)
	 * @return string of each stock with his details in HTML code
	 */
	
	public String getHtmlDescription(){
		String dateStr = dateFt.format(date);
		String result = new String("<b>Stock symbol is: </b>" +getSymbol()+" <b>ask: </b>"+getAsk() +" <b>Bid: </b>"+getBid()+" <b>Date: </b>"+ dateStr+" <b>Quantity: </b>"+ getStockQuantity());
		return result;
	}

	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public float getAsk() {
		return ask;
	}
	public void setAsk(float ask) {
		this.ask = ask;
	}
	public float getBid() {
		return bid;
	}
	public void setBid(float bid) {
		this.bid = bid;
	}
	public java.util.Date getDate() {
		return date;
	}
	public void setDate(java.util.Date date) {
		this.date = date;
	}
	
	public ALGO_RECOMMENDATION getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(ALGO_RECOMMENDATION recommendation) {
		this.recommendation = recommendation;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
}