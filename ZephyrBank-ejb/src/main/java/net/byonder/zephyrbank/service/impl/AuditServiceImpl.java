package net.byonder.zephyrbank.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import net.byonder.zephyrbank.dao.AuditDao;
import net.byonder.zephyrbank.model.Audit;
import net.byonder.zephyrbank.service.AuditService;

/**
 * @author jvdgriendt
 *
 */
@Stateless(name="auditService", mappedName="auditService")
public class AuditServiceImpl implements AuditService{

	@EJB
	AuditDao auditDao;
	
	@Override
	public List<Audit> geefAlleAuditRegels() {
		return auditDao.vindAlle();
	}

}
