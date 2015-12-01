package com.ticketservice.main;

import java.util.HashMap;
import java.util.Map;

import com.ticketservice.exception.ServiceException;
import com.ticketservice.util.Constants;

import junit.framework.TestCase;

/**
 * Unit test for simple TicketServiceMain
 */
public class TicketServiceMainTest extends TestCase
{
	public void testTicketServiceMain() {
		
		//PropertiesCache propertiesCache = PropertiesCache.getInstance();				
				
		TicketServiceMain objTicketServiceMain = new TicketServiceMain();
        Map<String, String> objMap = new HashMap<String, String>();
        //objMap.put(Constants.OPERATION_OPTION, "1");
        //objMap.put(Constants.VENUE_LEVEL, "4"); //when OPERATION_OPTION is 1
        
        /*objMap.put(Constants.OPERATION_OPTION, "2");
        objMap.put(Constants.NUM_SEATS, "25");
        objMap.put(Constants.CUSTOMER_EMAIL, "sp@hotmail.com");
        objMap.put(Constants.MIN_LEVEL, "1");
        objMap.put(Constants.MAX_LEVEL, "1");*/
        
        objMap.put(Constants.OPERATION_OPTION, "3");
        objMap.put(Constants.SEAT_HOLD_ID, "113");
        objMap.put(Constants.CUSTOMER_EMAIL, "sp@hotmail.com");
        
        try {
			objTicketServiceMain.accessService(objMap);
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
			//e.printStackTrace();
		}
        
	}
}
