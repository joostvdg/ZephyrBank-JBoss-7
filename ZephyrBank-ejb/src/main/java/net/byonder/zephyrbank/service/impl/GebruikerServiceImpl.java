package net.byonder.zephyrbank.service.impl;

import java.util.List;

import net.byonder.zephyrbank.service.GebruikerService;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import net.byonder.zephyrbank.dao.GebruikerDao;
import net.byonder.zephyrbank.model.Gebruiker;

/**
 * 
 * @author jvdgriendt
 */
@Stateless(mappedName="gebruikerService", name="gebruikerService")
public class GebruikerServiceImpl implements GebruikerService {

	@EJB(name="gebruikerDao")
	private GebruikerDao gebruikerDao;
	
	/** {@inheritDoc} */
	@Override
	public List<Gebruiker> geefAlleGebruikers() {
		return gebruikerDao.vindAlle();
	}

	/** {@inheritDoc} */
	@Override
	public void nieuweGebruiker(Gebruiker gebruiker) {
		gebruikerDao.bewaar(gebruiker);
	}

}
