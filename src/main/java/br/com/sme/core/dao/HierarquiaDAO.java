package br.com.sme.core.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.Query;

import br.com.sme.core.entity.Hierarquia;

public class HierarquiaDAO extends AbstractDAO<Hierarquia> {

	Logger logger = Logger.getLogger(HierarquiaDAO.class.getName());

	public List<Hierarquia> getHierarquiasQueEnviamNotificacoes() {

		logger.info("Buscando hierarquias que enviam notificacoes de usuarios que nao fizeram testes");

		StringBuilder sql = new StringBuilder();
		
		sql.append("select hie from Hierarquia hie join fetch hie.usuarios usu where ");
		sql.append(" hie.flagRelatorioUsuariosNaoFizeramTeste = :flagRelatorioUsuariosNaoFizeramTeste ");
		sql.append(" and hie.matriculasUsuariosReceberaoNotificacoes is not null ");
		sql.append(" and usu.situacao = :situacaoUsuarios ");
		
		
		Query query = em.createQuery(sql.toString());
		
		int flagRelatorioUsuariosNaoFizeramTesteAtivo = 1;
		String situacaoUsuariosAtivo = "A";
		
		query.setParameter("flagRelatorioUsuariosNaoFizeramTeste", flagRelatorioUsuariosNaoFizeramTesteAtivo);
		query.setParameter("situacaoUsuarios", situacaoUsuariosAtivo);
		
		
		List<Hierarquia> hierarquias = query.getResultList();
		logger.info("Hierarquias encontradas: " + hierarquias.toString());
		
		return hierarquias;
		
	}

}
