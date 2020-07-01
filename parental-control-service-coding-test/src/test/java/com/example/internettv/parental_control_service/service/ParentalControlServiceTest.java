package com.example.internettv.parental_control_service.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.example.internettv.thirdparty.MovieService;
import com.example.internettv.thirdparty.TechnicalFailureException;
import com.example.internettv.thirdparty.TitleNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author Thanuja
 * Unit test cases for Service methods
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ParentalControlServiceTest {
	
	@InjectMocks ParentalControlServiceImpl parentalControlService;
	@Mock MovieService movieService;


	/**
	 * Test Case 1- Customer is not eligible to watch the selected Movie
	 */
	@Test
	public void canWatchMovieFalse() throws Exception {
		//assign
		String titleId = "IRONMAN";
		Mockito.when(movieService.getParentalControlLevel(titleId)).thenReturn("15");
		//act
		boolean controlAccess = parentalControlService.canWatchMovie("12", titleId);
		//assert
		assertNotNull(controlAccess);
		assertEquals(false,controlAccess);
	}

	/**
	 * Test Case 2- Customer is eligible to watch the selected Movie
	 */
	@Test
	public void canWatchMovieTrue() throws Exception {
		//assign
		String titleId = "ICEAGE";
		Mockito.when(movieService.getParentalControlLevel(titleId)).thenReturn("U");
		//act
		boolean controlAccess = parentalControlService.canWatchMovie("12", titleId);
		//assert
		assertNotNull(controlAccess);
		assertEquals(true,controlAccess);
	}

	/**
	 * Test Case 3- The Selected Movie is not found
	 */
	@Test (expected= TitleNotFoundException.class)
	public void movieNotFoundException() throws Exception {
		//assign
		String titleId = "TITLE_NOT_FOUND";
		Mockito.when(movieService.getParentalControlLevel(titleId)).thenThrow(new TitleNotFoundException());
		//act
		boolean controlAccess = parentalControlService.canWatchMovie("12", titleId);
		//assert TitleNotFoundException
		
	}

	/**
	 * Test Case 4- Unexpected Exception
	 */
	@Test (expected= TechnicalFailureException.class)
	public void movieTechnicalFailureException() throws Exception {
		//assign
		String titleId = "TECHNICAL_FAILURE";
		Mockito.when(movieService.getParentalControlLevel(titleId)).thenThrow(new TechnicalFailureException());
		//act
		boolean controlAccess = parentalControlService.canWatchMovie("U", titleId);
		//assert TechnicalFailureException
		
	}

	/**
	 * Test Case 5-  Exception thrown when Movie Parental Control is not available
	 *  for the selected movie in the defined Parental Control Levels (U, PG, 12, 15, 18)
	 */
	@Test (expected= Exception.class)
	public void movieParentalControlNotFoundInStaticList() throws Exception {
		//assign
		String titleId = "MOVIE_CONTROL_NOT_FOUND_IN_STATIC_LIST";
		Mockito.when(movieService.getParentalControlLevel(titleId)).thenReturn("24");
		//act
		boolean controlAccess = parentalControlService.canWatchMovie("U", titleId);
		//assert Exception
		
	}

	/**
	 * Test Case 6-  Exception thrown when Movie Parental Control is returned as null
	 *  for the selected movie
	 */
	@Test (expected= Exception.class)
	public void movieParentalControlNull() throws Exception {
		//assign
		String titleId = "MOVIE_CONTROL_NOT_FOUND_IN_STATIC_LIST";
		Mockito.when(movieService.getParentalControlLevel(titleId)).thenReturn(null);
		//act
		boolean controlAccess = parentalControlService.canWatchMovie("U", titleId);
		//assert Exception

	}
	/**
	 * Test Case 7-  Exception thrown when Customer Parental Control is not available
	 *  in the defined Parental Control Levels (U, PG, 12, 15, 18)
	 */
	@Test (expected= Exception.class)
	public void customerParentalControlNotFoundInStaticList() throws Exception {
		//assign
		String titleId = "CUSTOMER_CONTROL_NOT_FOUND_IN_STATIC_LIST";
		Mockito.when(movieService.getParentalControlLevel(titleId)).thenReturn("U");
		//act
		boolean controlAccess = parentalControlService.canWatchMovie("50", titleId);
		//assert Exception

	}

}
