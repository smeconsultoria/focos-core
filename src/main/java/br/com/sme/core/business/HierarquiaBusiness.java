package br.com.sme.core.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.sme.core.dao.HierarquiaDAO;
import br.com.sme.core.entity.Hierarquia;

@Stateless
public class HierarquiaBusiness {

	@Inject
	private HierarquiaDAO hierarquiaDAO;
	
	
	public List<Hierarquia> getHierarquiasQueEnviamNotificacoes() {
		
		return hierarquiaDAO.getHierarquiasQueEnviamNotificacoes();
		
	}
	
	
	
}
