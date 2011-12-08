package net.byonder.zephyrbank.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.log4j.Logger;

import net.byonder.zephyrbank.dao.GebruikerDao;
import net.byonder.zephyrbank.dao.RekeningDao;
import net.byonder.zephyrbank.exceptions.OnvoldoendeSaldoExceptie;
import net.byonder.zephyrbank.model.Gebruiker;
import net.byonder.zephyrbank.model.Rekening;
import net.byonder.zephyrbank.model.SpaarRekening;
import net.byonder.zephyrbank.model.Transactie;
import net.byonder.zephyrbank.service.RekeningService;
import net.byonder.zephyrbank.value.GebruikerNaam;

/**
 * @author jvdgriendt
 *
 */
@Stateless(name="rekeningService", mappedName="rekeningService")
public class RekeningServiceImpl implements RekeningService{
	
	private static final Logger LOG = Logger.getLogger(RekeningServiceImpl.class);
	
	@EJB 
	RekeningDao rekeningDao;
	
	@EJB 
	GebruikerDao gebruikerDao;
		
	
	@Override
	public void nieuweRekening(Rekening rekening, GebruikerNaam gebruikerNaam) {
		Gebruiker eigenaar = gebruikerDao.vindGebruikerViaNaam(gebruikerNaam);
		if(eigenaar == null){
			eigenaar = new Gebruiker();
			eigenaar.setVoorNaam(gebruikerNaam.getVoorNaam());
			eigenaar.setTussenVoegsel(gebruikerNaam.getTussenVoegsel());
			eigenaar.setAchterNaam(gebruikerNaam.getAchterNaam());
		}
		rekening.setEigenaar(eigenaar);
		rekeningDao.bewaar(rekening);
	}

	@Override
	public List<Rekening> geefAlleRekening() {
		return rekeningDao.vindAlle();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void overboeking(float mutatie, Rekening vanRekening, Rekening naarRekening) throws OnvoldoendeSaldoExceptie {
		naarRekening.muteerSaldo(mutatie);
		float negatiefMutatie = (mutatie * (-1));
		vanRekening.muteerSaldo(negatiefMutatie);
		Transactie transactie = new Transactie(vanRekening, naarRekening, mutatie);
		vanRekening.voegMutatieToe(transactie);
		naarRekening.voegMutatieToe(transactie);
		updateRekening(vanRekening);
		updateRekening(naarRekening);
	}

	@Override
	public Rekening haalRegekeningOpViaId(long id) {
		return rekeningDao.haalOp(id);
	}

	@Override
	public void updateRekening(Rekening rekening) {
		rekeningDao.update(rekening);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void keerRenteUit(SpaarRekening spaarRekening, Rekening rekening) {
		try {
			float opgebouwdeRente = spaarRekening.keerRenteUit();
			rekening.muteerSaldo(opgebouwdeRente);
			Transactie transactie = new Transactie(spaarRekening, rekening, opgebouwdeRente);
			rekening.voegMutatieToe(transactie);
			LOG.info("Rente succesvol uitgekeerd" );
		} catch (Exception e){
			LOG.error(String.format("Kon rente niet uitkeren want: %s", e));
		}
		
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void updateRente(SpaarRekening rekening) {
		float rente = berekenRente(rekening);
		rekening.updateRente(rente);
		updateRekening(rekening);
	}

	/**
	 * Berekend de rente van een spaarrekening.
	 * 
	 * @param rekening
	 * @return
	 */
	private float berekenRente(SpaarRekening rekening) {
		float rente = (rekening.getSaldo() * 0.04f);
		LOG.debug(String.format("Rente berekenen voor Rekening %s, rente is %f", rekening, rente));
		return rente;
	}

}
