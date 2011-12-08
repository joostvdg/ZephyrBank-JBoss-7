package net.byonder.zephyrbank.bean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import net.byonder.zephyrbank.model.KredietRekening;
import net.byonder.zephyrbank.model.Rekening;
import net.byonder.zephyrbank.model.SpaarRekening;
import net.byonder.zephyrbank.service.RekeningService;
import net.byonder.zephyrbank.util.FacesUtil;
import net.byonder.zephyrbank.value.GebruikerNaam;

/**
 * @author jvdgriendt
 *
 */
@Named(value="rekeningBean")
@SessionScoped
public class RekeningBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB(name="rekeningService")
	RekeningService rekeningService;	
	
	private GebruikerNaam tempGebruiker;

	private List<Rekening> rekeningen;
	
	private String typeRekening;
	
	private float saldo;
	
	public RekeningBean(){
		tempGebruiker = new GebruikerNaam();
	}
	
	public GebruikerNaam getTempGebruiker(){
		return tempGebruiker;
	}
	
	public List<Rekening> getRekeningen(){
		rekeningen = rekeningService.geefAlleRekening();
		return rekeningen;
	}
	
	
	/**
	 * @return the typeRekening
	 */
	public String getTypeRekening() {
		return typeRekening;
	}

	/**
	 * @param typeRekening the typeRekening to set
	 */
	public void setTypeRekening(String typeRekening) {
		this.typeRekening = typeRekening;
	}

	/**
	 * @return the saldo
	 */
	public float getSaldo() {
		return saldo;
	}

	/**
	 * @param saldo the saldo to set
	 */
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public void nieuweRekening(){
		Rekening rekening = null;
		if (typeRekening.equals("spaar")){
			rekening = new SpaarRekening();
		} else {
			rekening = new KredietRekening(this.saldo, 0.00f);
		}
		try{
			rekeningService.nieuweRekening(rekening, tempGebruiker);
			FacesUtil.addInfoMessage("Opslaan van de Rekening gelukt!");
		} catch (Exception e) {
			FacesUtil.addErrorMessage(String.format("Opslaan van de Rekening niet gelukt, reden; %s", e));
		}
		
	}
	
	
}
