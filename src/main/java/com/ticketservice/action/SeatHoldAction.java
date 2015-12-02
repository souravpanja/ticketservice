package com.ticketservice.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.ticketservice.bean.LevelVO;
import com.ticketservice.bean.SeatDetails;
import com.ticketservice.bean.SeatHold;
import com.ticketservice.exception.ServiceException;
import com.ticketservice.util.Constants;

/**
 * @author SOURAV
 *
 */

public class SeatHoldAction extends TicketOperationImpl {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7011576487516651635L;
	
	public SeatHold findAndHoldSeats(int numSeats, Optional<Integer> minLevel, Optional<Integer> maxLevel, String customerEmail) throws ServiceException {
		//objTicketServiceUtil.displayTransMap(objTransDataMap);
		
		Set<Integer> keys = objTransDataMap.keySet();

		// Filerting Levels
		Set<Integer> newKeys = objTicketServiceUtil.calculateLavels(minLevel, maxLevel, keys);

		//validate numOfSeats on filtered levels
		this.validateNumSeats(numSeats, newKeys);
		
		//get the Map of Level and noOfSeats to hold
		Map<Integer, Integer> aSeatHoldMap = this.getSeatHoldMap(numSeats, newKeys);
		
		//hold the seats
		SeatHold objSeatHold = this.holdSeats(aSeatHoldMap, customerEmail);
		//objTicketServiceUtil.displayTransMap(objTransDataMap);
		return objSeatHold;
	}

	
	public SeatHold holdSeats(Map<Integer, Integer> aSeatHoldMap, String customerEmail) {
		SeatHold objSeatHold = new SeatHold();
		objSeatHold.setCustomerEmail(customerEmail);
		int previousId = seatHoldId;
		synchronized(this) {
			seatHoldId = objTicketServiceUtil.getId(previousId);
		}
		objSeatHold.setOperationId(seatHoldId);
		
		Set<Integer> keys = aSeatHoldMap.keySet();
		List<SeatDetails> lstSeatDetailsForSeatHold = new ArrayList<SeatDetails>();
		for(Integer key : keys) {
			LevelVO objLevelVO = objTransDataMap.get(key);
			int noOfBeansToModify = aSeatHoldMap.get(key);
			
			List<SeatDetails> lstSeatDetails = objLevelVO.getLstSeatDetails();
			List<SeatDetails> newLstSeatDetails = new ArrayList<SeatDetails>();
			
			for(SeatDetails objSeatDetails : lstSeatDetails) {				
				if(noOfBeansToModify > 0 && objSeatDetails.getStatus().equals(Constants.FREE_STATUS)) {
					objSeatDetails.setOperationId(seatHoldId);
					objSeatDetails.setCustomerEmail(customerEmail);
					objSeatDetails.setStatus(Constants.HOLD_STATUS);
					objSeatDetails.setTimeStamp(System.currentTimeMillis());
					lstSeatDetailsForSeatHold.add(objSeatDetails);
					noOfBeansToModify--;
				}
				newLstSeatDetails.add(objSeatDetails);				
			}
			objLevelVO.setLstSeatDetails(newLstSeatDetails);
			
			objTransDataMap.replace(key, objLevelVO);
		}
		
		objSeatHold.setSeatDetails(lstSeatDetailsForSeatHold);
		return objSeatHold;
	}
	
	// Get the number of available seats from defined Level
	public Map<Integer, Integer> getSeatHoldMap(int numSeats, Set<Integer> newKeys) throws ServiceException {
		Map<Integer, Integer> aSeatHoldMap = new HashMap<Integer, Integer>();
		int remainingSeats = numSeats;
		for(Integer key : newKeys) {
			int noOfAvailableSeats = 0;
			LevelVO objLevelVO = objTransDataMap.get(key);
			//noOfAvailableSeats = objLevelVO.getNoOfFreeSeats();
			List<SeatDetails> lstSeatDetails = objLevelVO.getLstSeatDetails();
			for(SeatDetails objSeatDetails : lstSeatDetails) {
				if( objSeatDetails.getStatus().equals(Constants.FREE_STATUS) ) {
					noOfAvailableSeats++;
				}
			}
			
			if(remainingSeats > noOfAvailableSeats) {
				aSeatHoldMap.put(key, noOfAvailableSeats);
				remainingSeats = remainingSeats - noOfAvailableSeats;
			} else if(remainingSeats <= noOfAvailableSeats) {
				aSeatHoldMap.put(key, remainingSeats);
				break;
			}
		}
		//System.out.println("aSeatHoldMap:"+aSeatHoldMap);
		return aSeatHoldMap;

	}
	
	public void validateNumSeats(int numSeats, Set<Integer> newKeys) throws ServiceException {
		int totalNoOfAvailableSeats = 0;
		//System.out.println(newKeys);
		for(Integer key : newKeys) {
			LevelVO objLevelVO = objTransDataMap.get(key);
			List<SeatDetails> lstSeatDetails = objLevelVO.getLstSeatDetails();
			int noOfAvailableSeatsinLevel = 0;
			for(SeatDetails objSeatDetails : lstSeatDetails) {
				if( objSeatDetails.getStatus().equals(Constants.FREE_STATUS) ) {
					noOfAvailableSeatsinLevel++;
				}
			}
			totalNoOfAvailableSeats =  totalNoOfAvailableSeats + noOfAvailableSeatsinLevel;			
		}
		
		if(totalNoOfAvailableSeats == 0) {
			throw new ServiceException(props.getProperty(Constants.NO_AVAILABLE_SEATS));
		}

		if(totalNoOfAvailableSeats < numSeats) {
			throw new ServiceException(props.getProperty(Constants.REQ_SEATS_LARGER)+":"+totalNoOfAvailableSeats);
		}
	}
	
	
	
}
