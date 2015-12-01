package com.ticketservice.bean;

import java.io.Serializable;

/**
 * @author SOURAV
 *
 */

public class Level implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1637956857466072311L;
	
	private int levelId;
	private String levelName;
	private float price;
	private int rows;
	private int seatsInRow;
	/**
	 * @return the levelId
	 */
	public int getLevelId() {
		return levelId;
	}
	/**
	 * @param levelId the levelId to set
	 */
	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}
	/**
	 * @return the levelName
	 */
	public String getLevelName() {
		return levelName;
	}
	/**
	 * @param levelName the levelName to set
	 */
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}
	/**
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}
	/**
	 * @param rows the rows to set
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}
	/**
	 * @return the seatsInRow
	 */
	public int getSeatsInRow() {
		return seatsInRow;
	}
	/**
	 * @param seatsInRow the seatsInRow to set
	 */
	public void setSeatsInRow(int seatsInRow) {
		this.seatsInRow = seatsInRow;
	}
	
	
}
