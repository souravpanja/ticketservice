package com.ticketservice.util;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import com.ticketservice.bean.LevelVO;
import com.ticketservice.bean.SeatDetails;

public class TicketServiceUtil {

	public Set<Integer> calculateLavels(Optional<Integer> minLevel, Optional<Integer> maxLevel, Set<Integer> keys) {

		Set<Integer> newKeys =  new HashSet<Integer>();

		for(Integer key : keys) {

			//int keyValue = key.intValue();

			if(!minLevel.isPresent() && !maxLevel.isPresent()) {
				newKeys.add(key);
			} else if(minLevel.isPresent() && !maxLevel.isPresent()) {
				if (key >= minLevel.get().intValue()) {
					newKeys.add(key);
				}
			} else if(!minLevel.isPresent() && maxLevel.isPresent()) {
				if (key <= maxLevel.get().intValue()) {
					newKeys.add(key);
				}
			} else if(minLevel.isPresent() && maxLevel.isPresent()) {
				if (key >= minLevel.get().intValue() && key <= maxLevel.get().intValue()) {
					newKeys.add(key);
				}
			}
		}

		return newKeys;
	}

	public int getId(int previousId) {
		AtomicInteger autoInt = new AtomicInteger(previousId);
		return autoInt.incrementAndGet();
	}
	
	public Optional<Integer> getOptionalInteger(String strInt) {
		Optional<Integer> strIntOptional = Optional.ofNullable(null);
		if(strInt != null && !"".equals(strInt)) {
			strIntOptional = Optional.of(Integer.valueOf(strInt));
		}
		return strIntOptional;
	}

	public long getTimeStamp() {

		return System.currentTimeMillis();

	}

	public String generateReservationCode() {
		
		UUID uniqueKey = UUID.randomUUID();
		return uniqueKey.toString().replaceAll("-", "");
		/*long currMills = System.currentTimeMillis();
		System.
		return Long.toString(currMills);*/

		/*public static String generateRandomString(int length) {
		      char[] values = {'a','b','c','d','e','f','g','h','i','j',
		               'k','l','m','n','o','p','q','r','s','t',
		               'u','v','w','x','y','z','0','1','2','3',
		               '4','5','6','7','8','9'};

		      String out = "";

		      for (int i=0;i<length;i++) {
		          int idx=random.nextInt(values.length);
		          out += values[idx];
		      }
		      return out;
		    }*/
	}

	public void displayTransMap(Map<Integer, LevelVO> transDataMap) {
		/*Set<Integer> aSet = transDataMap.keySet();
		for(Integer key : aSet) {
			System.out.println();
			System.out.print("key "+key);
			LevelVO objLevelVO = transDataMap.get(key);
			System.out.print("\t Id"+objLevelVO.getLevelId());
			List<SeatDetails> lst = objLevelVO.getLstSeatDetails();
			for(SeatDetails objSeatDetails:lst) {
				//if(objSeatDetails.equals(Constants.HOLD_STATUS)) {
					System.out.println();
					System.out.print("LevelId:"+objSeatDetails.getLevelId());
					System.out.print("\tOperationId:"+objSeatDetails.getOperationId());
					System.out.print("\tSeatId:"+objSeatDetails.getSeatId());
					System.out.print("\tStatus:"+objSeatDetails.getStatus());
					System.out.print("\tCustomerEmail:"+objSeatDetails.getCustomerEmail());
				//}
				
			}
		}*/
	}
	
	public void displaySeatDeatails(SeatDetails obj) {
		System.out.println("**********");
		System.out.print(obj.getLevelId());
		System.out.print("\t"+obj.getSeatId());
		System.out.print("\t"+obj.getStatus());
		System.out.print("\t"+obj.getTimeStamp());
		System.out.println("**********");
		
	}
}
