/**
 * 
 */
package com.ticketservice.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ticketservice.bean.Level;
import com.ticketservice.bean.LevelVO;
import com.ticketservice.bean.SeatDetails;
import com.ticketservice.util.Constants;

/**
 * @author SOURAV
 *
 */
public class TransDataMap {

	private static Map<Integer, LevelVO> transDataMap = null;

	private static boolean isDirty = false;

	private TransDataMap() {};

	/**
	 * @return the venuDetailsMap
	 */
	public static Map<Integer, LevelVO> getTransDetailsMap() {
		if(transDataMap == null) {
			transDataMap = Collections.synchronizedMap(new HashMap<Integer, LevelVO>());
			transDataMap = initiateSeatDetails(transDataMap);
		}
		return transDataMap;
	}

	private static Map<Integer, LevelVO> initiateSeatDetails(Map<Integer, LevelVO> transDataMap) {
		Map<Integer, Level> objLevelsMap = VenueDetailsMap.getLebelsMap();

		Set<Integer> keys = objLevelsMap.keySet();
		transDataMap = new HashMap<Integer, LevelVO>();
		for(Integer key : keys) {
			Level level = objLevelsMap.get(key);

			LevelVO levelVO = new LevelVO(level);
			int levelId = level.getLevelId();
			//Initiate seat details
			int noOfRows = level.getRows();
			int noOfSeatsinRow = level.getSeatsInRow();
			int seatsInLevel = noOfRows * noOfSeatsinRow;

			levelVO.setSeatsInLevel(seatsInLevel);

			List<SeatDetails> lstSeatDetails = new ArrayList<SeatDetails>();

			for(int countRow=1; countRow <= noOfRows; countRow++) {
				for(int countSeat=1; countSeat <= noOfSeatsinRow; countSeat++ ) {
					SeatDetails objSeatDetails = new SeatDetails();
					String seatId = Constants.LEVEL_CONST+Integer.toString(level.getLevelId())+Constants.ROW_CONST
							+ countRow + Constants.SEAT_CONST+countSeat;
					//System.out.println("seatId:"+seatId);
					objSeatDetails.setSeatId(seatId);
					objSeatDetails.setLevelId(levelId);
					objSeatDetails.setStatus(Constants.FREE_STATUS); //please uncomment
					// Below portion added for unit test, let it be commented 
					/*if(countRow + countSeat == 5) { 
						objSeatDetails.setStatus(Constants.HOLD_STATUS);
						objSeatDetails.setCustomerEmail("sp@hotmail.com");
						objSeatDetails.setOperationId(113);
					}*/
					lstSeatDetails.add(objSeatDetails);
				}

			}

			levelVO.setLstSeatDetails(lstSeatDetails);
			transDataMap.put(key, levelVO);
		}
		return transDataMap;
	}

	/**
	 * @return the transDataMap
	 *//*
	public static Map<Integer, VenueTransDetails> getTransDataMap() {
		return transDataMap;
	}*/

	/**
	 * @param transDataMap the transDataMap to set
	 */
	public static void setTransDataMap(Map<Integer, LevelVO> transDataMap) {
		if(isDirty == true) {
			TransDataMap.transDataMap = transDataMap;
		}
	}

	/**
	 * @return the isDirty
	 */
	public static boolean isDirty() {
		return isDirty;
	}

	/**
	 * @param isDirty the isDirty to set
	 */
	public static void setDirty(boolean isDirty) {
		TransDataMap.isDirty = isDirty;
	}



}
