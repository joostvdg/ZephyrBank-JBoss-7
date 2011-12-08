package net.byonder.zephyrbank.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author jvdgriendt
 *
 */
public class EntityManagerHolder {
	private static final String PERSISTENCE_UNIT = "zephyrBankTestPU";

	private EntityManagerFactory emFactory;
	private EntityManager entityManager;

	public EntityManagerHolder() {
	}

	/**
	 * Start database, entitymanager en begin een transactie
	 * 
	 * @throws Exception
	 */
	public void start() throws Exception {
		emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);	
		entityManager = emFactory.createEntityManager();
		

		// begin transactie
		entityManager.getTransaction().begin();
	}

	/**
	 * Rollback en ruim op
	 * 
	 * @throws Exception
	 */
	public void stop() throws Exception {
		// rollback
		entityManager.getTransaction().rollback();

		if (entityManager != null) {
			entityManager.close();
		}

		if (emFactory != null) {
			emFactory.close();
		}
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
}
