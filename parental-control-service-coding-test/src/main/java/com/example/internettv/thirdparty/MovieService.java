package com.example.internettv.thirdparty;

public interface MovieService {
	
	String getParentalControlLevel(String titleId) throws TitleNotFoundException, TechnicalFailureException;

}
