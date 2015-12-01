package com.ticketservice.bean;

import java.io.Serializable;

import com.ticketservice.action.TicketOperationImpl;

public class LevelOperation implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1212362799650548370L;
	
	
	private TicketOperationImpl ticketOperationImpl;
	private LevelVO venueTransDetails;
	/**
	 * @return the ticketOperationImpl
	 */
	public TicketOperationImpl getTicketOperationImpl() {
		return ticketOperationImpl;
	}
	/**
	 * @param ticketOperationImpl the ticketOperationImpl to set
	 */
	public void setTicketOperationImpl(TicketOperationImpl ticketOperationImpl) {
		this.ticketOperationImpl = ticketOperationImpl;
	}
	/**
	 * @return the venueTransDetails
	 */
	public LevelVO getVenueTransDetails() {
		return venueTransDetails;
	}
	/**
	 * @param venueTransDetails the venueTransDetails to set
	 */
	public void setVenueTransDetails(LevelVO venueTransDetails) {
		this.venueTransDetails = venueTransDetails;
	}
	
	
}
