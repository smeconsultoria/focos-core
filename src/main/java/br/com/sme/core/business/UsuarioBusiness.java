package br.com.sme.core.business;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.sme.core.dao.UsuarioDAO;
import br.com.sme.core.entity.Usuario;

@Stateless
public class UsuarioBusiness {

	@Inject
	private UsuarioDAO usuarioDAO;
	
	public Usuario findByMatriculaEmailIsNotNull(String matricula) {
		boolean situacaoAtiva = true;
		boolean emailIsNotNull = true;
		
		return usuarioDAO.findByMatricula(matricula, situacaoAtiva, emailIsNotNull);
	}

	
}
