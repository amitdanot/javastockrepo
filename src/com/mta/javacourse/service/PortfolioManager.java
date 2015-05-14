package com.mta.javacourse.service;

import java.util.Calendar;
import java.util.Date;

import com.mta.javacourse.model.Portfolio;
import com.mta.javacourse.model.Stock;
/**
 * This class represents a manager for a single portfolio 
 * @author Amit Danot
 *
 */
public class PortfolioManager {
	/**
	 * create and return a new portfolio instance
	 * @return
	 */

	public Portfolio getPortfolio(){
		Portfolio myPortfolio = new Portfolio("Exercise 7 portfolio");
		myPortfolio.updateBalance(10000);
		
		Calendar cal = Calendar.getInstance(); 
		cal.set(2014, 11, 15);
			
		Date date1 = cal.getTime();
		Stock s1 = new Stock("PIH",10F,8.5F, date1);
		
		Date date2 = cal.getTime();
		Stock s2 = new Stock("AAL", 30F, 25.5F, date2);
		
		Date date3 = cal.getTime();
		Stock s3 = new Stock("CAAS", 20F, 15.5F, date3);
		
		myPortfolio.buyStock(s1, 20);
		myPortfolio.buyStock(s2, 30);
		myPortfolio.buyStock(s3, 40);
		
		myPortfolio.sellStock("AAL", -1);
		myPortfolio.removeStock("CAAS");
		
		return myPortfolio;
	}
}
