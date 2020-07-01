package com.example.internettv.parental_control_service.service;

import com.example.internettv.parental_control_service.utils.ParentalControlLevelMap;
import com.example.internettv.thirdparty.MovieService;
import com.example.internettv.thirdparty.TechnicalFailureException;
import com.example.internettv.thirdparty.TitleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Thanuja
 * Implementation class for ParentalControlService
 *
 */
@Service
public class ParentalControlServiceImpl implements ParentalControlService{

    //@Autowired
	public MovieService service;
		
	public boolean canWatchMovie(String customerParentalControlLevel, String movieId) throws TitleNotFoundException, TechnicalFailureException {
		String movieControlLevel = service.getParentalControlLevel(movieId);
		Integer movieControlLevelRank = ParentalControlLevelMap.PARENTAL_CONTROL_LEVEL.get(movieControlLevel);
		Integer customerControlLevelRank = ParentalControlLevelMap.PARENTAL_CONTROL_LEVEL.get(customerParentalControlLevel);
		if(customerControlLevelRank>=movieControlLevelRank) {
			return true;
		}
		else {
			return false;
		}
	}

}
