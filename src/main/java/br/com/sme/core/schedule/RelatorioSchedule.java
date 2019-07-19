package br.com.sme.core.schedule;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.sme.core.facade.RelatorioFacade;
import br.com.sme.core.utils.JavaMailApp;

@Stateless
public class RelatorioSchedule {
	
	@Inject
	private RelatorioFacade relatorioFacade;
	
	JavaMailApp javaMailApp = new JavaMailApp();
	
	@Schedule(hour="*", minute="36", second="35")
	void agendado() {
		try {
			 System.out.println("agendado pela anotacao @Schedule");
			  
			 
			 relatorioFacade.gerarRelatorio();
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
	 
	}
	
}
