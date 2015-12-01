package com.ticketservice.util;

import com.ticketservice.bean.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import junit.framework.TestCase;

public class TicketServiceUtilTest extends TestCase {
	TicketServiceUtil objTicketServiceUtil = new TicketServiceUtil();

	public void testCalculateLavels() {
		System.out.println("in testCalculateLavels");
		Optional<Integer> minLevel = Optional.ofNullable(null);
		/*Optional<Integer> minLevel = null;
		String  venueLevelStr = "2";
		if(venueLevelStr != null) {
			minLevel = Optional.of(Integer.valueOf(venueLevelStr));
		}*/
		
		//Optional<Integer> maxLevel = Optional.ofNullable(null);
		Optional<Integer> maxLevel = null;
		String  venueLevelStr2 = "3";
		if(venueLevelStr2 != null) {
			maxLevel = Optional.of(Integer.valueOf(venueLevelStr2));
		}
		
		Map<Integer, LevelVO> aMap = new HashMap<Integer, LevelVO>();
		LevelVO aLevelVO1 = new LevelVO();
		aMap.put(1, aLevelVO1);
		LevelVO aLevelVO2 = new LevelVO();
		aMap.put(2, aLevelVO2);
		LevelVO aLevelVO3 = new LevelVO();
		aMap.put(3, aLevelVO3);
		LevelVO aLevelVO4 = new LevelVO();
		aMap.put(4, aLevelVO4);
		
		Set<Integer> aSet = aMap.keySet();
		
		for(Integer key : aSet) {
			System.out.print("\tkey1: "+key);
		}
		System.out.println();
		
		Set<Integer> aNewSet = objTicketServiceUtil.calculateLavels(minLevel, maxLevel, aSet);
		System.out.println();
		for(Integer int1 : aNewSet) {
			System.out.print("\tint1: "+int1);
		}
	}
	
	public void testGetId() {
		System.out.println("in testGetId");
		//Optional<Integer> previousId = Optional.ofNullable(null);
		int previousId = 1111;
		System.out.println(objTicketServiceUtil.getId(previousId));
	}


	public void testGenerateReservationCode() {
		System.out.println();
		System.out.println("in testGenerateReservationCode");
		System.out.println(objTicketServiceUtil.generateReservationCode());

	}
	
	

}
