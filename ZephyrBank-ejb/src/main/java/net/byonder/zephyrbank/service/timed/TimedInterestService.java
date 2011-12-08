package net.byonder.zephyrbank.service.timed;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

import org.apache.log4j.Logger;

import net.byonder.zephyrbank.model.KredietRekening;
import net.byonder.zephyrbank.model.Rekening;
import net.byonder.zephyrbank.model.SpaarRekening;
import net.byonder.zephyrbank.service.RekeningService;

/**
 * Periodiek afvurende service die ervoor zorgt dat {@link SpaarRekening}'n hun rente krijgen. 
 * 
 * @author jvdgriendt
 *
 */
@Singleton
@LocalBean
public class TimedInterestService {
 
	private static final Logger LOG = Logger.getLogger(TimedInterestService.class);
	
	@EJB RekeningService rekeningService;
	
	
	@Schedule(minute="*/5",hour="*", persistent=false)
	public void updateRenteSchedule(){
		for(Rekening rekening : rekeningService.geefAlleRekening()){
			if(rekening instanceof SpaarRekening){
				LOG.debug(String.format("Rente updaten voor rekening %s ", rekening) );
				rekeningService.updateRente((SpaarRekening) rekening);
			}
		}
	}
	
	@Schedule(minute="*/2",hour="*", persistent=false)
	public void keerRenteUitSchedule(){
		boolean uitGekeerdOpKredit = false;
		for(Rekening rekening : rekeningService.geefAlleRekening()){
			if(rekening instanceof SpaarRekening){
				if(rekening.getEigenaar() != null){
					for(Rekening tmpRekening : rekening.getEigenaar().getRekeningen()){
						if(tmpRekening instanceof KredietRekening){
							uitGekeerdOpKredit = true;
							LOG.debug(String.format("Rente uitkeren voor rekening %s ", rekening) );
							rekeningService.keerRenteUit((SpaarRekening)rekening, (KredietRekening)tmpRekening);
						}
					}
				}
				if (!uitGekeerdOpKredit){
					LOG.debug(String.format("Rente uitkeren voor rekening %s ", rekening) );
					rekeningService.keerRenteUit((SpaarRekening)rekening, rekening);
				}
				
			}
		}
	}

}
