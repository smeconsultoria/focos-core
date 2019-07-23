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

			String matriculasArr[] = matriculas.split(",");

			StringBuilder emailsUsuarios = new StringBuilder();

			List<Usuario> usuarios = hierarquia.getUsuarios();

			logger.info("usuarios encontrados: " + usuarios.size());

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
			
			
			String saudacao = "Prezados, <br> os seguintes colaboradores não executaram testes nas ultimas " + hierarquia.getQuantidadeHorasSemFazerTeste() + " horas.";
			strConteudoEmail.append(saudacao);
			
			
			strConteudoEmail.append(" <table>");
			strConteudoEmail.append(" <tr><th>Nome</th><th>Matricula</th></tr> ");
			strConteudoEmail.append("");
			strConteudoEmail.append("");
			
			int contador = 2;
			for (Usuario usuario : usuarios) {

				Teste teste = testeDAO.findByLastIdUSuario(usuario.getId());

				if (teste != null) {
					Calendar atual = Calendar.getInstance();
					atual.add(Calendar.HOUR, -hierarquia.getQuantidadeHorasSemFazerTeste());

					Calendar dataTeste = Calendar.getInstance();
					dataTeste.setTime(teste.getDataExecucao());

					
					if(dataTeste.before(atual)) {
						
						if(contador%2 == 0) {
							
							strConteudoEmail.append("<tr>");
							
						} else {
							
							strConteudoEmail.append("<tr class=\"even\">");
							
						}
						
						strConteudoEmail.append("<td>");
						strConteudoEmail.append(teste.getUsuario().getNome());
						strConteudoEmail.append("</td>");	
						
						strConteudoEmail.append("<td>");
						strConteudoEmail.append(teste.getUsuario().getMatricula());
						strConteudoEmail.append("</td>");
						
						strConteudoEmail.append("</tr>");
						
						
					}

				}

			}
			
			if(!strConteudoEmail.toString().isEmpty()) {
				
				strConteudoEmail.append("</table></body></html>");
				
				try {
				
					List<Configuracao> configuracoes = configuracaoDAO.findAll();
					
					if(!configuracoes.isEmpty()) {
						String remetente = configuracoes.get(0).getMailRemente();
						String senha = configuracoes.get(0).getSenhaRemetente();
						String assunto = "Usuários que não executaram Teste de Prontidão";
						
						String type = "text/html";
						
						javaMailApp.enviarEmail(assunto, strConteudoEmail.toString(), remetente, senha, type);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				
				
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
