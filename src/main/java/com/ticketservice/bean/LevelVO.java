/**
 * 
 */
package com.ticketservice.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author SOURAV
 *
 */
public class LevelVO extends Level implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5429679428688129676L;
	
	private int seatsInLevel;
	
	private List<SeatDetails> lstSeatDetails;
	
	public LevelVO() {}
	
	public LevelVO(Level objVenueDetails) {
		
		this.setLevelId(objVenueDetails.getLevelId());
		this.setLevelName(objVenueDetails.getLevelName());
		this.setPrice(objVenueDetails.getPrice());
		this.setRows(objVenueDetails.getRows());
		this.setSeatsInRow(objVenueDetails.getSeatsInRow());		
	}
		
	/**
	 * @return the seatsInLevel
	 */
	public int getSeatsInLevel() {
		return seatsInLevel;
	}

	/**
	 * @param seatsInLevel the seatsInLevel to set
	 */
	public void setSeatsInLevel(int seatsInLevel) {
		this.seatsInLevel = seatsInLevel;
	}

	/**
	 * @return the lstSeatDetails
	 */
	public List<SeatDetails> getLstSeatDetails() {
		return lstSeatDetails;
	}

	/**
	 * @param lstSeatDetails the lstSeatDetails to set
	 */
	public void setLstSeatDetails(List<SeatDetails> lstSeatDetails) {
		this.lstSeatDetails = lstSeatDetails;
	}	
	
}