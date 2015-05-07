package com.mta.javacourse.service;

import java.util.Calendar;
import java.util.Date;

import com.mta.javacourse.model.Portfolio;
import com.mta.javacourse.model.Stock;
/**
 * This class represents a manager for a single portfolio 
 * @author Amit
 *
 */
public class PortfolioManager {
	/**
	 * create and return a new portfolio instance
	 * @return
	 */

	public Portfolio getPortfolio(){
		Portfolio portfolio = new Portfolio("Portfolio");
		
		Calendar cal = Calendar.getInstance(); 
		cal.set(2014, 10, 15);
			
		Date date1 = cal.getTime();
		Stock s1 = new Stock("PIH", (float)13.1 ,(float)12.4, date1);
		
		Date date2 = cal.getTime();
		Stock s2 = new Stock("AAL", (float)5.78 ,(float)5.5, date2);
		
		Date date3 = cal.getTime();
		Stock s3 = new Stock("CAAS", (float)32.2 ,(float)31.5, date3);
		
		portfolio.addStock(s1);
		portfolio.addStock(s2);
		portfolio.addStock(s3);
		
		return portfolio;
	}
}
