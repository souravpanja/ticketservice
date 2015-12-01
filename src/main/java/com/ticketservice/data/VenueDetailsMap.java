package com.ticketservice.data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.ticketservice.bean.Level;
import com.ticketservice.util.VenueCache;

public class VenueDetailsMap {
	
	private static Map<Integer, Level> levelMap = null;
	
	private VenueDetailsMap() {};

	/**
	 * @return the venuDetailsMap
	 */
	public static Map<Integer, Level> getLebelsMap() {
		if(levelMap == null) {
			levelMap = new HashMap<Integer, Level>();
			levelMap = generateStaticData(levelMap);
		}
		return levelMap;
	}
	
	private static Map<Integer, Level> generateStaticData(Map<Integer, Level> venuDetailsMap) {
		
		VenueCache propertiesCache = VenueCache.getInstance();
		Set<String> aSet = new HashSet<String>();
		
		aSet = propertiesCache.getAllPropertyNames();
		Integer keyInt = null;
		String levelRowValue = null;
		Level venueDetails = null;
		
        for(String key : aSet) {
        	//System.out.println(key);
        	keyInt = Integer.valueOf(key);
        	//System.out.println(propertiesCache.getProperty(key));
        	levelRowValue = propertiesCache.getProperty(key);
        	String[] levelRowValues = levelRowValue.split(";");
        	venueDetails = new Level();
        	
        	venueDetails.setLevelId(keyInt);
        	venueDetails.setLevelName(levelRowValues[0]);
        	venueDetails.setPrice(Float.valueOf(levelRowValues[1]));
        	venueDetails.setRows(Integer.valueOf(levelRowValues[2]));
        	//venueDetails.setSeatsInLevel(seatsInLevel);
        	venueDetails.setSeatsInRow(Integer.valueOf(levelRowValues[3]));
        	
        	venuDetailsMap.put(keyInt, venueDetails);
        }
		
		return venuDetailsMap;
	}

	
}
