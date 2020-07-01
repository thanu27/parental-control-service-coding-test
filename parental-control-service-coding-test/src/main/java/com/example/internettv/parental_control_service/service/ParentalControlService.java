package com.example.internettv.parental_control_service.service;

/**
 * @author Thanuja
 * Service class for Parental Control level
 *
 */
public interface ParentalControlService {
	
	boolean canWatchMovie(String customerParentalControlLevel, String movieId) throws Exception;

}
