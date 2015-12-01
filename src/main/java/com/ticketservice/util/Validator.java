package com.ticketservice.util;

public class Validator {


	public boolean validOption(String option) {
		boolean validOption = false;
		if(option == null || option.equals("")) {
			return validOption;
		}
		return option.matches(Constants.REGX_OPTION);
	}
	
	public boolean validIntegerOptional(String strInt) {
		if(strInt == null || strInt.equals("")) {
			return true;
		}
		return strInt.matches(Constants.REGX_INTEGER);
	}
	
	public boolean validInteger(String strInt) {
		if(strInt == null || strInt.equals("")) {
			return false;
		}
		return strInt.matches(Constants.REGX_INTEGER);
	}
	
	
	public boolean validEmail(String strEmail) {
		if(strEmail == null || strEmail.equals("")) {
			return false;
		}
		return strEmail.matches(Constants.REGX_EMAIL);
	}
	
	/*public boolean compareMinMaxLevel(int min option) {
		boolean validOption = false;
		if(option == null || option.equals("")) {
			return validOption;
		}
		return option.matches(Constants.REGX_OPTION);
	}*/
	

}
