package br.com.sme.core.facade;

import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.inject.Inject;

import br.com.sme.core.business.HierarquiaBusiness;
import br.com.sme.core.business.UsuarioBusiness;
import br.com.sme.core.dao.ConfiguracaoDAO;
import br.com.sme.core.dao.TesteDAO;
import br.com.sme.core.entity.Configuracao;
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

	@Inject
	private ConfiguracaoDAO configuracaoDAO;

	Logger logger = Logger.getLogger(RelatorioFacade.class.getName());

	public void gerarRelatorio() throws Exception {

		List<Hierarquia> hierarquias = hierarquiaBusiness.getHierarquiasQueEnviamNotificacoes();

		JavaMailApp javaMailApp = new JavaMailApp();

		for (Hierarquia hierarquia : hierarquias) {

			String matriculas = hierarquia.getMatriculasUsuariosReceberaoNotificacoes();

			String matriculasArr[] = matriculas.split(";");

			StringBuilder destinatariosRelatorio = new StringBuilder();
			for (String matricula : matriculasArr) {

				Usuario usuario = usuarioBusiness.findByMatriculaEmailIsNotNull(matricula);

				if (usuario != null) {

					destinatariosRelatorio.append(usuario.getEmail());
					destinatariosRelatorio.append(",");
				}

			}

			String htmlMail = getHtmlEmail(hierarquia);

			if (!htmlMail.isEmpty()) {

				try {

					List<Configuracao> configuracoes = configuracaoDAO.findAll();

					if (!configuracoes.isEmpty()) {
						String remetente = configuracoes.get(0).getMailRemente();
						String senha = configuracoes.get(0).getSenhaRemetente();
						String assunto = "Usuários que não executaram Teste de Prontidão";

						String type = "text/html";

						javaMailApp.enviarEmail(assunto, htmlMail, remetente, destinatariosRelatorio.toString(), senha,
								type);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}

	}

	private String getHtmlEmail(Hierarquia hierarquia) {
		StringBuilder strConteudoEmail = new StringBuilder();

		strConteudoEmail.append("<html>");
		strConteudoEmail.append("<head>");
		strConteudoEmail.append("<style>");
		strConteudoEmail.append(" table {font-family: arial, sans-serif;border-collapse: collapse;width: 60%;} ");
		strConteudoEmail.append(" td, th {border: 1px solid #a7a7a7 ;text-align: left;padding: 8px;} ");
		strConteudoEmail.append(" tr:nth-child(even) {background-color: #dddddd;} ");
		strConteudoEmail.append(" .even {background-color: #dddddd;} ");
		strConteudoEmail.append(" </style> ");
		strConteudoEmail.append(" </head> ");
		strConteudoEmail.append(" <body> ");

		String saudacao = "Prezados, <br> os seguintes colaboradores não executaram testes nas ultimas "
				+ hierarquia.getQuantidadeHorasSemFazerTeste() + " horas.";
		strConteudoEmail.append(saudacao);

		strConteudoEmail.append(" <table>");
		strConteudoEmail.append(" <tr><th>Nome</th><th>Matricula</th></tr> ");

		boolean adicionouUsuario = false;

		String operacional = "O";

		for (Usuario usuario : hierarquia.getUsuarios()) {

			if (operacional.equals(usuario.getTipoUsuario())) {

				Teste teste = testeDAO.findByLastIdUSuario(usuario.getId());

				if (teste == null) {

					strConteudoEmail.append(generateLinhaRelatorio(usuario));

					adicionouUsuario = true;

				} else {
					Calendar atual = Calendar.getInstance();
					atual.add(Calendar.HOUR, -hierarquia.getQuantidadeHorasSemFazerTeste());

					Calendar dataTeste = Calendar.getInstance();
					dataTeste.setTime(teste.getDataExecucao());

					if (dataTeste.before(atual)) {

						strConteudoEmail.append(generateLinhaRelatorio(usuario));

						adicionouUsuario = true;
					}
				}
			}

		}

		strConteudoEmail.append("</table></body></html>");

		return adicionouUsuario ? strConteudoEmail.toString() : "";
	}

	private String generateLinhaRelatorio(Usuario usuario) {
		StringBuilder linhaRelatorio = new StringBuilder();

		linhaRelatorio.append("<tr>");

		linhaRelatorio.append("<td>");
		linhaRelatorio.append(usuario.getNome());
		linhaRelatorio.append("</td>");

		linhaRelatorio.append("<td>");
		linhaRelatorio.append(usuario.getMatricula());
		linhaRelatorio.append("</td>");

		linhaRelatorio.append("</tr>");

		return linhaRelatorio.toString();
	}

}
