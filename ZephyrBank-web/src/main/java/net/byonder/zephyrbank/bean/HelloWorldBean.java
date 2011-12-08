package net.byonder.zephyrbank.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class HelloWorldBean {

	private final String welcomeMessage = "HelloWorld!";

	
	/**
	 * @return the welcomeMessage
	 */
	public String getWelcomeMessage() {
		return welcomeMessage;
	} 
	
}
