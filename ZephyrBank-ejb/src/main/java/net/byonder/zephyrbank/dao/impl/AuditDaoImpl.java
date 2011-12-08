package net.byonder.zephyrbank.dao.impl;

import javax.ejb.Stateless;

import net.byonder.zephyrbank.dao.AuditDao;
import net.byonder.zephyrbank.model.Audit;

@Stateless(name="auditDao", mappedName="auditDao")
public class AuditDaoImpl extends AbstractBaseDao<Audit> implements AuditDao{

	public AuditDaoImpl() {
		super(Audit.class);
	}

}
