package br.edu.bsi.helpdesk.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class InteracaoChamado extends GenericDomain {
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Chamado chamado;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Funcionario funcionario;
	
	@Column(length = 140, nullable = false)
	private String resposta;
	
	public Date getDataInteracao() {
		return dataInteracao;
	}
	public void setDataInteracao(Date dataInteracao) {
		this.dataInteracao = dataInteracao;
	}
	@Column(nullable = false)
	private Date dataInteracao = new Date();
	
	public Chamado getChamado() {
		return chamado;
	}
	public void setChamado(Chamado chamado) {
		this.chamado = chamado;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public String getResposta() {
		return resposta;
	}
	public void setResposta(String resposta) {
		this.resposta = resposta;
	}
}
