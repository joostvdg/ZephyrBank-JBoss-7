package net.byonder.zephyrbank.service;

import javax.ejb.Local;

import net.byonder.zephyrbank.model.Transactie;

import java.util.List;

/**
 * @author jvdgriendt
 *
 */
@Local
public interface TransactieService {

	/**
	 * @return
	 */
	List<Transactie> getAlleTransacties();
}
