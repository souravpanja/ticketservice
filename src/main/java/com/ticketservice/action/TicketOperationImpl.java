package com.ticketservice.action;

import java.util.Map;

import com.ticketservice.bean.LevelVO;
import com.ticketservice.data.TransDataMap;
import com.ticketservice.util.Constants;
import com.ticketservice.util.PropertiesCache;
import com.ticketservice.util.TicketServiceUtil;

public abstract class TicketOperationImpl implements TicketOperation {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6346102099822066855L;
	
	static int seatHoldId = Constants.STARTING_ID;
	
	Map<Integer, LevelVO> objTransDataMap = TransDataMap.getTransDetailsMap();
	TicketServiceUtil objTicketServiceUtil = new TicketServiceUtil();
	PropertiesCache props = PropertiesCache.getInstance();
}
