package net.byonder.zephyrbank.service;

import java.util.List;

import javax.ejb.Local;

import net.byonder.zephyrbank.model.Audit;

@Local
public interface AuditService {

	/**
	 * @return
	 */
	List<Audit> geefAlleAuditRegels();
}
