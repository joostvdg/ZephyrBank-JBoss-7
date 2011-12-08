package net.byonder.zephyrbank.service;

import java.util.List;
import javax.ejb.Local;
import net.byonder.zephyrbank.model.Gebruiker;

/**
 * 
 * @author jvdgriendt
 */
@Local
public interface GebruikerService {

	/**
	 * Geeft een lijst terug van alle beschikbare gebruikers.
	 * 
	 * @return
	 */
	List<Gebruiker> geefAlleGebruikers();

	/**
	 * Zorgt dat een nieuwe gebruiker wordt gepersisteerd.
	 * 
	 * @param gebruiker de gebruiker die voor het eerst opgeslagen dient te worden.
	 */
	void nieuweGebruiker(Gebruiker gebruiker);

}
