package Listener;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;



import org.apache.log4j.Logger;

/**
 * @author jvdgriendt
 * 
 */
@LocalBean
@Stateless
public class Auditor {

	private static final Logger LOG = Logger.getLogger(Auditor.class);

	@PostPersist
	void postPersist(final Object entity) {
		LOG.info(String.format("Persisteren van object %s.", entity.getClass().getSimpleName()));
	}

	@PostLoad
	void postLoad(final Object entity) {
		if (entity != null) {
			LOG.info(String.format("Laden van object %s.", entity.getClass().getSimpleName()));
		}
	}

	@PostUpdate
	void postUpdate(final Object entity) {
		LOG.info(String.format("Updaten van object %s.", entity.getClass().getSimpleName()));
	}

}
