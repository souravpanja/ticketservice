package com.ticketservice.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.ticketservice.bean.LevelVO;
import com.ticketservice.bean.SeatDetails;
import com.ticketservice.exception.ServiceException;
import com.ticketservice.util.Constants;

/**
 * @author SOURAV
 *
 */
public class SeatReserveAction extends TicketOperationImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6862402818802853755L;

	public String reserveSeats(int seatHoldId, String customerEmail) throws ServiceException {
		//objTicketServiceUtil.displayTransMap(objTransDataMap);
		
		Set<Integer> keys = objTransDataMap.keySet();
		
		boolean seatHoldExist = false;
		for(Integer key : keys) {
			boolean seatHoldExistInLevel = false;
			LevelVO objLevelVO = objTransDataMap.get(key);

			List<SeatDetails> lstSeatDetails = objLevelVO.getLstSeatDetails();
			List<SeatDetails> newLstSeatDetails = new ArrayList<SeatDetails>();

			for(SeatDetails objSeatDetails : lstSeatDetails) {
				if( objSeatDetails.getOperationId() == seatHoldId && objSeatDetails.getCustomerEmail().contentEquals(customerEmail)
						&& objSeatDetails.getStatus().equals(Constants.HOLD_STATUS)) {
					seatHoldExistInLevel = true;
					//objSeatDetails.setOperationId(seatReservedId);
					objSeatDetails.setCustomerEmail(customerEmail);
					objSeatDetails.setStatus(Constants.RESERVED_STATUS);
				}
				newLstSeatDetails.add(objSeatDetails);
			}
			//System.out.println("newLstSeatDetails"+newLstSeatDetails);
			
			if(seatHoldExistInLevel) {
				objLevelVO.setLstSeatDetails(newLstSeatDetails);
				objTransDataMap.replace(key, objLevelVO);
				seatHoldExist = true;
			}
			
		}
		if(!seatHoldExist) {
			throw new ServiceException(props.getProperty(Constants.NO_HOLD_SEATS));
		}
		//objTicketServiceUtil.displayTransMap(objTransDataMap);
		return objTicketServiceUtil.generateReservationCode();
	}

}
