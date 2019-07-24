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
	
	@Schedule(hour="*", minute="00,5,10,15,20,25,30,35,40,45,50,55", second="00")
	void agendado() {
		try {
			 
			 relatorioFacade.gerarRelatorio();
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
	 
	}
	
}
