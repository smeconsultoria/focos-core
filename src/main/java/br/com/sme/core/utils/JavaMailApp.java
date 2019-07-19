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
	
	public static void main(String[] args) {
		JavaMailApp j = new JavaMailApp();
		j.enviarEmail("assunto","teste");
		
	}
	public void enviarEmail(String assunto, String conteudo) {
		Properties prop = new Properties();
		
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
        
		Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("no.reply.smeconsultoria@gmail.com", "sme-1010");
			}
		});

		/** Ativa Debug para sessão */
		session.setDebug(false);

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("seuemail@gmail.com"));
			// Remetente

			Address[] toUser = InternetAddress 
					.parse("jonasromao@gmail.com");//"seuamigo@gmail.com, seucolega@hotmail.com, seuparente@yahoo.com.br");

			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject(assunto);// Assunto
			message.setText(conteudo);
			/** Método para enviar a mensagem criada */
			Transport.send(message);

			System.out.println("Feito!!!");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}