package net.byonder.zephyrbank.bean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import net.byonder.zephyrbank.exceptions.OnvoldoendeSaldoExceptie;
import net.byonder.zephyrbank.model.Rekening;
import net.byonder.zephyrbank.service.RekeningService;
import net.byonder.zephyrbank.util.FacesUtil;

/**
 * @author jvdgriendt
 * 
 */
@Named(value = "boekingenBean")
@SessionScoped
public class BoekingenBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB(name="rekeningService")
	RekeningService rekeningService;

	private long rekeningVan;

	private long rekeningNaar;

	private float bedrag;

	/**
	 * @return the rekeningVan
	 */
	public long getRekeningVan() {
		return rekeningVan;
	}

	/**
	 * @param rekeningVan
	 *            the rekeningVan to set
	 */
	public void setRekeningVan(long rekeningVan) {
		this.rekeningVan = rekeningVan;
	}

	/**
	 * @return the rekeningNaar
	 */
	public long getRekeningNaar() {
		return rekeningNaar;
	}

	/**
	 * @param rekeningNaar
	 *            the rekeningNaar to set
	 */
	public void setRekeningNaar(long rekeningNaar) {
		this.rekeningNaar = rekeningNaar;
	}

	/**
	 * @return the bedrag
	 */
	public float getBedrag() {
		return bedrag;
	}

	/**
	 * @param bedrag
	 *            the bedrag to set
	 */
	public void setBedrag(float bedrag) {
		this.bedrag = bedrag;
	}

	public void opnemen() {
		float bedragNegatief = bedrag * (-1);
		try {
			Rekening rekening = rekeningService.haalRegekeningOpViaId(rekeningVan);
			rekening.muteerSaldo(bedragNegatief);
			rekeningService.updateRekening(rekening);
			FacesUtil.addInfoMessage("Bedrag is opgenomen van de rekening");
		} catch (OnvoldoendeSaldoExceptie e) {
			FacesUtil.addWarningMessage("Rekening heeft onvoldoende saldo om de mutatie uit te voeren.");
		}
	}

	public void storten() {
		try {
			Rekening rekening = rekeningService.haalRegekeningOpViaId(rekeningVan);
			rekening.muteerSaldo(bedrag);
			rekeningService.updateRekening(rekening);
			FacesUtil.addInfoMessage("Bedrag is gestort op de rekening");
		} catch (OnvoldoendeSaldoExceptie e) {
			FacesUtil.addWarningMessage("Rekening heeft onvoldoende saldo om de mutatie uit te voeren.");
		}
	}

	public void overboeken() {
		try {
			rekeningService.overboeking(bedrag, rekeningService.haalRegekeningOpViaId(rekeningVan), rekeningService.haalRegekeningOpViaId(rekeningNaar));
			FacesUtil.addInfoMessage("Bedrag is overgeboekt.");
		} catch (OnvoldoendeSaldoExceptie e) {
			FacesUtil.addWarningMessage("Rekening heeft onvoldoende saldo om de mutatie uit te voeren.");
		}
	}

}
