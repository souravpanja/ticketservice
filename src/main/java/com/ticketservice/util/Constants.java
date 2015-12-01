package com.ticketservice.util;

public interface Constants {
	
	String VENUE_PROPERtIES = "venue.txt";
	String MESSAGES_PROPERtIES = "messages.properties";
	
	String EXIT_TEXT = "EXIT_TEXT";
	
	String LEVEL_ROW_VALUE_SPLITER = ";";
	String CR = System.getProperty("line.separator");
	String OPERATION_OPTION = "method_choice";
	String VENUE_LEVEL = "venueLevel";
	String NUM_SEATS = "numSeats";
	String MIN_LEVEL = "minLevel";
	String MAX_LEVEL = "maxLevel";
	String CUSTOMER_EMAIL = "customerEmail";
	String CUSTOMER_EMAIL_RESERVED = "customerEmail";
	String SEAT_HOLD_ID = "seatHoldId";
	String LEVEL_CONST = "LEVEL_";
	String ROW_CONST = "ROW_";
	String SEAT_CONST = "SEAT_";
	
	String HOLD_STATUS = "HOLD";
	String FREE_STATUS = "FREE";
	String RESERVED_STATUS = "RESERVED";
	int STARTING_ID = 1111;
	String INITIAL_DELAY_SEC = "SUBSEQUENT_RATE_SEC";
	String SUBSEQUENT_RATE_SEC = "SUBSEQUENT_RATE_SEC";
	String HOLD_EXPIRED_IN_SEC = "HOLD_EXPIRED_IN_SEC";
	
	String REGX_OPTION = "[1-3]";
	String REGX_VENUE_LEVEL = "[1-4]";
	String REGX_INTEGER = "[0-9]*";
	String REGX_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	String NO_HOLD_SEATS = "NO_HOLD_SEATS";
	String WRONG_OPTION = "WRONG_OPTION";
	String WRONG_NUMSEATS = "WRONG_NUMSEATS";
	String WRONG_EMAIL = "WRONG_EMAIL";
	String WRONG_VENUE_LEVEL = "WRONG_VENUE_LEVEL";
	String WRONG_VENUE_MINMAX_LEVEL = "WRONG_VENUE_MINMAX_LEVEL";
	String NO_AVAILABLE_SEATS = "NO_AVAILABLE_SEATS";
	String REQ_SEATS_LARGER = "REQ_SEATS_LARGER";
	
}
