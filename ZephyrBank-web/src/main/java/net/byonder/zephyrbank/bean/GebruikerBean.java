package net.byonder.zephyrbank.bean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import javax.inject.Named;

import net.byonder.zephyrbank.model.Gebruiker;
import net.byonder.zephyrbank.service.GebruikerService;
import net.byonder.zephyrbank.util.FacesUtil;


/**
 * Basic gebruiker Web Bean.
 * 
 * @author jvdgriendt
 *
 */
@SuppressWarnings("serial")
@Named(value="gebruikerBean")
@SessionScoped
public class GebruikerBean implements Serializable {
	
	private List<Gebruiker> gebruikers;
	
	private Gebruiker instance;
	
	@EJB(beanName="gebruikerService")
	GebruikerService gebruikerService;
	
	public GebruikerBean(){
		instance = new Gebruiker();
	}
	
	/**
	 * Slaat via de GebruikerService de Instance gebruiker op.
	 */
	public void slaNieuweGebruikerOp(){
		gebruikerService.nieuweGebruiker(instance);
		instance = null;
		instance = new Gebruiker();
		FacesUtil.addInfoMessage("Opslaan van de gebruiker gelukt!");
	}

	public List<Gebruiker> getGebruikers() {
		gebruikers = gebruikerService.geefAlleGebruikers();
		return gebruikers;
	}

	public void setGebruikers(List<Gebruiker> gebruikers) {
		this.gebruikers = gebruikers;
	}

	public Gebruiker getInstance() {
		return instance;
	}

	public void setInstance(Gebruiker instance) {
		this.instance = instance;
	}

}
