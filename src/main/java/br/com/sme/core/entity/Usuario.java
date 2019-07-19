package br.com.sme.core.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TB_USR_USUARIO")
public class Usuario implements DefaultEntity, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "usr_id_usuario")
	private Long id;

	@Column(name = "usr_nm_usuario")
	private String nome;

	@Column(name = "usr_id_matricula")
	private String matricula;

	@Column(name = "usr_st_usuario")
	private String situacao;

	@Column(name = "usr_ds_email")
	private String email;

	@Column(name = "usr_id_tipo_usuario")
	private String tipoUsuario;

	@ManyToOne
	@JoinColumn(name = "hie_id_hierarquia")
	private Hierarquia hierarquia;

	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Teste> testes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public Hierarquia getHierarquia() {
		return hierarquia;
	}

	public void setHierarquia(Hierarquia hierarquia) {
		this.hierarquia = hierarquia;
	}

	public List<Teste> getTestes() {
		return testes;
	}

	public void setTestes(List<Teste> testes) {
		this.testes = testes;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
