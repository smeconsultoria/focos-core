package br.com.sme.core.facade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.inject.Inject;

import br.com.sme.core.business.HierarquiaBusiness;
import br.com.sme.core.business.UsuarioBusiness;
import br.com.sme.core.dao.TesteDAO;
import br.com.sme.core.entity.Hierarquia;
import br.com.sme.core.entity.Teste;
import br.com.sme.core.entity.Usuario;
import br.com.sme.core.utils.JavaMailApp;

public class RelatorioFacade {

	@EJB
	private HierarquiaBusiness hierarquiaBusiness;

	@EJB
	private UsuarioBusiness usuarioBusiness;

	@Inject
	private TesteDAO testeDAO;

	Logger logger = Logger.getLogger(RelatorioFacade.class.getName());

	public void gerarRelatorio() {

		List<Hierarquia> hierarquias = hierarquiaBusiness.getHierarquiasQueEnviamNotificacoes();
		
		JavaMailApp javaMailApp = new JavaMailApp();
		
		System.out.println("************************************************ " + hierarquias.size());

		for (Hierarquia hierarquia : hierarquias) {

			String matriculas = hierarquia.getMatriculasUsuariosReceberaoNotificacoes();

			String matriculasArr[] = matriculas.split(",");

			StringBuilder emailsUsuarios = new StringBuilder();

			List<Usuario> usuarios = hierarquia.getUsuarios();

			logger.info("usuarios encontrados: " + usuarios.size());

			StringBuilder strUsuariosQueNaoFizeramTestes = new StringBuilder();
			
			for (Usuario usuario : usuarios) {

				Teste teste = testeDAO.findByLastIdUSuario(usuario.getId());

				if (teste != null) {
					Calendar atual = Calendar.getInstance();
					atual.add(Calendar.HOUR, -hierarquia.getQuantidadeHorasSemFazerTeste());

					Calendar dataTeste = Calendar.getInstance();
					dataTeste.setTime(teste.getDataExecucao());

					
					if(dataTeste.before(atual)) {
						strUsuariosQueNaoFizeramTestes.append(teste.getUsuario().getNome() + "\n");
					}

				}

			}
			
			if(!strUsuariosQueNaoFizeramTestes.toString().isEmpty()) {
				javaMailApp.enviarEmail(hierarquia.getDescricao(), strUsuariosQueNaoFizeramTestes.toString());
			}
			

			/*
			 * for (String matricula : matriculasArr) {
			 * 
			 * Usuario usuario = usuarioBusiness.findByMatriculaEmailIsNotNull(matricula);
			 * 
			 * if (usuario != null) {
			 * 
			 * emailsUsuarios.append(usuario.getEmail()); }
			 * 
			 * }
			 * 
			 * System.out.println(emailsUsuarios);
			 */

		}

	}
	
	
	public static void main(String[] args) {
		
		try {
			Calendar atual = Calendar.getInstance();
			atual.add(Calendar.HOUR, -48);

			Calendar dataTeste = Calendar.getInstance();
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date1 = formatter1.parse("17/07/2019 10:00:00");
			dataTeste.setTime(date1);

			System.out.println(dataTeste.before(atual));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		
	}
}
