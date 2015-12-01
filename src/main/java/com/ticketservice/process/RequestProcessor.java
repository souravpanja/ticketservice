package com.ticketservice.process;

import java.util.Map;
import java.util.Optional;

import com.ticketservice.bean.SeatHold;
import com.ticketservice.exception.ServiceException;
import com.ticketservice.service.TicketService;
import com.ticketservice.service.TicketServiceImpl;
import com.ticketservice.util.Constants;
import com.ticketservice.util.PropertiesCache;
import com.ticketservice.util.TicketServiceUtil;
import com.ticketservice.util.Validator;

public class RequestProcessor {
	
	Validator validator = new Validator();
	PropertiesCache props = PropertiesCache.getInstance();
	TicketServiceUtil objTicketServiceUtil = new TicketServiceUtil();
	TicketService ticketService = new TicketServiceImpl();

	// This method is entry point for client to send data and receive result
	public Object getResult(Map<String, String> objMap) throws ServiceException {

		if(!validator.validOption(objMap.get(Constants.OPERATION_OPTION))) {
			throw new ServiceException(props.getProperty(Constants.WRONG_OPTION));
		}
		
		int choice = Integer.parseInt(objMap.get(Constants.OPERATION_OPTION));

		Object obj = this.enterValuesForOperation(choice, objMap);

		return obj;
	}

	// Input data validation and prepare to process
	private Object enterValuesForOperation(int choice, Map<String, String> objMap) throws ServiceException {

		Object returnObject = null;
		switch(choice) 
		{
		case 1: {
			//venueLevelStr
			String  venueLevelStr = objMap.get(Constants.VENUE_LEVEL);			
			if(!validator.validIntegerOptional(venueLevelStr)) {
				throw new ServiceException(props.getProperty(Constants.WRONG_VENUE_LEVEL));
			}			
			Optional<Integer> venuLevelOption = objTicketServiceUtil.getOptionalInteger(venueLevelStr);
			
			//numSeatsAvailable()
			int noOfAvailableSeats = ticketService.numSeatsAvailable(venuLevelOption);
			
			returnObject = Integer.valueOf(noOfAvailableSeats);
			break;
		}
		case 2: {	
			//numSeats
			if(!validator.validInteger(objMap.get(Constants.NUM_SEATS))) {
				throw new ServiceException(props.getProperty(Constants.WRONG_NUMSEATS));
			}
			int numSeats = Integer.valueOf(objMap.get(Constants.NUM_SEATS));
			
			//minLevelStr
			String  minLevelStr = objMap.get(Constants.MIN_LEVEL);
			if(!validator.validIntegerOptional(minLevelStr)) {
				throw new ServiceException(props.getProperty(Constants.WRONG_VENUE_LEVEL));
			}
			Optional<Integer> minLevelOption = objTicketServiceUtil.getOptionalInteger(minLevelStr);

			//maxLevelStr
			String  maxLevelStr = objMap.get(Constants.MAX_LEVEL);
			if(!validator.validIntegerOptional(maxLevelStr)) {
				throw new ServiceException(props.getProperty(Constants.WRONG_VENUE_LEVEL));
			}
			Optional<Integer> maxLevelOption = objTicketServiceUtil.getOptionalInteger(maxLevelStr);
			
			//customerEmail
			if(!validator.validEmail(objMap.get(Constants.CUSTOMER_EMAIL))) {
				throw new ServiceException(props.getProperty(Constants.WRONG_EMAIL));
			}			
			String customerEmail = objMap.get(Constants.CUSTOMER_EMAIL);
			
			//findAndHoldSeats()
			SeatHold objSeatHold = ticketService.findAndHoldSeats(numSeats, minLevelOption, maxLevelOption, customerEmail);
			
			returnObject = objSeatHold;			

			break;
		}
		case 3: {
			//customerEmail
			if(!validator.validEmail(objMap.get(Constants.CUSTOMER_EMAIL_RESERVED))) {
				throw new ServiceException(props.getProperty(Constants.WRONG_EMAIL));
			}
			String  customerEmail = objMap.get(Constants.CUSTOMER_EMAIL_RESERVED);
			
			//seatHoldId
			if(!validator.validInteger(objMap.get(Constants.SEAT_HOLD_ID))) {
				throw new ServiceException(props.getProperty(Constants.WRONG_NUMSEATS));
			}
			int seatHoldId = Integer.valueOf(objMap.get(Constants.SEAT_HOLD_ID));

			String reservationCode = ticketService.reserveSeats(seatHoldId, customerEmail);
			returnObject = reservationCode;
			break;
		}
		default: {
			throw new ServiceException(props.getProperty(Constants.WRONG_OPTION));
		}

		}

		return returnObject;
	}

}
