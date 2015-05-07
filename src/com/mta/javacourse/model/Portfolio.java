package com.mta.javacourse.model;


/**
 * This class represents a Portfolio of Stocks.
 * where the maximum of stocks in the Portfolio is 5.
 * 
 * @author AmitDanot
 * @since 26/4/2015
 */

public class Portfolio {
	
	private final static int MAX_PORTFOLIO_SIZE = 5;

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
	 * @see com.mta.javacourse.model
	 */
	
	public Portfolio(String name) {
		this.title = name;
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.portfolioSize = 0;
		
	}
	/**
	 * Copy c'tor of portfolio type
	 * copy an array of stocks from one to the empty one
	 * @param portfolio
	 */

	public Portfolio(Portfolio oldPortfolio) {
		this(oldPortfolio.getTitle());
		this.portfolioSize =oldPortfolio.getPortfolioSize();
		
		for (int i=0; i<portfolioSize; i++){
			this.stocks[i] = new Stock(oldPortfolio.getStocks()[i]);
			}
		}
	/**
	 * Add stock to portfolio array 
	 * 
	 * @param stock
	 */
	public void addStock(Stock stock){
		
		if(portfolioSize< MAX_PORTFOLIO_SIZE && stock != null)
		{
			stocks[this.portfolioSize] = stock;
			portfolioSize++;
			
		}
			else{
				System.out.println("Sorry, portfolio is full or stock is null");
			}
		}
	/**
	 * remove stocks from protfolio , by comparison the symbol of the stock
	 * @param stock
	 */
    public void removeStock(String stockSymbol) 
    {
            for(int i = 0; i < portfolioSize; i++)
            {
                    if(this.stocks[i].getSymbol().equals(stockSymbol))
                   {
                            if(i != portfolioSize-1 && portfolioSize > 1)
                                   for(int j = i; j < portfolioSize-1; j++)
                                    {
                                           this.stocks[j] = new Stock(this.stocks[j+1]);
                                    }
                    }
            }
            portfolioSize--;
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
		htmlResString = htmlResString+"<h1>"+getTitle()+"</h1> <br>";
		
		for(int i=0; i<portfolioSize;i++)
		{
			
			htmlResString = htmlResString + stocks[i].getHtmlDescription()+"<br>";
		}
		
		return htmlResString;
	}
}