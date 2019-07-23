package br.com.sme.core.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TB_HIE_HIERARQUIA")
public class Hierarquia implements DefaultEntity, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "hie_id_hierarquia")
	private Long id;

	@Column(name = "hie_ds_hierarquia")
	private String descricao;

	@Column(name = "fl_envio_usuario_nao_fez_teste")
	private int flagRelatorioUsuariosNaoFizeramTeste;

	@Column(name = "qt_horas_usuario_nao_fez_teste")
	private int quantidadeHorasSemFazerTeste;

	@Column(name = "matriculas_relatorio_teste")
	private String matriculasUsuariosReceberaoNotificacoes;

	@OneToMany(mappedBy = "hierarquia", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Usuario> usuarios;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getFlagRelatorioUsuariosNaoFizeramTeste() {
		return flagRelatorioUsuariosNaoFizeramTeste;
	}

	public void setFlagRelatorioUsuariosNaoFizeramTeste(int flagRelatorioUsuariosNaoFizeramTeste) {
		this.flagRelatorioUsuariosNaoFizeramTeste = flagRelatorioUsuariosNaoFizeramTeste;
	}

	public int getQuantidadeHorasSemFazerTeste() {
		return quantidadeHorasSemFazerTeste;
	}

	public void setQuantidadeHorasSemFazerTeste(int quantidadeHorasSemFazerTeste) {
		this.quantidadeHorasSemFazerTeste = quantidadeHorasSemFazerTeste;
	}

	public String getMatriculasUsuariosReceberaoNotificacoes() {
		return matriculasUsuariosReceberaoNotificacoes;
	}

	public void setMatriculasUsuariosReceberaoNotificacoes(String matriculasUsuariosReceberaoNotificacoes) {
		this.matriculasUsuariosReceberaoNotificacoes = matriculasUsuariosReceberaoNotificacoes;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Hierarquia [id=" + id + ", descricao=" + descricao + ", flagRelatorioUsuariosNaoFizeramTeste="
				+ flagRelatorioUsuariosNaoFizeramTeste + ", quantidadeHorasSemFazerTeste="
				+ quantidadeHorasSemFazerTeste + ", matriculasUsuariosReceberaoNotificacoes="
				+ matriculasUsuariosReceberaoNotificacoes + "]";
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
		Hierarquia other = (Hierarquia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
