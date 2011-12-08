package net.byonder.zephyrbank.bean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import net.byonder.zephyrbank.model.Transactie;
import net.byonder.zephyrbank.service.TransactieService;

@Named(value="transactieBean")
@SessionScoped
public class TransactieBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB(name="transactieService")
	TransactieService transactieService;
	
	private List<Transactie> transacties;
	
	public List<Transactie> getTransacties(){
		transacties = transactieService.getAlleTransacties();
		return transacties;
	}
}
