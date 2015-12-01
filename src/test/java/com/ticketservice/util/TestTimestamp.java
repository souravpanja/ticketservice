/**
 * 
 */
package com.ticketservice.util;

/**
 * @author SOURAV
 *
 */
public class TestTimestamp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long firstTime = System.currentTimeMillis();
		try {
			Thread.sleep(2* 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Long secondTime = System.currentTimeMillis();
		Long diffTime = secondTime - firstTime;
		System.out.println("firstTime :"+firstTime);
		System.out.println("secondTime:"+secondTime);
		System.out.println("diffTime :"+diffTime);
	}

}
