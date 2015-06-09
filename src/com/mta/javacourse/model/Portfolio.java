
package com.mta.javacourse.model;

import org.algo.model.PortfolioInterface;
import org.algo.model.StockInterface;

import com.mta.javacourse.exception.BalanceException;
import com.mta.javacourse.exception.PortfolioFullException;
import com.mta.javacourse.exception.StockAlreadyExistsException;
import com.mta.javacourse.exception.StockNotExistException;


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
	 * @throws BalanceException 
	 */

	public Portfolio(Portfolio oldPortfolio) throws BalanceException {
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
	public void addStock(Stock stock) throws StockAlreadyExistsException, PortfolioFullException{

		if(this.portfolioSize == MAX_PORTFOLIO_SIZE){
			throw new PortfolioFullException();
		}else if (stock == null){
			System.out.println("There is an error with stock received! (Check if it it istanciated)");
			
		}else {
			int i = this.findStock (stock.getSymbol());
			if(i != -1){
				throw new StockAlreadyExistsException(stock.getSymbol());
			}
		}

		stocks[this.portfolioSize] = stock;
		((Stock) stocks[this.portfolioSize]).setStockQuantity(0); // NOT ACTUALLY NEEDED CAUSE WHEN WE CREATE STOCK- DEFAULD IS 0.
		this.portfolioSize++;
		return;
	}
	/**
	 * remove stocks from protfolio , by comparison the symbol of the stock
	 * @param stock
	 * @throws BalanceException 
	 */
	
	public void removeStock(String stockName) throws StockNotExistException, BalanceException{
	int i=0;
		if (stockName == null){
			throw new StockNotExistException("The stock received is invalid!");
		}

		i = this.findStock (stockName);

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
		}
		else{
			throw new StockNotExistException();
		}
	}
/**
 * Method update stock quantity Depending sale
 * "-1 " means all specific stocks will be sold
 * @param symbol
 * @param quantity
 * @return
 * @throws BalanceException 
 */
	
	public void sellStock (String symbol, int quantity) throws IllegalArgumentException, StockNotExistException,BalanceException{
		if (symbol == null || quantity < -1 || quantity == 0 )
		{
			throw new IllegalArgumentException("There is an error! Please check your stock symbol or stock quntity.");
		}
		
		int i = this.findStock (symbol);
		
		if(i>-1){
				if(((Stock) stocks[i]).getStockQuantity() < quantity )
				{
					throw new IllegalArgumentException("Not enough stocks to sell");
				}
				else if (quantity == -1)
				{
					this.updateBalance(((Stock) this.stocks[i]).getStockQuantity()*this.stocks[i].getBid());
					((Stock) this.stocks[i]).setStockQuantity(0);
					System.out.println("Entire stocks kind of ("+symbol+") - was sold succefully");
					return;
				}
				else { 
					this.updateBalance(quantity*this.stocks[i].getBid());
					int currQuntity = ((Stock) this.stocks[i]).getStockQuantity();
					((Stock) this.stocks[i]).setStockQuantity(currQuntity - quantity);
					System.out.println(quantity +" stocks kind of ("+symbol+") - was sold succefully");
					return;
				}
			}
		
		throw new StockNotExistException("Stock was not found in this Portfolio");
	}
	/**
	 * Method update the stock quantity depending Buy
	 * " -1" means all specific stocks will be used
	 * @param stock
	 * @param quantity
	 * @return
	 * @throws BalanceException 
	 */
	public void buyStock(Stock stock, int quantity) throws PortfolioFullException,BalanceException, StockAlreadyExistsException, StockNotExistException{
		if(stock == null || quantity < -1){
			System.out.println("There is an error! Please check your stock symbol or stock quntity.");
			return;
		}

		int stockLocation = 0;
		stockLocation = this.findStock (stock.getSymbol());

		if(quantity*stock.getAsk() > this.balance){
			throw new BalanceException();
		}

		if(stockLocation == MAX_PORTFOLIO_SIZE-1){
			throw new PortfolioFullException();
		}


		if(stockLocation == -1){ 	 			//THE STOCK WAS NOT FOUND IN OUR STOCKS ARRAY
			try {								//NEED TO ADD IT TO THE PORTFOLIO ARRAY
				this.addStock(stock);
			
			} catch (StockAlreadyExistsException e) {
				e.getMessage();
				e.printStackTrace();
				throw e;
			}				

		}

		if(quantity == -1){
			stockLocation = this.findStock (stock.getSymbol());
			
			int howManyToBuy = (int)this.balance/(int)this.stocks[stockLocation].getAsk();
			try {
				this.updateBalance(-howManyToBuy*this.stocks[stockLocation].getAsk());
			} catch (BalanceException e) {
				e.getMessage();
				e.printStackTrace();
			}
			((Stock) this.stocks[stockLocation]).setStockQuantity(((Stock) this.stocks[stockLocation]).getStockQuantity()+howManyToBuy);
			System.out.println("Entire stock ("+stock.getSymbol()+") holdings that could be bought "
					+ "was bought succefully.");
			return;

		}else {
			stockLocation = this.findStock (stock.getSymbol());
			try {
				this.updateBalance(-quantity*this.stocks[stockLocation].getAsk());
			} catch (BalanceException e) {
				e.getMessage();
				e.printStackTrace();
			}
			((Stock) this.stocks[stockLocation]).setStockQuantity(((Stock) stocks[stockLocation]).getStockQuantity()+quantity);
			System.out.println("An amount of "+quantity+" of stock ("+stock.getSymbol()+") was bought succefully");
			return;
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
	
	public void updateBalance (float amount)throws BalanceException {
		float currBalance = this.balance + amount;
		if(currBalance < 0){
			throw new BalanceException("Please note you may not change balance to negative amount!");
		}
		else{
			this.balance = currBalance ;
			System.out.println("Balance has been updated to "+ this.balance);
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

