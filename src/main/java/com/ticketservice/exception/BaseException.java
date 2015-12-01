package com.ticketservice.exception;

import com.ticketservice.util.Constants;

/**
 * @author SOURAV
 *
 */
public class BaseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6399214456313321912L;

	public BaseException() {
		// TODO Auto-generated constructor stub
	}

	public BaseException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BaseException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	/**
	 * get the messages back to it's origin cause.
	 */
	@Override
	public String getMessage()
	{
		String retval=Constants.CR;
		retval+=super.getMessage()+Constants.CR;

		Throwable cause = getCause();
		if (cause!=null)
		{
			String message = cause.getMessage();
			if (message!=null)
			{
				retval+=message+Constants.CR;
			}
			else
			{
				// Add with stack trace elements of cause...
				StackTraceElement ste[] = cause.getStackTrace();
				for (int i=ste.length-1;i>=0;i--)
				{
					retval+="	at "+ste[i].getClassName()+"."+ste[i].getMethodName()+" ("+ste[i].getFileName()+":"+ste[i].getLineNumber()+")"+Constants.CR;
				}
			}
		}
		
		return retval;
	}
    
    public String getSuperMessage()
    {
        return super.getMessage();
    }

}
