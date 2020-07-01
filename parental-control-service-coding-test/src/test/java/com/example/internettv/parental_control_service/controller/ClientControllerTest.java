package com.example.internettv.parental_control_service.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.internettv.parental_control_service.service.ParentalControlService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.internettv.thirdparty.TechnicalFailureException;
import com.example.internettv.thirdparty.TitleNotFoundException;

/**
 * @author Thanuja
 * Unit Test cases for Controller methods
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest
public class ClientControllerTest {
	
	@Autowired MockMvc mockmvc;
	@MockBean ParentalControlService service;
	
	public void setUp() throws Exception{
		Mockito.when(service.canWatchMovie(Mockito.anyString(), Mockito.anyString())).thenReturn(false);
	}
	/**
	 * Test Case 1 - Check whether Customer is able to watch the selected Movie
	 */
	@Test
	public void canWatchMovie() throws Exception {
		
		mockmvc.perform(MockMvcRequestBuilders
				.get("/canWatchMovie")
				.param("customerParentalControlLevel", "12").param("movieId", "123"))
				.andExpect(status().isOk());
	}
	 /**
	 * Test Case 2 -  Check TitleNotFoundException - Selected Movie Not Found
	 */
	@Test
    public void canWatchMovieNotFoundException() throws Exception {

		Mockito.when(service.canWatchMovie(Mockito.anyString(), Mockito.anyString())).thenThrow(new TitleNotFoundException());

		mockmvc.perform(MockMvcRequestBuilders
				.get("/canWatchMovie")
				.param("customerParentalControlLevel", "PG").param("movieId", "TITLE_NOT_FOUND"))
				.andExpect(status().isNotFound());
    }
	/**
	 * Test Case 3 - Check Technical Failure Exception - Unexpected Exception
	 */
	@Test
    public void canWatchMovieTechnicalFailureException() throws Exception {

		Mockito.when(service.canWatchMovie(Mockito.anyString(), Mockito.anyString())).thenThrow(new TechnicalFailureException());

		mockmvc.perform(MockMvcRequestBuilders
				.get("/canWatchMovie")
				.param("customerParentalControlLevel", "15").param("movieId", "TECHNICAL_FAILURE"))
				.andExpect(status().isInternalServerError());
    }

	/**
	 * Test Case 4 - Throw Bad Request Error when mandatory parameter movieId is missing
	 */
	@Test
	public void canWatchMovieMissingMovieIdParameterException() throws Exception {

		mockmvc.perform(MockMvcRequestBuilders
				.get("/canWatchMovie")
				.param("customerParentalControlLevel", "12"))
				.andExpect(status().isBadRequest());
	}

	/**
	 * Test Case 5 - Throw Bad Request Error when mandatory parameter customerParentalControlLevel is missing
	 */
	@Test
	public void canWatchMovieMissingCustomerParentalControlLevelParameterException() throws Exception {

		mockmvc.perform(MockMvcRequestBuilders
				.get("/canWatchMovie")
				.param( "movieId","IRONMAN" ))
				.andExpect(status().isBadRequest());
	}

}
