package com.ticketservice.service;

import java.util.Optional;

import com.ticketservice.action.SeatFreeAction;
import com.ticketservice.action.SeatHoldAction;
import com.ticketservice.action.SeatReserveAction;
import com.ticketservice.bean.SeatHold;
import com.ticketservice.exception.ServiceException;
import com.ticketservice.util.Constants;
import com.ticketservice.util.PropertiesCache;

/**
 * @author SOURAV
 *
 */
public class TicketServiceImpl implements TicketService {

	SeatFreeAction objSeatFreeAction = new SeatFreeAction();
	SeatHoldAction objSeatHoldAction = new SeatHoldAction();
	SeatReserveAction objSeatReserveAction = new SeatReserveAction();
	PropertiesCache props = PropertiesCache.getInstance();

	public int numSeatsAvailable(Optional<Integer> venueLevel) throws ServiceException {
		return objSeatFreeAction.numSeatsAvailable(venueLevel);
	}

	public SeatHold findAndHoldSeats(int numSeats, Optional<Integer> minLevel, Optional<Integer> maxLevel, String customerEmail) throws ServiceException {
		Optional<Integer> venueLevelOption = Optional.ofNullable(null);
		int totalNoOfAvailableSeats = objSeatFreeAction.numSeatsAvailable(venueLevelOption);
		if(totalNoOfAvailableSeats == 0) {
			throw new ServiceException(props.getProperty(Constants.NO_AVAILABLE_SEATS));
		}

		if(totalNoOfAvailableSeats < numSeats) {
			throw new ServiceException(props.getProperty(Constants.REQ_SEATS_LARGER)+":"+totalNoOfAvailableSeats);
		}
		
		return objSeatHoldAction.findAndHoldSeats(numSeats, minLevel, maxLevel, customerEmail);
	}


	public String reserveSeats(int seatHoldId, String customerEmail) throws ServiceException {
		return objSeatReserveAction.reserveSeats(seatHoldId, customerEmail);
	}



}
