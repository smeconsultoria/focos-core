package br.com.sme.core.dao;

import java.util.logging.Logger;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.sme.core.entity.Usuario;

public class UsuarioDAO extends AbstractDAO<Usuario> {

	Logger logger = Logger.getLogger(HierarquiaDAO.class.getName());

	public Usuario findByMatricula(String matricula, boolean situacao, boolean emailIsNotNull) {
		logger.info("Buscando usuario por matricula: " + matricula);

		StringBuilder sql = new StringBuilder();

		sql.append("select usu from Usuario usu where ");
		sql.append(" matricula = :matricula ");
		sql.append(" and situacao = :situacao");

		
		if(emailIsNotNull) {
			sql.append(" and email is not null ");
		}
		
		Query query = em.createQuery(sql.toString());

		String situacaoStr = situacao ? "A" : "I";

		query.setParameter("matricula", matricula);
		query.setParameter("situacao", situacaoStr);

		try {
			Usuario usuario = (Usuario) query.getSingleResult();

			logger.info("Usuario encontrado: " + usuario.toString());

			return usuario;

		} catch (NoResultException e) {

			return null;

		}

	}

}
