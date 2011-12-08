package net.byonder.zephyrbank.dao;

import javax.ejb.Local;

import net.byonder.zephyrbank.model.Gebruiker;
import net.byonder.zephyrbank.value.GebruikerNaam;

@Local
public interface GebruikerDao extends BaseDao<Gebruiker> {

	Gebruiker vindGebruikerViaNaam(GebruikerNaam gebruikerNaam);
}
