package br.com.sme.core.facade;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.google.common.collect.Lists;

import br.com.sme.core.business.HierarquiaBusiness;
import br.com.sme.core.business.UsuarioBusiness;
import br.com.sme.core.dao.ConfiguracaoDAO;
import br.com.sme.core.dao.TesteDAO;
import br.com.sme.core.entity.Configuracao;
import br.com.sme.core.entity.Hierarquia;
import br.com.sme.core.entity.Teste;
import br.com.sme.core.entity.Usuario;
import br.com.sme.core.utils.JavaMailApp;
import br.com.sme.to.EmailTO;

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
	
	private static String INITIAL_HTML = "<html><head><style> table {font-family: arial, sans-serif;border-collapse: collapse;width: 60%;}  td, th {border: 1px solid #a7a7a7 ;text-align: left;padding: 8px;}  tr:nth-child(even) {background-color: #dddddd;}  .even {background-color: #dddddd;} </style>  </head> <body> ";
	private static String SAUDACAO_HTML = "Prezados, <br> os seguintes colaboradores não executaram testes nas ultimas ";

	private static String ASSUNTO_EMAIL = "Usuários que não executaram Teste de Prontidão";
	private static String TYPE = "text/html";
	
	private static String TIPO_USUARIO_OPERACIONAL = "O";

	public void gerarRelatorio() throws Exception {
		
		logger.info("******* processo iniciado");
		
		List<Hierarquia> hierarquias = hierarquiaBusiness.getHierarquiasQueEnviamNotificacoes();
		
		List<Configuracao> configuracoes = configuracaoDAO.findAll();

		if (configuracoes == null || configuracoes.isEmpty()) {
			return;
		}

		List<EmailTO> emailsTO = new ArrayList<EmailTO>();
		
		int quantidadeHierarquiasPorVez = 10;
		
		List<List<Hierarquia>> listaListaHierarquia =  Lists.partition(hierarquias, quantidadeHierarquiasPorVez);
		
		for(List<Hierarquia> listaHierarquia : listaListaHierarquia) {
			for (Hierarquia hierarquia : listaHierarquia) {

				logger.info("Verificando hierarquia: " + hierarquia.getDescricao());

				String matriculas = hierarquia.getMatriculasUsuariosReceberaoNotificacoes();

				logger.info("Matriculas que receberao email: " + matriculas);

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

					EmailTO emailTO = new EmailTO();
					emailTO.setAssunto(ASSUNTO_EMAIL + " - " + hierarquia.getDescricao());
					emailTO.setContentType(TYPE);
					emailTO.setConteudo(htmlMail);
					emailTO.setDestinatarios(destinatariosRelatorio.toString());

					emailsTO.add(emailTO);

				}

			}
			
			dispararEmail(configuracoes.get(0), emailsTO);
			
			emailsTO.clear();
		}

		

		
		
		logger.info("******* processo finalizado com sucesso");

	}

	private void dispararEmail(Configuracao configuracao, List<EmailTO> emailsTO) throws InterruptedException, AddressException, MessagingException {
		JavaMailApp javaMailApp = new JavaMailApp();

		String remetente = "relatorios.sme@gmail.com";
		String senha = "sme-1010";
		
		logger.info("******* quantidade de emails a serem enviados " + emailsTO.size());

		javaMailApp.enviarEmail(emailsTO, remetente, senha);

	}

	private String getHtmlEmail(Hierarquia hierarquia) {
		StringBuilder strConteudoEmail = new StringBuilder();

		strConteudoEmail.append(INITIAL_HTML);
		strConteudoEmail.append(SAUDACAO_HTML);
		
		strConteudoEmail.append(hierarquia.getQuantidadeHorasSemFazerTeste() + " horas.");
		
		strConteudoEmail.append(" <table>");
		strConteudoEmail.append(" <tr><th>Nome</th><th>Matricula</th></tr> ");

		boolean adicionouUsuario = false;

		for (Usuario usuario : hierarquia.getUsuarios()) {

			if (TIPO_USUARIO_OPERACIONAL.equals(usuario.getTipoUsuario())) {

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
