package br.com.sme.core.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.Query;

import br.com.sme.core.entity.Teste;

public class TesteDAO extends AbstractDAO<Teste> {

	Logger logger = Logger.getLogger(TesteDAO.class.getName());

	public Teste findByLastIdUSuario(Long idUsuario) {
		logger.info("Buscando teste por idUsuario: " + idUsuario);

		StringBuilder sql = new StringBuilder();

		sql.append("select t from Teste t join fetch t.usuario usu where ");
		sql.append(" t.situacao = :situacao ");
		sql.append(" and usu.id = :idUsuario ");
		sql.append(" order by t.id desc ");

		Query query = em.createQuery(sql.toString());

		String situacaoTesteConcluido = "C";
		
		query.setParameter("situacao", situacaoTesteConcluido);
		query.setParameter("idUsuario", idUsuario);

		List<Teste> testes = query.setMaxResults(1).getResultList();
		
		logger.info("Teste encontrado: " + testes.toString());
		
		if(testes.isEmpty()) {
			return null;
		} else {
			return testes.get(0);
		}
		
	}

}
