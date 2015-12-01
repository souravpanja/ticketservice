package com.ticketservice.service;

import java.util.Optional;

import com.ticketservice.bean.SeatHold;
import com.ticketservice.exception.ServiceException;

/**
 * @author SOURAV
 *
 */
public interface TicketService {
	
	int numSeatsAvailable(Optional<Integer> venueLevel) throws ServiceException ;
	
	SeatHold findAndHoldSeats(int numSeats, Optional<Integer> minLevel, Optional<Integer> maxLevel, String customerEmail) throws ServiceException ; 
	
	String reserveSeats(int seatHoldId, String customerEmail) throws ServiceException;
}
