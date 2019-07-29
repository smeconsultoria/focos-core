package br.com.sme.core.dao;

import java.util.logging.Logger;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.sme.core.entity.Usuario;

public class UsuarioDAO extends AbstractDAO<Usuario> {

	Logger logger = Logger.getLogger(HierarquiaDAO.class.getName());

	public Usuario findByMatricula(String matricula, String situacao, boolean emailIsNotNull) {

		StringBuilder sql = new StringBuilder();

		sql.append("select usu from Usuario usu where ");
		sql.append(" usu.matricula = :matricula ");
		sql.append(" and usu.situacao = :situacao");

		if (emailIsNotNull) {
			sql.append(" and email is not null ");
		}

		Query query = em.createQuery(sql.toString());

		query.setParameter("matricula", matricula);
		query.setParameter("situacao", situacao);

		try {
			Usuario usuario = (Usuario) query.getSingleResult();

			return usuario;

		} catch (NoResultException e) {

			return null;

		}

	}

}
