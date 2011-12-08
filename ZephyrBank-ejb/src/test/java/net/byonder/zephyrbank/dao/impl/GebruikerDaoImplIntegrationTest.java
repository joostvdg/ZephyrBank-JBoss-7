package net.byonder.zephyrbank.dao.impl;

import java.util.List;

import junit.framework.Assert;
import net.byonder.zephyrbank.model.Gebruiker;
import net.byonder.zephyrbank.test.AbstractBaseTest;

import org.junit.Before;
import org.junit.Test;

/**
 * @author jvdgriendt
 *
 */
public class GebruikerDaoImplIntegrationTest extends AbstractBaseTest {
	
	private GebruikerDaoImpl gebruikerDaoImpl;
	
	@Before
	public void setUp() throws Exception{
		super.setUp();
		AuditDaoImpl auditDaoImpl = new AuditDaoImpl();
		auditDaoImpl.entityManager = entityManager;
		Assert.assertNotNull(auditDaoImpl);
		gebruikerDaoImpl = new GebruikerDaoImpl();
		Assert.assertNotNull(gebruikerDaoImpl);
		gebruikerDaoImpl.entityManager = entityManager;
		gebruikerDaoImpl.auditDao = auditDaoImpl;
		
	}
	
	@Test
	public void testNieuweGebruiker(){
		String voornaam = "A";
		String tussenVoegsel = "B";
		String achternaam = "C";
		Gebruiker gebruiker = new Gebruiker();
		gebruiker.setVoorNaam(voornaam);
		gebruiker.setTussenVoegsel(tussenVoegsel);
		gebruiker.setAchterNaam(achternaam);
		
		this.gebruikerDaoImpl.bewaar(gebruiker);
		flushAndClear();
		
		List<Gebruiker> gebruikers =  gebruikerDaoImpl.vindAlle();
		Assert.assertEquals(gebruikers.size(), 1);
		Assert.assertTrue(gebruikers.contains(gebruiker));
	}

}
