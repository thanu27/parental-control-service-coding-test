package com.example.internettv.parental_control_service.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Thanuja
 * Parental Control Levels
 *
 */
public class ParentalControlLevelMap {

	/* Create static map to load Parental Control Levels where U is the least restrictive and 18 is the most restrictive*/
	public static final Map<String, Integer> PARENTAL_CONTROL_LEVEL;

	static {
	    Map<String, Integer> map = new HashMap<String, Integer>();
	    map.put("U", 1);
	    map.put("PG", 2);
	    map.put("12", 3);
	    map.put("15", 4);
	    map.put("18", 5);
	    PARENTAL_CONTROL_LEVEL = Collections.unmodifiableMap(map);
	}

}
