package net.byonder.zephyrbank.interceptor;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.apache.log4j.Logger;

/**
 * Auditor voor het bij houden van Usage Logs.
 * 
 * @author jvdgriendt
 *
 */
public class AuditInterceptor {

	private static final Logger LOG = Logger.getLogger(AuditInterceptor.class);
	
	private static final String LOGFILE_LOCATION = "/home/jvdgriendt/usage_zephyr_bank.log";
	
	@PostConstruct @PreDestroy
	public void callMeBack(InvocationContext context){
		LOG.info(String.format(">>>> EJB CALLBACK: Type: %s, Target Class: %s.", context.getClass().getSimpleName(), context.getTarget().getClass().getSimpleName()));
	}
	
	@AroundInvoke
	public Object logMethodCalls(final InvocationContext context) throws Exception{
		File file = new File(LOGFILE_LOCATION);
		FileOutputStream fos = new FileOutputStream(file, true);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		try{
			String usageRegel = String.format("[Type: %s, Class: %s, Method: %s, Timestamp: [%4$te-%4$tm-%4$tY %4$tT %4$tZ]]%n", context.getClass().getSimpleName(), context.getTarget().getClass().getSimpleName(), context.getMethod().getName(), new Date());			
			bos.write(usageRegel.getBytes());
			
			return context.proceed();
		} finally {
			bos.close();
			fos.close();
		}
	}
}
