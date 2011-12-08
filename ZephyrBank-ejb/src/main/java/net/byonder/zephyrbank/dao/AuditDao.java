package net.byonder.zephyrbank.dao;

import javax.ejb.Local;

import net.byonder.zephyrbank.model.Audit;

/**
 * @author jvdgriendt
 *
 */
@Local
public interface AuditDao extends BaseDao<Audit> {

}
