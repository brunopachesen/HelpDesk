package br.edu.bsi.helpdesk.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@SuppressWarnings("serial")
@Entity
public class Chamado extends GenericDomain {
	@Column(length = 50, nullable = false)
	private String natureza;
	
	@Column(length = 50, nullable = false)
	private short status;

	@Column(length = 15, nullable = false)
	private String descricao;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario usuario;
	
	public String getNatureza() {
		return natureza;
	}

	public void setNatureza(String natureza) {
		this.natureza = natureza;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	
	
}
