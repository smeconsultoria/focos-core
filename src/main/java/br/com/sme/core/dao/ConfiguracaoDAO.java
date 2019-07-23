package br.com.sme.core.dao;

import javax.annotation.PostConstruct;

import br.com.sme.core.entity.Configuracao;

public class ConfiguracaoDAO extends AbstractDAO<Configuracao> {

	@PostConstruct
	private void postConstruct() {
		setPersistentClass(Configuracao.class);
	}

}
