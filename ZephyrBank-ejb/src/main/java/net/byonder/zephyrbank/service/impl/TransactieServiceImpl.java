package net.byonder.zephyrbank.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import net.byonder.zephyrbank.dao.TransactieDao;
import net.byonder.zephyrbank.model.Transactie;
import net.byonder.zephyrbank.service.TransactieService;

/**
 * @author jvdgriendt
 *
 */
@Stateless(name="transactieService", mappedName="transactieService")
public class TransactieServiceImpl implements TransactieService{

	@EJB
	TransactieDao transactieDao;
	
	@Override
	public List<Transactie> getAlleTransacties() {
		return transactieDao.vindAlle();
	}

}
