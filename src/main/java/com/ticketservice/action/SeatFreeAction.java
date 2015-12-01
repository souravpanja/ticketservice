package com.ticketservice.action;
/**
 * 
 */

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.ticketservice.bean.LevelVO;
import com.ticketservice.bean.SeatDetails;
import com.ticketservice.util.Constants;

/**
 * @author SOURAV
 *
 */
/**
 * @author SOURAV
 *
 */
public class SeatFreeAction extends TicketOperationImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6359366127640109060L;

	/**
	 * Method to get the available seats
	 * venueLevel : Option parameter for LevelId
	 * return number of available seats
	 */
	public int numSeatsAvailable(Optional<Integer> venueLevel) {

		Set<Integer> keys = objTransDataMap.keySet();

		int noOfAvailableSeats = 0;
		for(Integer key : keys) {
			LevelVO objLevelVO = objTransDataMap.get(key);
			List<SeatDetails> lstSeatDetails = objLevelVO.getLstSeatDetails();
			
			if(!venueLevel.isPresent()) {				
				for(SeatDetails objSeatDetails : lstSeatDetails) {
					if( objSeatDetails.getStatus().equals(Constants.FREE_STATUS) ) {
						noOfAvailableSeats++;
					}
				}

			} else if(venueLevel.get().equals(key)){
				noOfAvailableSeats = 0;
				for(SeatDetails objSeatDetails : lstSeatDetails) {
					if( objSeatDetails.getStatus().equals(Constants.FREE_STATUS) ) {
						noOfAvailableSeats++;
					}
				}
				break;
			}
		}

		return noOfAvailableSeats;

	}
}
