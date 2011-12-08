package net.byonder.zephyrbank.bean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import net.byonder.zephyrbank.model.Audit;
import net.byonder.zephyrbank.service.AuditService;

/**
 * @author jvdgriendt
 *
 */
@Named(value="auditBean")
@SessionScoped
public class AuditBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB(name="auditService")
	AuditService auditService;
	
	private List<Audit> auditRegels;
	
	public List<Audit> getAuditRegels(){
		auditRegels = auditService.geefAlleAuditRegels();
		return auditRegels;
	}

}
