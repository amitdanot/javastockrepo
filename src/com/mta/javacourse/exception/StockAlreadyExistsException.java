package com.mta.javacourse.exception;

import org.algo.exception.PortfolioException;

public class StockAlreadyExistsException extends PortfolioException {
	
	public StockAlreadyExistsException(){
		super("Stock Already Exists");
	}
	
	public StockAlreadyExistsException(String symbol){
		super("Stock "+symbol+" already exists in portfolio.");
	}

}
