package net.byonder.zephyrbank.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * @author jvdgriendt
 *
 */
public class FacesUtil {

	
	/**
	 * @param berichtInhoud
	 */
	public static void addInfoMessage(String berichtInhoud){
		addMessage(FacesMessage.SEVERITY_INFO, berichtInhoud);
	}
	
	/**
	 * @param berichtInhoud
	 */
	public static void addErrorMessage(String berichtInhoud){
		addMessage(FacesMessage.SEVERITY_ERROR, berichtInhoud);
	}
	
	/**
	 * @param berichtInhoud
	 */
	public static void addWarningMessage(String berichtInhoud){
		addMessage(FacesMessage.SEVERITY_WARN, berichtInhoud);
	}
	
	/**
	 * @param severity
	 * @param berichtInhoud
	 */
	private static void addMessage(FacesMessage.Severity severity, String berichtInhoud ){
		FacesMessage message = new FacesMessage(severity  ,berichtInhoud, berichtInhoud);
		FacesContext.getCurrentInstance().addMessage("", message);
	}
}
