package net.byonder.zephyrbank.service;

import java.util.List;

import javax.ejb.Local;

import net.byonder.zephyrbank.exceptions.OnvoldoendeSaldoExceptie;
import net.byonder.zephyrbank.model.Rekening;
import net.byonder.zephyrbank.model.SpaarRekening;
import net.byonder.zephyrbank.value.GebruikerNaam;

/**
 * @author jvdgriendt
 *
 */
@Local
public interface RekeningService {

	/**
	 * @param rekening
	 */
	void nieuweRekening(Rekening rekening, GebruikerNaam gebruikerPlaceHolder);
	
	/**
	 * @return
	 */
	List<Rekening> geefAlleRekening();
	
	/**
	 * @param mutatie
	 * @param vanRekening
	 * @param naarRekening
	 */
	void overboeking(float mutatie, Rekening vanRekening, Rekening naarRekening) throws OnvoldoendeSaldoExceptie ;
	
	/**
	 * @param id
	 * @return
	 */
	Rekening haalRegekeningOpViaId(long id);

	/**
	 * @param rekening
	 */
	void updateRekening(Rekening rekening);
	
	/**
	 * Keert de opgebouwde rente van een spaarrekening uit op een kreditrekening.
	 * 
	 * @param spaarRekening de spaarrekening met de opgebouwde rente
	 * @param rekening de rekening die de uitkeer krijgt gestort
	 */
	void keerRenteUit(SpaarRekening spaarRekening, Rekening rekening);

	/**
	 * Update de rente van een spaarrekening.
	 * 
	 * @param rekening de spaarrekening in kwestie
	 */
	void updateRente(SpaarRekening rekening);
}
