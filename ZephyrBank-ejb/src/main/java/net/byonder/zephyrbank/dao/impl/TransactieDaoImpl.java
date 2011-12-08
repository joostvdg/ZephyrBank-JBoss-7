package net.byonder.zephyrbank.dao.impl;

import javax.ejb.Stateless;

import net.byonder.zephyrbank.dao.TransactieDao;
import net.byonder.zephyrbank.model.Transactie;

/**
 * @author jvdgriendt
 *
 */
@Stateless
public class TransactieDaoImpl extends AbstractBaseDao<Transactie> implements TransactieDao{

	public TransactieDaoImpl() {
		super(Transactie.class);
	}

}
