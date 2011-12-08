package net.byonder.zephyrbank.dao.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.byonder.zephyrbank.dao.AuditDao;
import net.byonder.zephyrbank.dao.BaseDao;
import net.byonder.zephyrbank.model.Audit;

import org.apache.log4j.Logger;

/**
 * Base Dao Implementatie.
 * 
 * @author jvdgriendt
 */
public abstract class AbstractBaseDao<T> implements BaseDao<T> {

	@PersistenceContext(unitName = "ZephyrBank")
	protected EntityManager entityManager;

	@EJB
	AuditDao auditDao;

	private Logger log;

	private final Class<T> clazz;

	protected AbstractBaseDao(Class<T> clazz) {
		this.clazz = clazz;
		this.log = Logger.getLogger(clazz);
	}

	/** {@inheritDoc} */ 
	@Override
	public T haalOp(long id) {
		log.info(String.format("Ophalen van een %s met id %d.", clazz.getSimpleName(), id));
		return entityManager.find(clazz, id);
	}

	/** {@inheritDoc} */
	@Override
	public boolean bewaar(T t) {
		log.info(String.format("Opslaan van een %s.", clazz.getSimpleName()));
		entityManager.persist(t);
		if (t instanceof Audit) {
		} else{
			Audit audit = new Audit();
			audit.setActie("Opslaan Entiteit");
			audit.setDate(new Date());
			audit.setOmschrijving(String.format("Persisteren van object %s.", clazz.getSimpleName()));
			auditDao.bewaar(audit);
		}
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public boolean update(T t) {
		log.info(String.format("Updaten van een %s.", clazz.getSimpleName()));
		entityManager.merge(t);
		if (t instanceof Audit) {
		} else{
			Audit audit = new Audit();
			audit.setActie("Updaten Entiteit");
			audit.setDate(new Date());
			audit.setOmschrijving(String.format("Updaten van object %s.", clazz.getSimpleName()));
			auditDao.bewaar(audit);
		}
		return true;
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> vindAlle() {
		String className = clazz.getSimpleName();
		String classNameLower = clazz.getSimpleName().toLowerCase();
		log.info(String.format("Ophalen van een lijst met alle %s.", className));
		String queryString = String.format("SELECT %s FROM %s %s", classNameLower, className, classNameLower);
		Query query = entityManager.createQuery(queryString);
		List<T> lijst = (List<T>) query.getResultList();
		log.info(String.format("Aantal resultaten gevonden: %d.", lijst.size()));
		return lijst;
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> vindAllePaged(PagingInstructions pagingInstructions) {
		// TODO : nog geen pagingInstructions geimplementeerd
		String className = clazz.getSimpleName();
		String classNameLower = clazz.getSimpleName().toLowerCase();
		log.info(String.format("Ophalen van een lijst met alle %s.", className));
		String queryString = String.format("SELECT %s FROM %s %s", classNameLower, className, classNameLower);
		Query query = entityManager.createQuery(queryString);
		List<T> lijst = (List<T>) query.getResultList();
		log.info(String.format("Aantal resultaten gevonden: %d.", lijst.size()));
		return lijst;
	}

}
