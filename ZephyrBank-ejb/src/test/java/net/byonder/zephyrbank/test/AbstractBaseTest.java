package net.byonder.zephyrbank.test;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;

/**
 * @author jvdgriendt
 *
 */
public abstract class AbstractBaseTest {

	private EntityManagerHolder entityManagerHolder;
	
	/**
	 * Entity Manager voor de integratie testen.
	 */
	protected EntityManager entityManager;
	
	@Before
	public void setUp() throws Exception{
		entityManagerHolder = new EntityManagerHolder();
		entityManagerHolder.start();
		entityManager = entityManagerHolder.getEntityManager();
	}
	
	@After
	public void tearDown() throws Exception{
		entityManagerHolder.stop();
	}
	
	/**
	 * Commit de changes en schoon de cache op.
	 */
	protected void flushAndClear(){
		entityManager.flush();
		entityManager.clear();
	}
	
}
