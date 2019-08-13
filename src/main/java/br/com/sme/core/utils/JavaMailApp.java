package br.com.sme.core.utils;

import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.google.common.collect.Lists;

import br.com.sme.to.EmailTO;

public class JavaMailApp {

	Logger logger = Logger.getLogger(JavaMailApp.class.getName());
	
	@SuppressWarnings("static-access")
	public void enviarEmail(List<EmailTO> emailsTO, final String remetente, final String senha)
			throws AddressException, MessagingException, InterruptedException {

		Session session = getSession(remetente, senha);

		Transport transport = session.getTransport("smtp");
		

		int quantidadePorVez = 10;
		
		
		
		if (emailsTO.size() < quantidadePorVez) {
			transport.connect("smtp.gmail.com", 587, remetente, senha);
			
			sendEmail(transport, emailsTO, session, remetente);

			transport.close();
		} else {

			List<List<EmailTO>> listaListaEmailTO = Lists.partition(emailsTO, quantidadePorVez);
			
			for(List<EmailTO> lista: listaListaEmailTO) {
				
				logger.info("transport.connect");
				
				transport.connect("smtp.gmail.com", 587, remetente, senha);
				
				logger.info("conseguiu o transport connect");
				
				sendEmail(transport, lista, session, remetente);
				
				transport.close();
			}
			

		}

	}

	@SuppressWarnings("static-access")
	private void sendEmail(Transport transport, List<EmailTO> emailsTO, Session session, String remetente)
			throws InterruptedException, AddressException, MessagingException {

		
		

		for (EmailTO emailTO : emailsTO) {

			int maxTries = 3;
			
			boolean tentarNovamente = true;
			
			Message message = getMessage(session, remetente, emailTO);

			while (tentarNovamente) {
				try {
					
					logger.info("Enviando o email: " + emailTO.getAssunto());
					
					Transport.send(message);

					tentarNovamente = false;
				} catch (Exception e) {
					e.printStackTrace();

					logger.info("Email que deu erro: " + emailTO.getAssunto());
					
					Thread.sleep(3000);

					maxTries = maxTries - 1;

					if (maxTries == 0) {

						tentarNovamente = false;

					}
				}
			}

		}

	}

	private Session getSession(final String remetente, final String senha) {
		Properties prop = new Properties();

		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true"); // TLS

		final PasswordAuthentication auth = new PasswordAuthentication(remetente, senha);
		
		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return auth;
			}
		});

		/** Ativa Debug para sessão */
		session.setDebug(false);

		return session;
	}

	private Message getMessage(Session session, String remetente, EmailTO emailTO)
			throws AddressException, MessagingException {
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(remetente));

		Address[] toUser = InternetAddress.parse(emailTO.getDestinatarios());

		message.setRecipients(Message.RecipientType.TO, toUser);
		message.setSubject(emailTO.getAssunto());

		message.setContent(emailTO.getConteudo(), emailTO.getContentType());

		return message;
	}
}