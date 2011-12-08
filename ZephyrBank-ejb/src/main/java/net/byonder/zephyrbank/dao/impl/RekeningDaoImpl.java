package net.byonder.zephyrbank.dao.impl;

import javax.ejb.Stateless;

import net.byonder.zephyrbank.dao.RekeningDao;
import net.byonder.zephyrbank.model.Rekening;

@Stateless(name="rekeningDao", mappedName="rekeningDao")
public class RekeningDaoImpl extends AbstractBaseDao<Rekening> implements RekeningDao {

	public RekeningDaoImpl(){
		super(Rekening.class);
	}
}
