package com.mta.javacourse;

import java.util.Date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Stock {
	
	public final static int BUY = 0;
	public final static int SELL = 1;
	public final static int REMOVE = 2;
	public final static int HOLD = 3;
	
	private String symbol;
	private float ask;
	private float bid;
	private Date date;
	private int recommendation;
	private int stockQuantity;
	
	DateFormat dateFt = new SimpleDateFormat("MM/dd/yyyy");
	
	public Stock(String symbol, float ask, float bid, Date date) {
		this.symbol = symbol;
		this.ask = ask;
		this.bid = bid;
		this.date = date;
		
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
	
	public String getHtmlDescription(){
		String dateStr = dateFt.format(date);
		String result = new String("<b>Stock symbol is: </b>" +getSymbol()+" <b>ask: </b>"+getAsk() +" <b>Bid: </b>"+getBid()+" <b>Date: </b>"+ dateStr);
		return result;
	}
}