package com.ticketservice.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import com.ticketservice.bean.LevelVO;
import com.ticketservice.bean.SeatDetails;
import com.ticketservice.util.Constants;


public class FreeHoldTask extends TicketOperationImpl{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8458047668280140020L;
	
	Timer timer;
	
	public FreeHoldTask(){
		timer = new Timer();
		timer.scheduleAtFixedRate(new ActualTask(), Long.parseLong(props.getProperty(Constants.INITIAL_DELAY_SEC)) * 1000, // initial value
				Long.parseLong(props.getProperty(Constants.SUBSEQUENT_RATE_SEC)) * 1000); // scheduled time
	}
	
	/*
	 * Class of thread to do the expiration operation on hold seats
	 */
	class ActualTask extends TimerTask {
		public void run() {
			objTicketServiceUtil.displayTransMap(objTransDataMap);
			
			Set<Integer> keys = objTransDataMap.keySet();
			
			for(Integer key : keys) {
				boolean seatHoldIdExist = false;
				LevelVO objLevelVO = objTransDataMap.get(key);

				List<SeatDetails> lstSeatDetails = objLevelVO.getLstSeatDetails();
				List<SeatDetails> newLstSeatDetails = new ArrayList<SeatDetails>();
				for(SeatDetails objSeatDetails : lstSeatDetails) {
					//System.out.println("SeatId:"+objSeatDetails.getSeatId()+"   timeStamp:"+objSeatDetails.getTimeStamp()+"   Status:"+objSeatDetails.getStatus());
					if( (objSeatDetails.getStatus().equals(Constants.HOLD_STATUS)) && 
							( (System.currentTimeMillis() - objSeatDetails.getTimeStamp()) >= (Long.parseLong(props.getProperty(Constants.HOLD_EXPIRED_IN_SEC)) * 1000) ) ) {
						seatHoldIdExist = true;
						objSeatDetails.setStatus(Constants.FREE_STATUS);
						objSeatDetails.setTimeStamp(System.currentTimeMillis());
						objSeatDetails.setOperationId(0);
						objSeatDetails.setCustomerEmail(null);
					}
					newLstSeatDetails.add(objSeatDetails);
				}
				if(seatHoldIdExist) {
					objLevelVO.setLstSeatDetails(newLstSeatDetails);
					objTransDataMap.replace(key, objLevelVO);
				}
				
			}
			//objTicketServiceUtil.displayTransMap(objTransDataMap);
		}
	}
}
