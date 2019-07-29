package br.com.sme.to;

public class EmailTO {

	private String assunto;
	private String conteudo;
	private String destinatarios;
	private String contentType;

	public EmailTO() {
	}

	public EmailTO(String assunto, String conteudo, String remetente, String contentType) {
		super();
		this.assunto = assunto;
		this.conteudo = conteudo;
		this.destinatarios = remetente;
		this.contentType = contentType;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public String getDestinatarios() {
		return destinatarios;
	}

	public void setDestinatarios(String destinatarios) {
		this.destinatarios = destinatarios;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

}
