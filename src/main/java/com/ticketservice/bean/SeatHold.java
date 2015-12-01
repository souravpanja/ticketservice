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
public class SeatHold implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5738686865437176050L;

	private int operationId;
	
	private String customerEmail;
	
	private List<SeatDetails> seatDetails;

	/**
	 * @return the operationId
	 */
	public int getOperationId() {
		return operationId;
	}

	/**
	 * @param operationId the operationId to set
	 */
	public void setOperationId(int operationId) {
		this.operationId = operationId;
	}

	/**
	 * @return the customerEmail
	 */
	public String getCustomerEmail() {
		return customerEmail;
	}

	/**
	 * @param customerEmail the customerEmail to set
	 */
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	/**
	 * @return the seatDetails
	 */
	public List<SeatDetails> getSeatDetails() {
		return seatDetails;
	}

	/**
	 * @param seatDetails the seatDetails to set
	 */
	public void setSeatDetails(List<SeatDetails> seatDetails) {
		this.seatDetails = seatDetails;
	}
	
}
