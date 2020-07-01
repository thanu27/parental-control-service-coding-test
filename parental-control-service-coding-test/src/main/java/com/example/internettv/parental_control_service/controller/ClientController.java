package com.example.internettv.parental_control_service.controller;

import com.example.internettv.parental_control_service.service.ParentalControlService;
import com.example.internettv.thirdparty.TitleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.internettv.thirdparty.TechnicalFailureException;

/**
 * @author Thanuja
 * Controller class for Parental Control Service
 *
 */
@RestController
public class ClientController {
	
	@Autowired
	ParentalControlService service;
	
	/**
	 * @param customerParentalControlLevel - Parental Control Level for Customer
	 * @param movieId - Selected Movie 
	 * @return - boolean value to determine access to watch movie
	 */
	@SuppressWarnings("rawtypes")
	@GetMapping("/canWatchMovie")
	public ResponseEntity<Object> canWatchMovie(@RequestParam("customerParentalControlLevel") String customerParentalControlLevel, @RequestParam("movieId") String movieId) {
		boolean controlAccess = false;
		if(null!=customerParentalControlLevel && null!=movieId)
		//if(StringUtils.isNotBlank(customerParentalControlLevel) && StringUtils.isNotBlank(movieId))
		{
			try {
				controlAccess = service.canWatchMovie(customerParentalControlLevel, movieId);
			}
			catch (TitleNotFoundException ex) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        } catch (TechnicalFailureException ex) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        } catch (Exception e) {
	        	 return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity(controlAccess,HttpStatus.OK);	
		}
		else {
			return new ResponseEntity("Provide valid query parameters",HttpStatus.BAD_REQUEST);
		}
	}

}
