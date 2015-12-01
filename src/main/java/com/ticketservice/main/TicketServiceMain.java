package com.ticketservice.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ticketservice.action.FreeHoldTask;
import com.ticketservice.bean.SeatDetails;
import com.ticketservice.bean.SeatHold;
import com.ticketservice.exception.ServiceException;
import com.ticketservice.process.RequestProcessor;
import com.ticketservice.util.Constants;
import com.ticketservice.util.TicketServiceUtil;

/**
 * @author SOURAV
 *
 */
public class TicketServiceMain
{
	TicketServiceUtil util = new TicketServiceUtil();
	RequestProcessor objRequestProcessor = new RequestProcessor();
	FreeHoldTask objFreeHoldTask = new FreeHoldTask(); //It is require to initiate the thread for expiration of HOLD object
	//PropertiesCache props = PropertiesCache.getInstance();

	public static void main( String[] args )
	{
		System.out.println("*************Below is the outline for the three and its corresponding number**************");
		System.out.println("********************1 get the number of available seats******************************");
		System.out.println("********************2 to find available and hold seats*******************************");
		System.out.println("********************3 reserve the hold seat for the customer*************************");
		System.out.println("*************************************************************************************");
		
		

		TicketServiceMain objTicketServiceMain = new TicketServiceMain();

		// using InputStreamReader
		BufferedReader reader = null;

		String serviceNo = null;
		boolean isLoop = true;
		do {
			try {
			reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println();
			System.out.println("99 for EXIT");
			System.out.println("Enter your service no(1 or 2 or 3)");
			serviceNo = reader.readLine();
			//System.out.println("Your option is: " + serviceNo);
			//inputMap.put(Constants.OPERATION_OPTION, serviceNo);
			if(serviceNo.equals("99")) {
				isLoop = false;
			} else {
				Map<String, String> inputMap = objTicketServiceMain.enterValuesForOperation(serviceNo, reader);
				//util.displayTransMap(inputMap);
				//System.out.println(inputMap);
				Object objRetrunValue = objTicketServiceMain.accessService(inputMap);
				switch(serviceNo) 
				{
				case "1": {
					Integer objInt = (Integer)objRetrunValue;
					System.out.println("*************************Count Free Seats START************************");
					System.out.println("No of seats available:"+objInt); 
					System.out.println("*************************Count Free Seats END************************");
					break;
				}
				case "2": {
					SeatHold objSeatHold = (SeatHold)objRetrunValue;
					System.out.println("*************************SeatHolD Details START************************");
					System.out.println("****Please RESERVE below seats earliest unless it will be expired*****");
					System.out.println("Seat Hold Id:"+objSeatHold.getOperationId());
					System.out.println("Customer Email:"+objSeatHold.getCustomerEmail());
					List<SeatDetails> aList= objSeatHold.getSeatDetails();

					for(SeatDetails aBean : aList) {
						System.out.println();
						System.out.print("SeatId:"+aBean.getSeatId());
						System.out.print("\t Status:"+aBean.getStatus());
						System.out.print("\t LevelId:"+aBean.getLevelId());
					}
					System.out.println();
					System.out.println("*************************SeatHolD Details END************************");
					break;
				}
				case "3": {
					String reservConfirmationCode = (String)objRetrunValue;
					System.out.println("*************************Reserve Seats START************************");
					System.out.println("Reservation Confirmation Code:"+reservConfirmationCode); 
					System.out.println("*************************Reserve Seats END************************");
					break;
				}
				default: {
					//System.err.println("Wrong option it would be 1 or 2 or 3");
				}
				}
			}

			} catch (IOException ioe) {
				System.out.println(ioe.getMessage());
				//ioe.printStackTrace();
			} catch (ServiceException se){
				System.err.println(se.getMessage());
				//se.printStackTrace();
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
				//ex.printStackTrace();
			} 
		} while(isLoop == true);
		
		if(reader != null) {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		System.exit(0);
	}

	public Object accessService(Map<String, String> objMap) throws ServiceException {
		//RequestProcessor objRequestProcessor = new RequestProcessor();
		Object objResult = objRequestProcessor.getResult(objMap);
		return objResult;
	}

	private Map<String, String> enterValuesForOperation(String choice, BufferedReader reader) throws IOException {
		
		Map<String, String> inputMap = new HashMap<String, String>();
		inputMap.put(Constants.OPERATION_OPTION, choice );
		switch(choice) 
		{
		case "1": {
			//System.out.println("Option 1");
			System.out.println("\t If you don't want to restrict the serch operation please press enter. Below is the optional numeric input parameter for this operation, ");
			System.out.println("\t venuLevel (optional): a numeric venue level identifier to limit the search");
			//System.out.println("\t return the number of tickets available on the provided level");
			reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter venuLevel(optional - press enter for all): ");
			String venuLevel = reader.readLine();
			//System.out.println("venuLevel:"+venuLevel);    	    	
			inputMap.put(Constants.VENUE_LEVEL, venuLevel);
			break;
		}
		case "2": {
			//System.out.println("Option 2");
			System.out.println("\t If you don't want to restrict the hold operation please don't enter the value for below parameters minLevel and maxLevel.");
			System.out.println("Below is the optional numeric input parameter for this operation, ");
			System.out.println("\t numSeats : the number of seats to find and hold");
			System.out.println("\t minLevel (Optional): the minimum venue level");
			System.out.println("\t maxLevel (Optional): the maximum venue level");
			System.out.println("\t customerEmail : the email address of the customer to which the seat hold is assigned");

			reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter numSeats*:");
			String numSeats = reader.readLine();
			inputMap.put(Constants.NUM_SEATS, numSeats);
			//System.out.println("numSeats:"+numSeats);

			reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter minLevel (Optional):");
			String minLevel = reader.readLine();
			inputMap.put(Constants.MIN_LEVEL, minLevel);
			//System.out.println("minLevel:"+minLevel);

			reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter maxLevel (Optional):");
			String maxLevel = reader.readLine();
			inputMap.put(Constants.MAX_LEVEL, maxLevel);
			//System.out.println("maxLevel:"+maxLevel);

			reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter customerEmail: ");
			String customerEmail = reader.readLine();
			inputMap.put(Constants.CUSTOMER_EMAIL, customerEmail);
			//System.out.println("customerEmail:"+customerEmail);

			break;
		}
		case "3": {
			//System.out.println("Option 3");
			System.out.println("\t Below are the input parameters for this operation, ");
			System.out.println("\t seatHoldId : the seat hold identifier");
			System.out.println("\t customerEmail : the email address of the customer to which the seat hold is assigned");
			//System.out.println("\t return a resevation confirmation code");

			reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter seatHoldId*: ");
			String seatHoldId = reader.readLine();
			inputMap.put(Constants.SEAT_HOLD_ID, seatHoldId);
			//System.out.println("seatHoldId:"+seatHoldId);

			reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter customerEmail*: ");
			String customerEmail1 = reader.readLine();
			inputMap.put(Constants.CUSTOMER_EMAIL_RESERVED, customerEmail1);
			//System.out.println("seatHoldId:"+customerEmail1);

			break;
		}
		default: {
			System.err.println("Wrong option it would be 1 or 2 or 3");
		}

		}
		return inputMap;
	}


}
