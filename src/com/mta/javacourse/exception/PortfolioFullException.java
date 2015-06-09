package com.mta.javacourse.exception;

import org.algo.exception.PortfolioException;

public class PortfolioFullException extends PortfolioException{
	
	public PortfolioFullException(){
		super("Portfolio is full. adding more stocks than max stocks limitation");
	}

}
