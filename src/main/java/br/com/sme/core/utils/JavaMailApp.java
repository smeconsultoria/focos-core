package br.com.sme.core.utils;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailApp {

	public void enviarEmail(String assunto, String conteudo, final String remetente, String destinatarios, final String senha, String contentType) {
		Properties prop = new Properties();

		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true"); // TLS

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(remetente, senha);
			}
		});

		/** Ativa Debug para sessão */
		session.setDebug(false);

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(remetente));

			Address[] toUser = InternetAddress.parse(destinatarios);

			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject(assunto);
			
			message.setContent(conteudo,contentType);
			/** Método para enviar a mensagem criada */
			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}