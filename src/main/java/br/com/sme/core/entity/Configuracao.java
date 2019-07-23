package br.com.sme.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_cfg_configuracao")
public class Configuracao implements DefaultEntity, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cfg_cd_codigo")
	private Long id;

	@Column(name = "cfg_mail_ds_mail_remetente")
	private String mailRemente;

	@Column(name = "cfg_mail_ds_senha_remetente")
	private String senhaRemetente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMailRemente() {
		return mailRemente;
	}

	public void setMailRemente(String mailRemente) {
		this.mailRemente = mailRemente;
	}

	public String getSenhaRemetente() {
		return senhaRemetente;
	}

	public void setSenhaRemetente(String senhaRemetente) {
		this.senhaRemetente = senhaRemetente;
	}

	@Override
	public String toString() {
		return "Configuracao [id=" + id + ", mailRemente=" + mailRemente + ", senhaRemetente=" + senhaRemetente + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Configuracao other = (Configuracao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
