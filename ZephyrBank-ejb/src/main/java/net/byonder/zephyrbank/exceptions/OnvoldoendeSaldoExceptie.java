package net.byonder.zephyrbank.exceptions;

import javax.ejb.ApplicationException;


/**
 *
 * @author jvdgriendt
 */
@ApplicationException
public class OnvoldoendeSaldoExceptie extends Exception{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OnvoldoendeSaldoExceptie(String message) {
        super(message);
    }


    
}
