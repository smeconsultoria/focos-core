package br.com.sme.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_tre_teste_executado")
public class Teste implements DefaultEntity, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "tre_id_teste_executado")
	private Long id;

	@Column(name = "tre_dt_execucao")
	private Date dataExecucao;

	@Column(name = "tre_st_teste")
	private String situacao;

	@ManyToOne
	@JoinColumn(name = "usr_id_usuario")
	private Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataExecucao() {
		return dataExecucao;
	}

	public void setDataExecucao(Date dataExecucao) {
		this.dataExecucao = dataExecucao;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
