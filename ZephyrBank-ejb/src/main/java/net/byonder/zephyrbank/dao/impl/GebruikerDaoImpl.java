/**
 * 
 */
package net.byonder.zephyrbank.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.Query;

import net.byonder.zephyrbank.dao.GebruikerDao;
import net.byonder.zephyrbank.model.Gebruiker;
import net.byonder.zephyrbank.value.GebruikerNaam;

/**
 * @author jvdgriendt
 *
 */
@Stateless(name="gebruikerDao", mappedName="gebruikerDao")
public class GebruikerDaoImpl extends AbstractBaseDao<Gebruiker> implements GebruikerDao {

	public GebruikerDaoImpl() {
		super(Gebruiker.class);
	}

	/** {@inheritDoc} */
	@Override
	public Gebruiker vindGebruikerViaNaam(GebruikerNaam gebruikerNaam) {
		Gebruiker gebruiker = null;
		String queryString = String.format("SELECT gebruiker FROM Gebruiker gebruiker ");
		
		if(gebruikerNaamCorrect(gebruikerNaam)){
			queryString += String.format(" WHERE gebruiker.voorNaam = '%s' ", gebruikerNaam.getVoorNaam());
		} else{
			throw new IllegalArgumentException("GebruikerNaam mag niet null of leeg zijn" );
		}
		
		if(notNotNullOrEmpty(gebruikerNaam.getAchterNaam())) 
			queryString += String.format(" AND gebruiker.achterNaam = '%s' ", gebruikerNaam.getAchterNaam());
		
		if(notNotNullOrEmpty(gebruikerNaam.getTussenVoegsel()))
			queryString += String.format(" AND gebruiker.tussenVoegsel = '%s' ", gebruikerNaam.getTussenVoegsel());
		
		Query query = entityManager.createQuery(queryString, Gebruiker.class);
		gebruiker = (Gebruiker) query.getSingleResult();
			
		return gebruiker;
	}

	private boolean gebruikerNaamCorrect(GebruikerNaam gebruikerNaam) {
		return (gebruikerNaam != null) && notNotNullOrEmpty(gebruikerNaam.getVoorNaam());
	}
	
	private boolean notNotNullOrEmpty(String stringToTest){
		return (stringToTest != null) && (!"".equals(stringToTest));
	}

}
