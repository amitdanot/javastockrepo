
package com.mta.javacourse.model;

import org.algo.model.PortfolioInterface;
import org.algo.model.StockInterface;


/**
 * This class represents a Portfolio of Stocks.
 * where the maximum of stocks in the Portfolio is 5.
 * 
 * @author AmitDanot.
 * @since 26/4/2015
 */

public class Portfolio implements PortfolioInterface {
	
	private final static int MAX_PORTFOLIO_SIZE = 5;
	
	public enum ALGO_RECOMMENDATION {
		BUY, SELL, REMOVE , HOLD 
	}
	
	private String title;
	private int portfolioSize;
	private StockInterface[] stocks;
	private float balance ; 
	/**
	 * C'tor of Portfolio.
	 * Creates an instance of an array of Stocks
	 * Set the Portfolio Size to start as 0.
	 * @param title
	 * 		  the title of the Portfolio
	 * @author Amit
	 */
	public Portfolio() {
		this.title = new String("temp title");
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.portfolioSize = 0;
		this.balance = 0;
	}
	
	/**
	 * C'tor of Portfolio.
	 * Creates an instance of an array of Stocks
	 * Set the Portfolio Size to start as 0.
	 * @param title
	 * 		  the title of the Portfolio
	 * @param stocksArray
	 */
	public Portfolio(StockInterface[] stocksArray) {
		this.portfolioSize = stocksArray.length;
		 this.title = new String("Temporary Title");
		this.stocks = new StockInterface[MAX_PORTFOLIO_SIZE];
		this.balance = 0;
		for(int i = 0; i<this.portfolioSize; i++){
			this.stocks[i]= new Stock ((Stock)stocksArray[i]);;
			/*
			 * this.title = new String("Temporary Title");
		this.stocks = new StockInterface[MAX_PORTFOLIO_SIZE];
			 */
		}
	}
	/**
	 * Copy C'tor of Portfolio.
	 * Receives the title of the portfolio.
	 * Creates an instance of an array of Stocks 
	 * Set the Portfolio Size to start as 0.
	 * @param title
	 * 		  the title of the Portfolio
	 * @see com.mta.javacourse.model
	 */
	
	public Portfolio(String name) {
		this.title = name;
		this.stocks = new StockInterface[MAX_PORTFOLIO_SIZE];
		this.portfolioSize = 0;
		this.balance = 0 ;
	}
	/**
	 * Copy c'tor of portfolio type
	 * copy an array of stocks from one to the empty one
	 * @param portfolio
	 */

	public Portfolio(Portfolio oldPortfolio) {
		this(oldPortfolio.getTitle());
		this.portfolioSize =oldPortfolio.getPortfolioSize();
		this.updateBalance(oldPortfolio.getBalance()); 
		
		for (int i=0; i<portfolioSize; i++){
			this.stocks[i] = new Stock((Stock) oldPortfolio.getStocks()[i]);
			}
	
		}
	/**
	 * Add stock to portfolio array 
	 * 
	 * @param stock
	 */
	public void addStock(Stock stock){

		if(portfolioSize == MAX_PORTFOLIO_SIZE){
			System.out.println("Can’t add new stock, portfolio can have only "+MAX_PORTFOLIO_SIZE+" stocks");
			return;
		}else if (stock == null){
			System.out.println("error!");
			return;
		}else {
			int i = this.findStock (stock.getSymbol());
			if(i != -1){
				System.out.println("Stock already exists in portfolio.");
				return;
			}
		}
		
		stocks[this.portfolioSize] = stock;
		((Stock) stocks[this.portfolioSize]).setStockQuantity(0); 
		this.portfolioSize++;
		return;
	}
	/**
	 * remove stocks from protfolio , by comparison the symbol of the stock
	 * @param stock
	 */
	public boolean removeStock(String stockName){
		
		if (stockName == null){
			System.out.println("The stock received is invalid!");
			return false;
		}
	
		int i = this.findStock (stockName);
			
		if(i>-1){
			if (portfolioSize > 1){
				this.sellStock(stocks[i].getSymbol(), -1);
				stocks[i] = stocks[this.portfolioSize-1];
				stocks[this.portfolioSize-1]=null;
				
			}else  if (this.portfolioSize == 1){
				this.sellStock(stocks[i].getSymbol(), -1);
				stocks[i]=null;
			}
			portfolioSize--;
			System.out.println("Stock "+stockName+" was deleted as per request");
			return true;
		}
	
	System.out.println("Stock was not found in this Portfolio");
	return false;
	}
/**
 * Method update stock quantity Depending sale
 * "-1 " means all specific stocks will be sold
 * @param symbol
 * @param quantity
 * @return
 */
	
	public boolean sellStock (String symbol, int quantity){
		if (symbol == null || quantity < -1 || quantity == 0 )
		{
			System.out.println("There is an error at stock symbol or stock quntity");
			return false;
		}
		
		int i = this.findStock (symbol);
		
		if(i>-1){
				if(((Stock) stocks[i]).getStockQuantity() < quantity )
				{
					System.out.println("Not enough stocks to sell");
					return false;
				}
				else if (quantity == -1)
				{
					this.updateBalance(((Stock) this.stocks[i]).getStockQuantity()*this.stocks[i].getBid());
					((Stock) this.stocks[i]).setStockQuantity(0);
					System.out.println("Entire stocks kind of ("+symbol+") - was sold succefully");
					return true;
				}
				else { 
					this.updateBalance(quantity*this.stocks[i].getBid());
					int currQuntity = ((Stock) this.stocks[i]).getStockQuantity();
					((Stock) this.stocks[i]).setStockQuantity(currQuntity - quantity);
					System.out.println(quantity +" stocks kind of ("+symbol+") - was sold succefully");
					return true;
				}
			}
		
		System.out.println("Stock was not found in this Portfolio");
		return false; 
	}
	/**
	 * Method update the stock quantity depending Buy
	 * " -1" means all specific stocks will be used
	 * @param stock
	 * @param quantity
	 * @return
	 */
	
	public boolean buyStock (Stock stock , int quantity){
		
		if (stock == null || quantity < -1 )
		{
			System.out.println("There is an error at stock , or stock quntity");
			return false;
		}
		if (quantity * stock.getAsk() > this.balance)
		{
			System.out.println("Not enough balance to complete purchase.");
			return false;
		}
		int i = this.findStock (stock.getSymbol());
		
		if(i>-1){
				
				if (quantity == -1){
					int quantityToBuy = (int)this.balance / (int)this.stocks[i].getAsk();
					this.updateBalance(-quantityToBuy*this.stocks[i].getAsk());
					((Stock) this.stocks[i]).setStockQuantity(((Stock) this.stocks[i]).getStockQuantity()+quantityToBuy);
					System.out.println("Entire stock ("+stock.getSymbol()+") holdings that could be bought "
							+ "was bought succefully.");
					return true;
				}
				else {
					this.updateBalance(-quantity*this.stocks[i].getAsk());
					((Stock) this.stocks[i]).setStockQuantity(((Stock) stocks[i]).getStockQuantity()+quantity);
					System.out.println("An amount of "+quantity+" of stock ("+stock.getSymbol()+") was bought succefully");
					return true;
				}
			}
		if(i == MAX_PORTFOLIO_SIZE){
			System.out.println("Please note that the portfolio has reached it's maximum stock capacity.");
			return false;
		}
		
		if (quantity == -1){ // when we buy a new stock we also need to add it to array
			this.addStock(stock);
			int quantityToBuy = (int)this.balance/(int)this.stocks[i].getAsk();
			this.updateBalance(-(quantityToBuy*this.stocks[this.portfolioSize-1].getAsk()));
			((Stock) this.stocks[i]).setStockQuantity(((Stock) this.stocks[this.portfolioSize-1]).getStockQuantity()+quantityToBuy);
			System.out.println("Entire stock ("+stock.getSymbol()+") holdings that could be bought "
					+ "was bought succefully.");
			return true;
		} else {
			this.addStock(stock); //add the stock to portfolioSize-1 in the stocks array.
			this.updateBalance(-quantity*this.stocks[portfolioSize -1].getAsk());
			((Stock) this.stocks[this.portfolioSize -1]).setStockQuantity(quantity);
			System.out.println("Stock "+stock.getSymbol()+" was added successfuly to the portfolio. With quantity of "
					+ quantity+" stocks.");
			return true;

		}
	}
	/**
	 * Method calculates the portfolio's total stocks value by using another methods 
	 * @return
	 */
	public float getStocksValue(){  
		float totalValue =0;
		for(int i = 0; i<this.portfolioSize ;i++){
			totalValue += ((Stock) this.stocks[i]).getStockQuantity()*this.stocks[i].getBid();
		}
		return totalValue;		
	}
	/**
	 * Method uses the portfolio's stock details.
	 * @return string with portfolio's detains in HTML code.
	 */
	public String getHtmlString(){
		String htmlResString = new String();
		htmlResString = htmlResString+"<h1>"+getTitle()+"</h1> <br>";
		
		for(int i=0; i<portfolioSize;i++)
		{
			if(stocks[i] != null)
			{
			htmlResString = htmlResString + ((Stock) stocks[i]).getHtmlDescription()+"<br>";
			}
		}
		htmlResString += "Total Portfolio Value :"+this.getTotalValue()+ "$.<br>"+
		"Total Stocks Value :"+this.getStocksValue()+"$. <br>"+"Balance :"+this.balance+"$.";
		return htmlResString;
	}
	/**
	 * Find a stock in stocks array by symbol
	 * @param stockToFind
	 * @return index of the stock in the stocks array
	 * or return -1 if stock not found
	 */
	public int findStock (String stockToFind){
		for(int i = 0; i< this.portfolioSize; i++){
			if(stockToFind.equals(this.stocks[i].getSymbol())){
				return i;
			}
		}
		return -1;
	}
	
	public StockInterface findStockPlace (String stockToFind){
		int i = 0;
		for( i = 0; i< this.portfolioSize; i++){
			if(stockToFind.equals(this.stocks[i].getSymbol())){
				return this.stocks[i];
			}
		}
		return null;
	}
	/**
	 * Method receives amount and adds it to current balance
	 * @param amount
	 */
	
	public void updateBalance (float amount){
		float currBalance = this.balance + amount;
		if(currBalance < 0){
			System.out.println("You can not change balance to negative amount");
		}
		else{
			this.balance = currBalance ;
		}
	}

	
	/**
	 * Method calculates the portfolio's total value
	 * @return
	 */
	public float getTotalValue(){
		
		return this.getStocksValue()+this.balance;		
	}
	public StockInterface[] getStocks(){
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
	public float getBalance() {
		return balance;
	}
	

}

